package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;
import sqlPerforment.*;

public class QuanLyGiaoVien{

    //ĐÈN BÁO
    private static int light_themHoSoGiaoVien=0;
    private static int light_suaThongTinGiaoVien=0;
    private static int light_inDanhSachGiaoVien=0;
    private static int light_timKiemGiaoVien=0;
    
    public void QuanLyGiaoVienChooseAction(Statement stmt) throws  SQLException, Exception{
	System.out.println();
	int choice;
	do{
	    choice=new Menu().getChoice(Menu.menuQuanLyGiaoVien);
	    switch(choice){
	    case 1:
		if(light_themHoSoGiaoVien==0){
		    light_themHoSoGiaoVien=1;
		    themHoSoGiaoVien(stmt);
		    light_themHoSoGiaoVien=0;
		}
		break;   
			    
	    case 2:
		if(light_suaThongTinGiaoVien==0){
		    light_suaThongTinGiaoVien=1;
		    suaThongTinGiaoVien(stmt);
		    light_suaThongTinGiaoVien=0;
		}
		break;
			    
	    case 3:
		if(light_inDanhSachGiaoVien==0){
		    light_inDanhSachGiaoVien=1;
		    inDanhSachGiaoVien(stmt);
		    light_inDanhSachGiaoVien=0;
		}
		break;
			   
	    case 4:
		if(light_timKiemGiaoVien==0){
		    light_timKiemGiaoVien=1;
		    timKiemGiaoVien(stmt);
		    light_timKiemGiaoVien=0;
		}
		break;

	    case 5:
		System.out.println("Quit\n**************************************\n");
		break;
	    }
	}while(choice!=5);
    }

