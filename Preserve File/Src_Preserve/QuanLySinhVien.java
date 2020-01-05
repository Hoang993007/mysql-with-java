package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;
import sqlPerforment.*;

public class QuanLySinhVien{

    Statement stmt;
    public QuanLySinhVien(Statement stmt){
	this.stmt=stmt;
    }
    
    public void exec() throws  SQLException, Exception{
	String sql=new String();
	String MaSV=new String();
	String HoSV=new String();
	String TenSV=new String();
	String NgaySinh=new String();
	String NoiSinh=new String();
	String[] fieldsNameToPrint;

	int count;
	    
	String check=new String();
	Scanner scan=new Scanner(System.in);

	System.out.println();
	int choice;
	do{
	    choice=new Menu().getChoice(Menu.menuQuanLySinhVien);
	    
	    switch(choice) {
	    case 1:
		System.out.println("Thêm hồ sơ sinh viên");
			    
		do{
		    try_case_1:
		    try{
			System.out.print("\nMa sinh vien: ");
			MaSV=scan.nextLine();
			if(MaSV.equals("")) break try_case_1;
				
			//check if exists
			if(new CheckExists(stmt).checkExists("SinhVien","MaSV='"+MaSV+"'")==1){
			    System.out.println("\nThe record already exists");
			}else{
			    System.out.print("Ho sinh vien: ");
			    HoSV=scan.nextLine();
			    if(HoSV.equals("")) break try_case_1;
					
			    System.out.print("Ten sinh vien: ");
			    TenSV=scan.nextLine();
			    if(TenSV.equals("")) break try_case_1;
					
			    System.out.print("Ngay sinh (yyyy-mm-dd): ");
			    NgaySinh=scan.nextLine()
				;if(NgaySinh.equals("")) break try_case_1;
					
			    System.out.print("Noi sinh: ");
			    NoiSinh=scan.nextLine();
			    if(NoiSinh.equals("")) break try_case_1;
					
			    sql="insert into SinhVien "
				+"values('"+MaSV+"','"+HoSV+"','"+TenSV+"','"+NgaySinh+"','"+NoiSinh+"')";
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
		System.out.println("\nSửa thông tin sinh viên\n");
			    
		do{
		    try_case_2:
		    try{
			System.out.print("\nMa sinh vien need to be updated: ");
			String UDMaSV=scan.nextLine();
			if(UDMaSV.equals("")) break try_case_2;
				
			//check if exists
			if(new CheckExists(stmt).checkExists("SinhVien","MaSV='"+UDMaSV+"'")==0){
			    System.out.println("The record doen't exitst\n");
			}else{//exitst
			    sql="select * from SinhVien where MaSV='"+UDMaSV+"'";
				
			    fieldsNameToPrint=new String[]{"MaSV","TenSV","HoSV","NgaySinh","NoiSinh"};
			    new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
			    
			    //start to update
			    System.out.print("\nUpdate ma sinh vien? (y/n): ");
			    if(scan.nextLine().equals("y")){
				System.out.print("Ma sinh vien: ");
				MaSV=scan.nextLine();
				if(MaSV.equals("")) break try_case_2;
					    
				if(new CheckExists(stmt).checkExists("SinhVien","MaSV='"+MaSV+"'")==1)
				    System.out.println("The record already exists\n");
				else{//exitst
				    sql="update SinhVien set MaSV='"+MaSV+"'  where MaSV='"+UDMaSV+"'";//notice the space " where
				    try{
					stmt.executeUpdate(sql);
				    }catch(SQLException se){
					se.printStackTrace();
				    }
				    UDMaSV=MaSV;
				}
			    }
				    
			    System.out.print("\nUpdate ho sinh vien? (y/n): ");
			    if(scan.nextLine().equals("y")){
				System.out.print("Ho sinh vien: ");
				HoSV=scan.nextLine();
				if(HoSV.equals("")) break try_case_2;
				    
				sql="update SinhVien set HoSV='"+HoSV+"' where MaSV='"+UDMaSV+"'";
				try{
				    stmt.executeUpdate(sql);
				}catch(SQLException se){
				    se.printStackTrace();
				}
			    }
				    
			    System.out.print("\nUpdate ten sinh vien? (y/n): ");
			    if(scan.nextLine().equals("y")){
				System.out.print("Ten sinh vien: ");
				TenSV=scan.nextLine();
				if(TenSV.equals("")) break try_case_2;
				    
				sql="update SinhVien set TenSV='"+TenSV+"' where MaSV='"+UDMaSV+"'";
				try{
				    stmt.executeUpdate(sql);
				}catch(SQLException se){
				    se.printStackTrace();
				}
			    }
				    
			    System.out.print("\nUpdate ngay sinh? (y/n): ");
			    if(scan.nextLine().equals("y"))
				{
				    System.out.print("Ngay sinh: ");
				    NgaySinh=scan.nextLine();
				    if(NgaySinh.equals("")) break try_case_2;
				    
				    sql="update SinhVien set NgaySinh='"+NgaySinh+"' where MaSV='"+UDMaSV+"'";
				    try{
					stmt.executeUpdate(sql);
				    }catch(SQLException se){
					se.printStackTrace();
				    }
				}
				    
			    System.out.print("\nUpdate noi sinh? (y/n): ");
			    if(scan.nextLine().equals("y")){
				System.out.print("Noi sinh: ");
				NoiSinh=scan.nextLine();
				if(NoiSinh.equals("")) break try_case_2;
				    
				sql="update SinhVien set NoiSinh='"+NoiSinh+"' where MaSV='"+UDMaSV+"'";
				try{
				    stmt.executeUpdate(sql);
				}catch(SQLException se){
				    se.printStackTrace();scan.nextLine();
				}
			    }
				    
			    sql="select * from SinhVien where MaSV='"+UDMaSV+"'";
			    new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
			}
		    }catch(Exception e){
			e.printStackTrace();
		    }
			    
		    System.out.print("\n### Type y to continue/n to exit: ");
		}while(scan.nextLine().equals("y"));
		break;
		    
		    
	    case 3:
		System.out.println("Tìm kiếm sinh viên");

		do{
		    try_case_3:
		    try{
			sql="select * from SinhVien where ";
			String sql1=sql;

			count=0;
				
			System.out.print("\nUse Ma sinh vien to search? (y/n): ");
			if(scan.nextLine().equals("y")){
			    System.out.print("Ma sinh vien: ");
			    MaSV=scan.nextLine();
			    if(MaSV.equals("")) break try_case_3;
				
			    count=count+1;
			    sql=sql+"MaSV like '"+MaSV+"' ";
			}
				
			System.out.print("\nUse Ten sinh vien to search? (y/n): ");
			if(scan.nextLine().equals("y")){
			    System.out.print("Ten sinh vien: ");
			    TenSV=scan.nextLine();
			    if(TenSV.equals("")) break try_case_3;
				
			    if(count>=1) sql=sql+"and "; else count=count+1;
			    sql=sql+"TenSV like '"+TenSV+"' ";
			}
				
			System.out.print("\nUse Ho sinh vien to search? (y/n): ");
			if(scan.nextLine().equals("y")){
			    System.out.print("Ho sinh vien: ");
			    HoSV=scan.nextLine();
			    if(HoSV.equals("")) break try_case_3;
				
			    if(count>=1) sql=sql+"and "; else count=count+1;
			    sql=sql+"HoSV like '"+HoSV+"' ";
			}
				
			System.out.print("\nUse ngay sinh to search? (y/n): ");
			if(scan.nextLine().equals("y")){
			    System.out.print("Ngay sinh: ");
			    NgaySinh=scan.nextLine();
			    if(NgaySinh.equals("")) break try_case_3;
				
			    if(count>=1) sql=sql+"and "; else count=count+1;
			    sql=sql+"NgaySinh='"+NgaySinh+"' ";
			}
				
			System.out.print("\nUse noi sinh to search? (y/n): ");
			if(scan.nextLine().equals("y")){
			    System.out.print("Noi sinh: ");
			    NoiSinh=scan.nextLine();
			    if(NoiSinh.equals("")) break try_case_3;
				
			    if(count>=1) sql=sql+"and "; else count=count+1;
			    sql=sql+"NoiSinh like '"+NoiSinh+"' ";
			}

			int checkPrint=0;
			if(!sql.equals(sql1)){
			    fieldsNameToPrint=new String[]{"MaGV","TenGV","HoGV","DonVi"};
			    checkPrint=new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
			}
			
			if(checkPrint==0){
			    System.out.println("*****************\nNo data\n*****************");
			}
		    }catch(Exception e){
			e.printStackTrace();
		    }
			    
		    System.out.print("\n### Type y to continue/n to exit: ");
		}while(scan.nextLine().equals("y"));
		break;


	    case 4:
		System.out.println("Quit\n**************************************\n");
		break;
	    }
	}while(choice!=4);
    }
}
