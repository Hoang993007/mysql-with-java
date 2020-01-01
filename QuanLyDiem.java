package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;

public class QuanLyDiem{
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
	    String MaSV=new String();
	    String MaLop=new String();
	    String HoSV=new String();
	    String TenSV=new String();
	    String NgaySinh=new String();
	    String NoiSinh=new String();
	    int Diem;

	    String MaMH=new String();
	    String TenMH=new String();
	    
	    Scanner scan=new Scanner(System.in);
	    String check=new String();

	    System.out.println();
	    int choice;
	    do
		{
		    choice=new Menu().getChoice(Menu.menuQuanLyDiem);
		    switch(choice)
			{
			case 1:
			case 2:
			    if(choice==1)
				System.out.println("Nhập điểm\n");
			    else
				System.out.println("Sửa điểm\n");
			    
			    String ISClass=new String();//INSERT CLASS
			    do{
				System.out.print("Input the MaLop you want to input score: ");
				ISClass=scan.nextLine();
				
				//check if exists
				if(new CheckExists().checkExists("Lop","MaLop",ISClass)==0){
				    System.out.println("The class doesn't exists");
				}
				else {
				    System.out.println("\nClass: "+ISClass+":\n");
				    String check2=new String();
				    do
					{
					    System.out.print("Ma sinh vien: ");
					    MaSV=scan.nextLine();
					    
					    //check if exists
					    if(new CheckExists().checkExists("SinhVienLop","MaSV",MaSV)==0){
						System.out.println("\nThe SinhVien doesn't exists in this class");
					    }
					    else
						{
						    System.out.print("Diem: ");
						    Diem=scan.nextInt();
						    
						    sql="update SinhVienLop set Diem='"+Diem+"' where MaSV='"+MaSV+"' and MaLop='"+ISClass+"'";
						    stmt.executeUpdate(sql);
						}
					    System.out.print("Do you want to continue insert score? (Type 'y' or 'n'): ");
					    check2=scan.nextLine();
					}while(check2.equals("y"));
				}
				
				System.out.print("\nDo you want to continue with new class? (Type 'y' or 'n'): ");
				check=scan.nextLine();
				System.out.println();
			    }while(check.equals("y"));
			    break;
			    
			case 3:
			    System.out.println("In bảng điểm cho lớp\n");
			    
			    System.out.print("Input the MaLop: ");
			    MaLop=scan.nextLine();
			    if(new CheckExists().checkExists("Lop","MaLop",MaLop)==0){
				System.out.println("The class doesn't exists");
			    }
			    else {
				System.out.println("\nClass: "+MaLop+":\n");
				
				sql="select "
				    +"c.MaSV,c.HoSV,c.TenSV,c.NgaySinh,c.NoiSinh,b.Diem "
				    +"from "
				    +"Lop a inner join SinhVienLop b on a.MaLop=b.MaLop "
				    +"inner join SinhVien c on c.MaSV=b.MaSV"
				    +"where "
				    +"b.MaLop='"+MaLop+"'";
				ResultSet rs=stmt.executeQuery(sql);
				
				//Extract data from result set
				while(rs.next()){
				    //Retrieve by column name
				    System.out.print("MaSV: "+rs.getString("MaSV"));
				    System.out.print(", HoSV: "+rs.getString("HoSV"));
				    System.out.print(", TenSV: "+rs.getString("TenSV"));
				    System.out.print(", NgaySinh: "+rs.getString("NgaySinh"));
				    System.out.print("NoiSinh: "+rs.getString("NoiSinh"));
				    System.out.println(", Diem: "+rs.getInt("Diem"));
				}
				rs.close();
			    }
			    break;
			case 4:
			    System.out.println("In bảng điểm cho sinh viên\n");
			    
			    System.out.print("Input the MaSV: ");
			    MaSV=scan.nextLine();
			    if(new CheckExists().checkExists("SinhVien","MaSV",MaSV)==0){
				System.out.println("This student doesn't exists");
			    }
			    else {
				System.out.println("\nMaSV: "+MaSV+":\n");
				
				sql="select "
				    +"a.MaLop,a.MaMH,d.TenMH,b.Diem "
				    +"from "
				    +"Lop a inner join SinhVienLop b on a.MaLop=b.MaLop "
				    +"inner join SinhVien c on c.MaSV=b.MaSV"
				    +"inner join MonHoc d on a.MaMH=d.MaMH"
				    +"where b.MaSV='"+MaSV+"'";
				ResultSet rs=stmt.executeQuery(sql);
				
				//STEP5: Extract data from result set
				while(rs.next()){
				    //Retrieve by column name
				    System.out.print("MaLop: "+rs.getString("MaLop"));
				    System.out.print(", MaMH: "+rs.getString("MaMH"));
				    System.out.print(", TenMH: "+rs.getString("TenMH"));
				    System.out.println(", Diem: "+rs.getInt("Diem"));
				}
				rs.close();
			    }
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
