package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;
import sqlPerforment.*;

public class QuanLyGiaoVien{
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
	    String MaGV=new String();
            String HoGV=new String();
	    String TenGV=new String();
	    String DonVi=new String();

	    String[] fieldsNameToPrint;

	    int count;
	    
	    String check=new String();
	    Scanner scan=new Scanner(System.in);

	    System.out.println();
	    int choice;
	    
	    do
		{
		    choice=new Menu().getChoice(Menu.menuQuanLyGiaoVien);
		    switch(choice)
			{
			case 1:
			    System.out.println("Thêm hồ sơ giáo viên\n");
			    
			    do{
				System.out.print("Ma giao vien: ");
				MaGV=scan.nextLine();
				
				//check if exists
				if(new CheckExists().checkExists("GiaoVien","MaGV",MaGV)==1)
                                    {
				    System.out.println("\nThe record already exists");
				}else
				    {
					System.out.print("Ho giao vien: ");
					HoGV=scan.nextLine();
					
					System.out.print("Ten giao vien: ");
					TenGV=scan.nextLine();
					
					System.out.print("Don vi: ");
					DonVi=scan.nextLine();
					
					sql="insert into GiaoVien "
					    +"values('"+MaGV+"','"+HoGV+"','"+TenGV+"','"+DonVi+"')";
					stmt.executeUpdate(sql);
				    }
				
				System.out.print("\n### Type y to continue/n to exit: ");
				check=scan.nextLine();
			    }while(check.equals("y"));
			    break;
			    
			    
			case 2:
			    System.out.println("\nSửa thông tin giáo viên\n");
			    
			    do{
				System.out.print("Ma giao vien need to be updated: ");
				String UDMaGV=scan.nextLine();
				
				//check if exists
				if(new CheckExists().checkExists("GiaoVien","MaGV",UDMaGV)==0)
				    {
					System.out.println("The record doesn't exists\n");
				    }
				else{//exitst
				    //start to update
				    System.out.print("\nUpdate ma giao vien? (y/n): ");
				    check=scan.nextLine();

				    if(check.equals("y"))
					{
					    System.out.print("Ma giao vien: ");
					    MaGV=scan.nextLine();
					    
					    if(new CheckExists().checkExists("GiaoVien","MaGV",MaGV)==1)
						{
						    System.out.println("The record already exists\n");
						}
					    else{//exitst
						sql="update GiaoVien set MaGV='"+MaGV+"'  where MaGV='"+UDMaGV+"'";//notice the space " where
						stmt.executeUpdate(sql);
						UDMaGV=MaGV;
					    }
					}
				    
				    System.out.print("\nUpdate ho giao vien? (y/n): ");
				    check=scan.nextLine();

				    if(check.equals("y"))
					{
					    System.out.print("Ho giao vien: ");
					    HoGV=scan.nextLine();
					    sql="update GiaoVien set HoGV='"+HoGV+"' where MaGV='"+UDMaGV+"'";
					    stmt.executeUpdate(sql);
					}
				    
				    System.out.print("\nUpdate ten giao vien? (y/n): ");
				    check=scan.nextLine();

				    if(check.equals("y"))
					{
					    System.out.print("Ten giao vien: ");
					    TenGV=scan.nextLine();
					    sql="update GiaoVien set TenGV='"+TenGV+"' where MaGV='"+UDMaGV+"'";
					    stmt.executeUpdate(sql);
					}
				    
				    System.out.print("\nUpdate don vi? (y/n): ");
				    check=scan.nextLine();

				    if(check.equals("y"))
					{
					    System.out.print("Don vi: ");
					    DonVi=scan.nextLine();
					    sql="update GiaoVien set DonVi='"+DonVi+"' where MaGV='"+UDMaGV+"'";
					    stmt.executeUpdate(sql);
					}
				    
				    sql="select * from GiaoVien where MaGV='"+UDMaGV+"'";

				    fieldsNameToPrint=new String[]{"MaGV","TenGV","HoGV","DonVi"};
				    new SQLPerforment().prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
				}
				
				System.out.print("\n### Type y to continue/n to exit: ");
				check=scan.nextLine();
				System.out.println();
			    }while(check.equals("y"));
			    break;
			    
			    
			case 3:
			    System.out.println("In danh sách giáo viên");
			    System.out.println();
			    
			    sql="select * from GiaoVien";

				    fieldsNameToPrint=new String[]{"MaGV","TenGV","HoGV","DonVi"};
				    new SQLPerforment().prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);

			    scan.nextLine();

			    break;
			    
			    
			case 4:
			    System.out.println("Tìm kiếm giáo viên");
			    System.out.println();
			    
			    do{
				String sql1;
				sql="select * from GiaoVien where ";
				sql1=sql;
				count=0;
				
				System.out.print("\nUse Ma giao vien to search? (y/n): ");
				check=scan.nextLine();

				if(check.equals("y"))
				    {
					System.out.print("Ma giao vien: ");
					MaGV=scan.nextLine();
					sql=sql+"MaGV='"+MaGV+"' ";
					count=count+1;
				    }
				
				System.out.print("\nUse Ten giao vien to search? (y/n): ");
				check=scan.nextLine();

				if(check.equals("y"))
				    {
					System.out.print("Ten giao vien: ");
					TenGV=scan.nextLine();
					if(count>=1) sql=sql+"and "; else count=count+1;
					sql=sql+"TenGV='"+TenGV+"' ";
				    }
				
				System.out.print("\nUse Ho giao vien to search? (y/n): ");
				check=scan.nextLine();
				
				if(check.equals("y"))
				    {
					System.out.print("Ho giao vien: ");
					HoGV=scan.nextLine();
					if(count>=1) sql=sql+"and "; else count=count+1;
					sql=sql+"HoGV='"+HoGV+"' ";
				    }
				
				System.out.print("\nUse Don vi to search? (y/n): ");
				check=scan.nextLine();
				
				if(check.equals("y"))
				    {
					System.out.print("Don vi: ");
					DonVi=scan.nextLine();
					if(count>=1) sql=sql+"and "; else count=count+1;
					sql=sql+"DonVi='"+DonVi+"' ";
				    }

	                         int checkPrint=0;
				if(!sql.equals(sql1)){

				    fieldsNameToPrint=new String[]{"MaGV","TenGV","HoGV","DonVi"};
				  checkPrint=new SQLPerforment().prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
				}
				if(checkPrint==0){
				    System.out.println("\n*****************\nNo data\n*****************");
				}
				
				System.out.print("\n### Type y to continue/n to exit: ");
				check=scan.nextLine();
				System.out.println();
			    }while(check.equals("y"));
			    break;


			case 5:
			    System.out.println("Quit");
			    System.out.println("**************************************");
			    System.out.println();
			    break;
			}
		}while(choice!=5);
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
