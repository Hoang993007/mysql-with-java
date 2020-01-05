package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;
import sqlPerforment.*;

public class QuanLyDiem{
    
    private Statement stmt;
    public QuanLyDiem(Statement stmt){
	this.stmt=stmt;
    }
    
    public void exec() throws  SQLException, Exception{
	String sql=new String();
	String MaSV=new String();
	String MaLop=new String();
	String HoSV=new String();
	String TenSV=new String();
	String NgaySinh=new String();
	String NoiSinh=new String();
	int Diem;//must be double
	
	String clearInt=new String();
	
	String MaMH=new String();
	String TenMH=new String();
	
	String[] fieldsNameToPrint;
	String[] fieldsNamePrint;
	
	Scanner scan=new Scanner(System.in);
	String check=new String();
	
	System.out.println();
	int choice;
	do{
	    choice=new Menu().getChoice(Menu.menuQuanLyDiem);
	    switch(choice){
	    case 1:
	    case 2:
		if(choice==1)
		    System.out.println("Nhập điểm\n");
		else
		    System.out.println("Sửa điểm\n");
			
		String ISClass=new String();//INSERT CLASS
		System.out.println();
		do{
		    try_case_12:
		    try{
			System.out.print("Input the MaLop you want to input score: ");
			ISClass=scan.nextLine();
			if(ISClass.equals("")) break try_case_12;
			    
			//check if exists
			if(new CheckExists(stmt).checkExists("Lop","MaLop='"+ISClass+"'")==0)
			    System.out.println("The class doesn't exists");
			else {
			    System.out.println("\nClass: "+ISClass+":\n");

			    do {
				System.out.print("Ma sinh vien: ");
				MaSV=scan.nextLine();
				if(MaSV.equals("")) break try_case_12;
					
				//check if exists
				if(new CheckExists(stmt).checkExists("SinhVienLop","MaSV='"+MaSV+"' and "+"MaLop='"+ISClass+"'")==0)
				    System.out.println("\nThe SinhVien doesn't exists in this class");
				else  if(choice==1&&new CheckExists(stmt).checkExists("SinhVienLop","MaSV='"+MaSV+"' and "+"MaLop='"+ISClass+"' and Diem IS NULL")==0){
				    System.out.println("\nDiem had inputed\n");
				}else  if(choice==2&&new CheckExists(stmt).checkExists("SinhVienLop","MaSV='"+MaSV+"' and "+"MaLop='"+ISClass+"' and Diem IS NULL")==1){
				    System.out.println("\nDiem haven't inputed\n");
				}else{



				    //Fix like with SoTC
				    System.out.print("Diem: ");
				    Diem=scan.nextInt();
				    clearInt=scan.nextLine();


				    
						
				    sql="update SinhVienLop set Diem='"+Diem+"' where MaSV='"+MaSV+"' and MaLop='"+ISClass+"'";
				    try{
					stmt.executeUpdate(sql);
				    }catch(SQLException se){
					se.printStackTrace();scan.nextLine();
				    }
				}
				System.out.print("\nDo you want to continue insert score? (y/n): ");
			    }while(scan.nextLine().equals("y"));
			}
		    }catch(Exception e){
			e.printStackTrace();scan.nextLine();
		    }			    
		    System.out.print("\nDo you want to continue with new class? (y/n): ");
		}while(scan.nextLine().equals("y"));
		break;
			
			
	    case 3:
		System.out.println("In bảng điểm cho lớp\n");
		
	    try_case_3:
		try{	
		    System.out.print("Input the MaLop: ");
		    MaLop=scan.nextLine();
		    if(MaLop.equals("")) break try_case_3;
		    
		    if(new CheckExists(stmt).checkExists("Lop","MaLop='"+MaLop+"'")==0){
			System.out.println("The class doesn't exists");
			scan.nextLine();
		    }
		    else {
			System.out.println("\nClass: "+MaLop+":\n");
			    
			sql="select "
			    +"c.MaSV,c.HoSV,c.TenSV,c.NgaySinh,c.NoiSinh,b.Diem "
			    +"from "
			    +"Lop a inner join SinhVienLop b on a.MaLop=b.MaLop "
			    +"inner join SinhVien c on c.MaSV=b.MaSV "
			    +"where "
			    +"b.MaLop='"+MaLop+"'";
			    
			fieldsNameToPrint=new String[]{"c.MaSV","c.HoSV","c.TenSV","c.NgaySinh","c.NoiSinh","b.Diem"};
			fieldsNamePrint=new String[]{"MaSV","HoSV","TenSV","NgaySinh","NoiSinh","Diem"};
			new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNamePrint);
			    
			scan.nextLine();
		    }
		}catch(Exception e){
		    e.printStackTrace();
		    scan.nextLine();
		}
		break;
			
			
	    case 4:
		System.out.println("In bảng điểm cho sinh viên\n");
	    try_case_4:
		try{
		    //lặp lại cho sinh viên khác nữa
			
		    System.out.print("Input the MaSV: ");
		    MaSV=scan.nextLine();
		    if(MaSV.equals("")) break try_case_4;
		    
		    if(new CheckExists(stmt).checkExists("SinhVien","MaSV='"+MaSV+"'")==0){
			System.out.println("This student doesn't exists");
			scan.nextLine();
		    }else {
			System.out.println("\nMaSV: "+MaSV+":\n");
			    
			sql="select "
			    +"a.MaLop,a.MaMH,d.TenMH,b.Diem "
			    +"from "
			    +"Lop a inner join SinhVienLop b on a.MaLop=b.MaLop "
			    +"inner join SinhVien c on c.MaSV=b.MaSV "
			    +"inner join MonHoc d on a.MaMH=d.MaMH "
			    +"where b.MaSV='"+MaSV+"'";
			    
			fieldsNameToPrint=new String[]{"a.MaLop","a.MaMH","d.TenMH","b.Diem"};
			fieldsNamePrint=new String[]{"MaLop","MaMH","TenMH","Diem"};
			new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNamePrint);
			    
			scan.nextLine();
		    }
		}catch(Exception e){
		    e.printStackTrace();scan.nextLine();
		}
		break;
			
			
	    case 5:
		System.out.println("Quit\n**************************************\n");
		break;
	    }
	}while(choice!=5);
    }
    
}
