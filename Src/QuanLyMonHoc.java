package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;
import sqlPerforment.*;

public class QuanLyMonHoc{
    
    Statement stmt;
    public QuanLyMonHoc(Statement stmt){
	this.stmt=stmt;
    }
    
    public void exec() throws  SQLException, Exception{
	String sql=new String();
	String MaMH=new String();
	String TenMH=new String();
	int SoTC=0;
	String check=new String();
	
	Scanner scan=new Scanner(System.in);
	
	String[] fieldsNameToPrint;
	
	System.out.println();
	int choice;
	do{
	    choice=new Menu().getChoice(Menu.menuQuanLyMonHoc);
	    switch(choice){
	    case 1:
		System.out.println("Thêm môn học mới");
			
		do{
		    System.out.println();
		    
		    try_case_1:
		    try{
			System.out.print("Ma mon hoc: ");
			MaMH=scan.nextLine();
			if(MaMH.equals("")) break try_case_1;
			    
			//check if exists
			if(new CheckExists(stmt).checkExists("MonHoc","MaMH='"+MaMH+"'")==1){
			    System.out.println("The record already exists");
			}else{
			    System.out.print("Ten mon hoc: ");
			    TenMH=scan.nextLine();
			    if(TenMH.equals("")) break try_case_1;



			    
			    //do{
			    //	try{
			    System.out.print("So tin chi: ");
			    SoTC=scan.nextInt();
			    scan.nextLine();
			    //	}catch(Exception e){
			    //scan.nextLine();//đọc xâu thừa mà ở kia do là nextInt nên ko đọc
			    //System.out.println("Error\n");
			    //System.out.print("Want to continue? (y): ");
			    //if(scan.nextLine().equals("y")
			    //}
			    //}while(1==1);



				    
			    sql="insert into MonHoc "
				+"values('"+MaMH+"','"+TenMH+"',"+SoTC+")";

			    try{
				stmt.executeUpdate(sql);
			    }catch(SQLException se){
				se.printStackTrace();
			    }
			}
			    
		    }catch(Exception e){
			e.printStackTrace();
		    }
				
		    System.out.print("\n### Type y to continue/n to exit: ");
		}while(scan.nextLine().equals("y"));
		break;
			    
	    case 2:
		System.out.println("Sửa thông tin môn học\n");

		do{
		    try_case_2:
		    try{
			System.out.print("\nMa mon hoc need to be updated: ");
			String UDMaMH=scan.nextLine();
			if(UDMaMH.equals("")) break try_case_2;
				
			//check if exists
			if(new CheckExists(stmt).checkExists("MonHoc","MaMH='"+UDMaMH+"'")==0){
			    System.out.println("The record doesn't exists\n");
			}else{//exitst
			    sql="select * from MonHoc where MaMH='"+UDMaMH+"'";
				
			    fieldsNameToPrint=new String[]{"MaMH","TenMH","SoTC"};
			    new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);

			    //start to update
			    System.out.print("\nUpdate ma mon hoc? (y/n): ");
			    if(scan.nextLine().equals("y")){
				System.out.print("Ma mon hoc: ");
				MaMH=scan.nextLine();
				if(MaMH.equals("")) break try_case_2;
					    
				if(new CheckExists(stmt).checkExists("MonHoc","MaMH='"+MaMH+"'")==1){
				    System.out.println("The record already exists\n");
				}else{//exitst
				    sql="update MonHoc set MaMH='"+MaMH+"'  where MaMH='"+UDMaMH+"'";//notice the space " where
				    try{
					stmt.executeUpdate(sql);
				    }catch(SQLException se){
					se.printStackTrace();
				    }
						
				    UDMaMH=MaMH;
				}
			    }
				    
			    System.out.print("\nUpdate ten mon hoc? (y/n): ");
			    if(scan.nextLine().equals("y")){
				System.out.print("Ten mon hoc: ");
				TenMH=scan.nextLine();
				if(TenMH.equals("")) break try_case_2;
					
				sql="update MonHoc set TenMH='"+TenMH+"' where MaMH='"+UDMaMH+"'";
				try{
				    stmt.executeUpdate(sql);
				}catch(SQLException se){
				    se.printStackTrace();
				}
			    }
				    
			    System.out.print("\nUpdate so tin chi? (y/n): ");



				    
			    if(scan.nextLine().equals("y")){
				System.out.print("So tin chi: ");
				SoTC=scan.nextInt();
				check=scan.nextLine();




					
				sql="update MonHoc set SoTC="+SoTC+" where MaMH='"+UDMaMH+"'";
				try{
				    stmt.executeUpdate(sql);
				}catch(SQLException se){
				    se.printStackTrace();scan.nextLine();
				}
			    }
				    
			    sql="select * from MonHoc where MaMH='"+UDMaMH+"'";
			    new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
			}
		    }catch(Exception e){
			e.printStackTrace();
		    }
				
		    System.out.print("\n### Type y to continue/n to exit: ");
		}while(scan.nextLine().equals("y"));
		break;
			    
			    
	    case 3:
		System.out.println("In danh sách môn học\n");
			    
		sql="select * from MonHoc";

		fieldsNameToPrint=new String[]{"MaMH","TenMH","SoTC"};
		new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
			     
		scan.nextLine();
		break;


	    case 4:
		System.out.println("Quit\n**************************************\n");
		break;
	    }
	}while(choice!=4);
    }
}
