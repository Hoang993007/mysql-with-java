package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;

public class QuanLyMonHoc{
    public void exec(){
	//***********************************************************
	Connection conn = null;
	Statement stmt = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection(DB_CHOOSE.DB_URL, DB_CHOOSE.USER, DB_CHOOSE.PASS);
stmt=conn.createStatement();
//**********************************************************
	      
	    String sql=new String();
	    String MaMH=new String();
	    String TenMH=new String();
	    int SoTC=0;
	    String check=new String();
	    Scanner scan=new Scanner(System.in);

	    System.out.println();
	    int choice;
	    do
		{
		    choice=new Menu().getChoice(Menu.menuQuanLyMonHoc);
		    switch(choice)
			{
			case 1:
			    System.out.println("Thêm môn học mới");
			    System.out.println();
			    
			    do{
				System.out.print("Ma mon hoc: ");
				MaMH=scan.nextLine();
				
				//check if exists
				if(new CheckExists().checkExists("MonHoc","MaMH",MaMH)==1){
				    System.out.println("The record already exists");
				}else{
					System.out.print("Ten mon hoc: ");
					TenMH=scan.nextLine();
					
					System.out.print("So tin chi: ");
					SoTC=scan.nextInt();
					scan.nextLine();//clear buffer like in C;
					
					sql="insert into MonHoc "
					    +"values('"+MaMH+"','"+TenMH+"',"+SoTC+")";
					stmt.executeUpdate(sql);
				    }
				
				
				System.out.print("\n### Type y to continue/n to exit: ");
				check=scan.nextLine();
				System.out.println();
			    }while(check.equals("y"));
			    
			    break;
			    
			case 2:
			    System.out.println("Sửa thông tin môn học");
			    System.out.println();
			    
			    do{
				System.out.print("Ma mon hoc need to be updated: ");
				String UDMaMH=scan.nextLine();
				
				//check if exists
				if(new CheckExists().checkExists("MonHoc","MaMH",UDMaMH)==0){
					System.out.println("The record doesn't exists\n");
				    }
				else{//exitst
				    //start to update
				    
				    System.out.print("\nUpdate ma mon hoc? (y/n): ");
				    check=scan.nextLine();

				    if(check.equals("y"))
					{
					    System.out.print("Ma mon hoc: ");
					    MaMH=scan.nextLine();
					    if(new CheckExists().checkExists("MonHoc","MaMH",MaMH)==1)
						{
						    System.out.println("The record already exists\n");
						}
					    else{//exitst
						sql="update MonHoc set MaMH='"+MaMH+"'  where MaMH='"+UDMaMH+"'";//notice the space " where
						stmt.executeUpdate(sql);
						UDMaMH=MaMH;
					    }
					}
				    
				    System.out.print("\nUpdate ten mon hoc? (y/n): ");
				    check=scan.nextLine();

				    if(check.equals("y"))
					{
					    System.out.print("Ten mon hoc: ");
					    TenMH=scan.nextLine();
					    sql="update MonHoc set TenMH='"+TenMH+"' where MaMH='"+UDMaMH+"'";
					    stmt.executeUpdate(sql);
					}
				    
				    System.out.print("\nUpdate so tin chi? (y/n): ");
				    check=scan.nextLine();

				    if(check.equals("y"))
					{
					    System.out.print("So tin chi: ");
					    SoTC=scan.nextInt();
					    check=scan.nextLine();
					    sql="update MonHoc set SoTC="+SoTC+" where MaMH='"+UDMaMH+"'";
					    stmt.executeUpdate(sql);
					}
				    
				    sql="select * from MonHoc where MaMH='"+UDMaMH+"'";
				    ResultSet rs=stmt.executeQuery(sql);
				    
                                    System.out.println();
				    while(rs.next()){
					//Retrieve by column name
					System.out.print("MaMH: "+rs.getString("MaMH"));
					System.out.print(", TenMH: "+rs.getString("TenMH"));
					System.out.println(", SoTC: "+rs.getInt("SoTC"));
				    }
				    rs.close();
				}
				
				System.out.print("\n### Type y to continue/n to exit: ");
				check=scan.nextLine();
				System.out.println();
			    }while(check.equals("y"));
			    break;
			    
			    
			case 3:
			    System.out.println("In danh sách môn học");
			    System.out.println();
			    
			    sql="select * from MonHoc";
			    ResultSet rs=stmt.executeQuery(sql);
			     
			    while(rs.next()){
				//Retrieve by column name
				System.out.print("MaMH: "+rs.getString("MaMH"));
				System.out.print(", TenMH: "+rs.getString("TenMH"));
				System.out.println(", SoTC: "+rs.getInt("SoTC"));
			    }scan.nextLine();
			    rs.close();
stmt.execute("--");
			    break;
			case 4:
			    System.out.println("Quit");
		System.out.println("**************************************");
				System.out.println();
			    break;
			}
		}while(choice!=4);
		    
		    //***********************************************
		}catch(SQLException se){
	    //Handle errors for JDBC
	    se.printStackTrace();
	}catch(Exception e){
	    //Handle errors for Class.forName
	    e.printStackTrace();
	}finally{
	    //finally block used to close resources
	    try{
		if(stmt!=null)
		    conn.close();
	    }catch(SQLException se){
	    }// do nothing
	    try{
		if(conn!=null)
		    conn.close();
	    }catch(SQLException se){
		se.printStackTrace();
	    }//end finally try
	}//end try
	//*******************************************************
    }
}
