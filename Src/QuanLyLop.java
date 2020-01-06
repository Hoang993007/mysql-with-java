package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;
import sqlPerforment.*;
import getInput.*;

public class QuanLyLop{

    Statement stmt;
    public QuanLyLop(Statement stmt){
	this.stmt=stmt;
    }
    
    public void exec() throws  SQLException, Exception{
	String sql=new String();
	String MaLop=new String();
	String MaMH=new String();
	String NamHoc=new String();
	int HocKy;
	String MaGV=new String();
	String MaSV=new String();
	    
	String check=new String();
	Scanner scan=new Scanner(System.in);

	String[] fieldsNameToPrint;

	System.out.println();
	int choice;
	do{
	    choice=new Menu().getChoice(Menu.menuQuanLyLop);
	    switch(choice){
	    case 1:
		System.out.println("Tạo lớp mới\n");
			    
		do{
		    try_case_1:
		    try{
			System.out.print("Ma lop: ");
			MaLop=scan.nextLine();
			if(MaLop.equals("")) break try_case_1;
				
			if(new CheckExists(stmt).checkExists("Lop","MaLop='"+MaLop+"'")==1){
			    System.out.println("\nThe record already exists");
			}else{
			    System.out.print("Ma mon hoc: ");
			    MaMH=scan.nextLine();
			    if(MaMH.equals("")) break try_case_1;
				    
			    //check if exists
			    if(new CheckExists(stmt).checkExists("MonHoc","MaMH='"+MaMH+"'")==0){
				System.out.println("Mon Hoc doesn't exists");
			    }else {
				System.out.print("Ma giao vien: ");
				MaGV=scan.nextLine();
				if(MaGV.equals("")) break try_case_1;
					
				//check if exists
				if(new CheckExists(stmt).checkExists("GiaoVien","MaGV='"+MaGV+"'")==0){
				    System.out.println("\nThe teacher doesn't exits");	
				}else {
				    System.out.print("Nam hoc (xxxx-xxxx): ");
				    NamHoc=scan.nextLine();
				    if(NamHoc.equals("")) break try_case_1;

				    System.out.print("Hoc ky: ");
				    GetInput getHocKy=new GetInput();
				    getHocKy.getInt();
				    if(getHocKy.getError()==1) break try_case_1;
				    else HocKy=getHocKy.getIntValue();
				    
				    sql="insert into Lop "
					+"values('"+MaLop+"','"+MaMH+"','"+NamHoc+"',"+HocKy+",'"+MaGV+"')";
				    try{
					stmt.executeUpdate(sql);
				    }catch(SQLException se){
					se.printStackTrace();
				    }
				}
			    }
			}
		    }catch(Exception e){
			e.printStackTrace();
		    }
		    System.out.print("\n### Type y to continue/n to exit: ");
		}while(scan.nextLine().equals("y"));
		break;
			    
	    case 2:
		System.out.println("Sửa đổi thông tin lớp\n");
			    
		do{
		    try_case_2:
		    try{
			System.out.print("Ma lop need to be updated: ");
			String UDMaLop=scan.nextLine();
			if(UDMaLop.equals("")) break try_case_2;
				
			//check if exists
			if(new CheckExists(stmt).checkExists("Lop","MaLop='"+UDMaLop+"'")==0){
			    System.out.println("The record doesn't exitst\n");
			}else{//exitst
			    sql="select * from Lop where MaLop='"+UDMaLop+"'";
				
			    fieldsNameToPrint=new String[]{"MaLop","MaMH","NamHoc","HocKy","MaGV"};
			    new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);

			    //start to update
			    System.out.print("\nUpdate ma lop? (y/n): ");
			    if(scan.nextLine().equals("y")){
				System.out.print("Ma lop: ");
				MaLop=scan.nextLine();
				if(MaLop.equals("")) break try_case_2;
					    
				if(new CheckExists(stmt).checkExists("Lop","MaLop='"+MaLop+"'")==1){
				    System.out.println("The record already exists\n");
				}else{//exitst
				    sql="update Lop set MaLop='"+MaLop+"'  where MaLop='"+UDMaLop+"'";//notice the space " where
				    try{
					stmt.executeUpdate(sql);
				    }catch(SQLException se){
					se.printStackTrace();
				    }
				    UDMaLop=MaLop;
				}
			    }
				    
			    System.out.print("\nUpdate ma mon hoc? (y/n): ");
			    if(scan.nextLine().equals("y")){
				System.out.print("Ma mon hoc: ");
				MaMH=scan.nextLine();
				if(MaMH.equals("")) break try_case_2;
					    
				if(new CheckExists(stmt).checkExists("MonHoc","MaMH='"+MaMH+"'")==0){
				    System.out.println("Mon Hoc doesn't exists");
				}else {
				    sql="update Lop set MaMH='"+MaMH+"' where MaLop='"+UDMaLop+"'";
				    try{
					stmt.executeUpdate(sql);
				    }catch(SQLException se){
					se.printStackTrace();
				    }
				}
			    }
				    
			    System.out.print("\nUpdate nam hoc? (y/n): ");
			    if(scan.nextLine().equals("y")){
				System.out.print("Nam hoc (xxxx-xxxx): ");
				NamHoc=scan.nextLine();
				if(NamHoc.equals("")) break try_case_2;
					    
				sql="update Lop set NamHoc='"+NamHoc+"' where MaLop='"+UDMaLop+"'";
				try{
				    stmt.executeUpdate(sql);
				}catch(SQLException se){
				    se.printStackTrace();
				}
			    }
				    
			    System.out.print("\nUpdate hoc ky? (y/n): ");
			    if(scan.nextLine().equals("y")){

				System.out.print("Hoc ky: ");
				GetInput getHocKy=new GetInput();
				getHocKy.getInt();
				if(getHocKy.getError()==1) break try_case_2;
				else HocKy=getHocKy.getIntValue();
				
				sql="update Lop set HocKy="+HocKy+" where MaLop='"+UDMaLop+"'";
				try{
				    stmt.executeUpdate(sql);
				}catch(SQLException se){
				    se.printStackTrace();
				}
			    }
				    
			    System.out.print("\nUpdate giao vien? (y/n): ");
			    if(scan.nextLine().equals("y")){
				System.out.print("Ma giao vien: ");
				MaGV=scan.nextLine();
				if(MaGV.equals("")) break try_case_2;
					    
				if(new CheckExists(stmt).checkExists("GiaoVien","MaGV='"+MaGV+"'")==0){
				    System.out.println("\nThe teacher doesn't exits");	
				}else {
				    sql="update Lop set MaGV='"+MaGV+"'  where MaLop='"+UDMaLop+"'";//notice the space " where
				    try{
					stmt.executeUpdate(sql);
				    }catch(SQLException se){
					se.printStackTrace();
				    }
				}
			    }
				    
			    sql="select * from Lop where MaLop='"+UDMaLop+"'";
			    new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
			}
		    }catch(Exception e){
			e.printStackTrace();scan.nextLine();
		    }
		    System.out.print("\n### Type y to continue/n to exit: ");
		}while(scan.nextLine().equals("y"));
		break;
			    
	    case 3:
		System.out.println("\nBổ sung sinh viên vào lớp\n");
			    
		String ISClass=new String();//INSERT CLASS
		do{
		    try_case_3:
		    try{
			System.out.print("\nInput the MaLop you want to add student to: ");
			ISClass=scan.nextLine();
			if(ISClass.equals("")) break try_case_3;
				
			//check if exists
			if(new CheckExists(stmt).checkExists("Lop","MaLop='"+ISClass+"'")==0){
			    System.out.println("The class doesn't exists");
			}
			else {
			    System.out.println("\nClass: "+ISClass+":\n");
			    do{
				System.out.print("\nMa sinh vien: ");
				MaSV=scan.nextLine();
				if(MaSV.equals("")) break try_case_3;
					    
				//check if exists
				if(new CheckExists(stmt).checkExists("SinhVien","MaSV='"+MaSV+"'")==0){
				    System.out.println("The SinhVien doesn't exists");
				}else{
				    //check if exists
				    if(new CheckExists(stmt).checkExists("SinhVienLop","MaSV='"+MaSV+"' and "+"MaLop='"+ISClass+"'")==1){
					System.out.println("\nThe record already exists");
				    }else{
					sql="insert into SinhVienLop (MaSV,MaLop)"
					    +"values('"+MaSV+"','"+ISClass+"')";
					try{
					    stmt.executeUpdate(sql);
					}catch(SQLException se){
					    se.printStackTrace();
					}
				    }
				}
					    
				System.out.print("\nDo you want to continue insert studen? (y/n): ");
			    }while(scan.nextLine().equals("y"));
			}
		    }catch(Exception e){
			e.printStackTrace();scan.nextLine();
		    }
		    System.out.print("\nDo you want to continue with new class? (y/n): ");
		}while(scan.nextLine().equals("y"));
		break;
			    
	    case 4:
		System.out.println("Loại bỏ sinh viên khỏi lớp\n");
			    
		String DLClass=new String();//DELETE CLASS
		do{
		    try_case_4:
		    try{
			System.out.print("\nInput the MaLop you want to delete student from: ");
			DLClass=scan.nextLine();
			if(DLClass.equals("")) break try_case_4;
				
			//check if exists
			if(new CheckExists(stmt).checkExists("Lop","MaLop='"+DLClass+"'")==0){
			    System.out.println("The class doesn't exists");
			}else {
			    System.out.println("\nClass: "+DLClass+":\n");
			    do{
				System.out.print("\nMa sinh vien want to delete: ");
				MaSV=scan.nextLine();
				if(MaSV.equals("")) break try_case_4;
					    
				//check if exists
				if(new CheckExists(stmt).checkExists("SinhVienLop","MaSV='"+MaSV+"' and "+"MaLop='"+DLClass+"'")==0){
				    System.out.println("\nThe SinhVien doesn't exists in this class");
				}else{
				    sql="delete from SinhVienLop where MaSV='"+MaSV+"' and "+"MaLop='"+MaLop+"'";
				    try{
					stmt.executeUpdate(sql);
				    }catch(SQLException se){
					se.printStackTrace();
				    }
				}
					    
				System.out.print("\nDo you want to continue delete studen from this class? (y/n): ");
			    }while(scan.nextLine().equals("y"));
			}
		    }catch(Exception e){
			e.printStackTrace();
		    }
			    
		    System.out.print("\nDo you want to continue with new class? (y/n): ");
		}while(scan.nextLine().equals("y"));
		break;
			    
	    case 5:
		System.out.println("Hủy lớp\n");
			    
		do{
		    try_case_5:
		    try{
			System.out.print("\nInput the MaLop you want to delete: ");
			MaLop=scan.nextLine();
			if(MaLop.equals("")) break try_case_5;
				
			//check if exists
			if(new CheckExists(stmt).checkExists("Lop","MaLop='"+MaLop+"'")==0){
			    System.out.println("The class doesn't exists");
			}else {    
			    sql="delete from Lop where MaLop='"+MaLop+"'";
			    try{
				stmt.executeUpdate(sql);
			    }catch(SQLException se){
				se.printStackTrace();
			    }	   
			}
		    }catch(Exception e){
			e.printStackTrace();
		    }
			    
		    System.out.print("\nDo you want to continue with new class? (y/n): ");
		}while(scan.nextLine().equals("y"));
		break;
			    
	    case 6:
		System.out.println("In danh sách lớp\n");
			
		sql="select * from Lop";
			    
		fieldsNameToPrint=new String[]{"MaLop","MaMH","NamHoc","HocKy","MaGV"};
		new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
		scan.nextLine();
		break;


	    case 7:
		System.out.println("Quit\n**************************************\n");
		break;
	    }
	}while(choice!=7);
    }
}