    private static void themHoSoGiaoVien(Statement stmt) throws  SQLException, Exception{
	System.out.println("Thêm hồ sơ giáo viên\n");

	String sql=new String(); 
	String MaGV=new String();
	String HoGV=new String();
	String TenGV=new String();
	String DonVi=new String();

	Scanner scan=new Scanner(System.in);
    
	do{
	    try_case_1:
	    try{
		System.out.print("Ma giao vien: ");
		MaGV=scan.nextLine();
		if(MaGV.equals("")) break try_case_1;
				
		//check if exists
		if(new CheckExists(stmt).checkExists("GiaoVien","MaGV='"+MaGV+"'")==1){
		    System.out.println("\nThe record already exists");
		}else{
		    System.out.print("Ho giao vien: ");
		    HoGV=scan.nextLine();
		    if(HoGV.equals("")) break try_case_1;
					
		    System.out.print("Ten giao vien: ");
		    TenGV=scan.nextLine();
		    if(TenGV.equals("")) break try_case_1;
					
		    System.out.print("Don vi: ");
		    DonVi=scan.nextLine();
		    if(DonVi.equals("")) break try_case_1;
					
		    sql="insert into GiaoVien "
			+"values('"+MaGV+"','"+HoGV+"','"+TenGV+"','"+DonVi+"')";
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
    }

    private static void suaThongTinGiaoVien(Statement stmt) throws  SQLException, Exception{
	System.out.println("\nSửa thông tin giáo viên\n");

	String sql=new String(); 
	String MaGV=new String();
	String HoGV=new String();
	String TenGV=new String();
	String DonVi=new String();

	String[] fieldsNameToPrint;

	Scanner scan=new Scanner(System.in);
	    
	do{
	    try_case_2:
	    try{
		System.out.print("Ma giao vien need to be updated: ");
		String UDMaGV=scan.nextLine();
		if(UDMaGV.equals("")) break try_case_2;
				
		//check if exists
		if(new CheckExists(stmt).checkExists("GiaoVien","MaGV='"+UDMaGV+"'")==0){
		    System.out.println("The record doesn't exists\n");
		}else{//exitst
		    sql="select * from GiaoVien where MaGV='"+UDMaGV+"'";
				
		    fieldsNameToPrint=new String[]{"MaGV","TenGV","HoGV","DonVi"};
		    new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
				
		    //start to update
		    System.out.print("\nUpdate ma giao vien? (y/n): ");
		    if(scan.nextLine().equals("y")){
			System.out.print("Ma giao vien: ");
			MaGV=scan.nextLine();
			if(MaGV.equals("")) break try_case_2;
				    
			if(new CheckExists(stmt).checkExists("GiaoVien","MaGV='"+MaGV+"'")==1){
			    System.out.println("The record already exists\n");
			}else{
			    sql="update GiaoVien set MaGV='"+MaGV+"'  where MaGV='"+UDMaGV+"'";//notice the space " where
			    try{
				stmt.executeUpdate(sql);
			    }catch(SQLException se){
				se.printStackTrace();
			    }
			    UDMaGV=MaGV;
			}
		    }
				    
		    System.out.print("\nUpdate ho giao vien? (y/n): ");
		    if(scan.nextLine().equals("y")){
			System.out.print("Ho giao vien: ");
			HoGV=scan.nextLine();
			if(HoGV.equals("")) break try_case_2;
					    
			sql="update GiaoVien set HoGV='"+HoGV+"' where MaGV='"+UDMaGV+"'";
			try{
			    stmt.executeUpdate(sql);
			}catch(SQLException se){
			    se.printStackTrace();
			}
		    }
				    
		    System.out.print("\nUpdate ten giao vien? (y/n): ");
		    if(scan.nextLine().equals("y")){
			System.out.print("Ten giao vien: ");
			TenGV=scan.nextLine();
			if(TenGV.equals("")) break try_case_2;
					
			sql="update GiaoVien set TenGV='"+TenGV+"' where MaGV='"+UDMaGV+"'";
			try{
			    stmt.executeUpdate(sql);
			}catch(SQLException se){
			    se.printStackTrace();
			}
		    }
				    
		    System.out.print("\nUpdate don vi? (y/n): ");
		    if(scan.nextLine().equals("y")){
			System.out.print("Don vi: ");
			DonVi=scan.nextLine();
			if(DonVi.equals("")) break try_case_2;
					
			sql="update GiaoVien set DonVi='"+DonVi+"' where MaGV='"+UDMaGV+"'";
			try{
			    stmt.executeUpdate(sql);
			}catch(SQLException se){
			    se.printStackTrace();
			}
		    }
				    
		    sql="select * from GiaoVien where MaGV='"+UDMaGV+"'";
		    new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
		}
	    }catch(Exception e){
		e.printStackTrace();
	    }
				
	    System.out.print("\n### Type y to continue/n to exit: ");
	}while(scan.nextLine().equals("y"));	
    }

    private static void inDanhSachGiaoVien(Statement stmt) throws  SQLException, Exception{
	System.out.println("In danh sách giáo viên\n");

	String sql=new String(); 

	String[] fieldsNameToPrint;

	Scanner scan=new Scanner(System.in);
  
	sql="select * from GiaoVien";

	fieldsNameToPrint=new String[]{"MaGV","TenGV","HoGV","DonVi"};
	new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);

	scan.nextLine();
    }

    private static void timKiemGiaoVien(Statement stmt) throws  SQLException, Exception{
	System.out.println("Tìm kiếm giáo viên\n");

	String sql=new String(); 
	String MaGV=new String();
	String HoGV=new String();
	String TenGV=new String();
	String DonVi=new String();

	String[] fieldsNameToPrint;

	int count;

	Scanner scan=new Scanner(System.in);

	do{
	    try_case_4:
	    try{
		String sql1;
		sql="select * from GiaoVien where ";
		sql1=sql;
		count=0;
				
		System.out.print("\nUse Ma giao vien to search? (y/n): ");
		if(scan.nextLine().equals("y")){
		    System.out.print("Ma giao vien: ");
		    MaGV=scan.nextLine();
		    if(MaGV.equals("")) break try_case_4;
				    
		    count=count+1;
		    sql=sql+"MaGV like '"+MaGV+"' ";
		}
				
		System.out.print("\nUse Ten giao vien to search? (y/n): ");
		if(scan.nextLine().equals("y")){
		    System.out.print("Ten giao vien: ");
		    TenGV=scan.nextLine();
		    if(TenGV.equals("")) break try_case_4;
				    
		    if(count>=1) sql=sql+"and "; else count=count+1;
		    sql=sql+"TenGV like '"+TenGV+"' ";
		}
				
		System.out.print("\nUse Ho giao vien to search? (y/n): ");
		if(scan.nextLine().equals("y")){
		    System.out.print("Ho giao vien: ");
		    HoGV=scan.nextLine();
		    if(HoGV.equals("")) break try_case_4;
				    
		    if(count>=1) sql=sql+"and "; else count=count+1;
		    sql=sql+"HoGV like '"+HoGV+"' ";
		}
				
		System.out.print("\nUse Don vi to search? (y/n): ");
		if(scan.nextLine().equals("y")){
		    System.out.print("Don vi: ");
		    DonVi=scan.nextLine();
		    if(DonVi.equals("")) break try_case_4;
				    
		    if(count>=1) sql=sql+"and "; else count=count+1;
		    sql=sql+"DonVi like '"+DonVi+"' ";
		}

		int checkPrint=0;
		if(!sql.equals(sql1)){
		    fieldsNameToPrint=new String[]{"MaGV","TenGV","HoGV","DonVi"};
		    checkPrint=new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
		}
				
		if(checkPrint==0){
		    System.out.println("\n*****************\nNo data\n*****************");
		}
	    }catch(Exception e){
		e.printStackTrace();scan.nextLine();
	    }
	    System.out.print("\n### Type y to continue/n to exit: ");
	}while(scan.nextLine().equals("y"));
    }
}
