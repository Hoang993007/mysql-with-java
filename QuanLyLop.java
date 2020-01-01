package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;


//HAVE NOT TESTED YET




public class QuanLyLop{
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
	    String MaLop=new String();
            String MaMH=new String();
	    String NamHoc=new String();
	    int HocKy;
	    String MaGV=new String();
	    String MaSV=new String();
	    
	    String check=new String();
	    Scanner scan=new Scanner(System.in);

	    System.out.println();
	    int choice;
	    do
		{
		    choice=new Menu().getChoice(Menu.menuQuanLyLop);
		    switch(choice)
			{
			case 1:
			    System.out.println("Tạo lớp mới");
			    System.out.println();
			    
			    do{
				System.out.print("Ma lop: ");
				MaLop=scan.nextLine();
				
				if(new CheckExists().checkExists("Lop","MaLop",MaLop)==1){
				    System.out.println();
				    System.out.println("The record already exists");
				}else{
				    System.out.print("Ma mon hoc: ");
				    MaMH=scan.nextLine();
				    
				    //check if exists
				    if(new CheckExists().checkExists("MonHoc","MaMH",MaMH)==0){
					System.out.println("Mon Hoc doesn't exists");
				    }
				    else {
					System.out.print("Ma giao vien: ");
					MaGV=scan.nextLine();
					
					//check if exists
					if(new CheckExists().checkExists("GiaoVien","MaGV",MaGV)==0){
					    System.out.println();
					    System.out.println("The teacher doesn't exits");	
					}
					else {
					    System.out.print("Nam hoc (xxxx-xxxx): ");
					    NamHoc=scan.nextLine();
					    
					    System.out.print("Hoc ky: ");
					    HocKy=scan.nextInt();
					    check=scan.nextLine();//CLEAR BUFFER
					    
					    sql="insert into Lop "
						+"values('"+MaLop+"','"+MaMH+"','"+NamHoc+"',"+HocKy+",'"+MaGV+"')";
					    stmt.executeUpdate(sql);
					}
				    }
				}
				
				System.out.print("Type 'y' to continue, 'n' to exit: ");
				check=scan.nextLine();
				System.out.println();
			    }while(check.equals("y"));
			    break;
			    
			case 2:
			    System.out.println("Sửa đổi thông tin lớp");
			    System.out.println();
			    
			    do{
				System.out.print("Ma lop need to be updated: ");
				String UDMaLop=scan.nextLine();
				
				//check if exists
				if(new CheckExists().checkExists("Lop","MaLop",UDMaLop)==0){
				    System.out.println("The record doesn't exitst");
				    System.out.println();
				}
				else{//exitst
				    //start to update
				    System.out.print("Do you want to update ma lop? (Type 'y' or 'n'): ");
				    check=scan.nextLine();
				    System.out.println();
				    if(check.equals("y"))
					{
					    System.out.print("Ma lop: ");
					    MaLop=scan.nextLine();
					    
					    if(new CheckExists().checkExists("Lop","MaLop",MaLop)==1){
						System.out.println("The record already exists");
						System.out.println();
					    }
					    else{//exitst
						sql="update Lop set MaLop='"+MaLop+"'  where MaLop='"+UDMaLop+"'";//notice the space " where
						stmt.executeUpdate(sql);
						UDMaLop=MaLop;
					    }
					}
				    
				    System.out.print("Do you want to update ma mon hoc? (Type 'y' or 'n'): ");
				    check=scan.nextLine();
				    System.out.println();
				    if(check.equals("y"))
					{
					    System.out.print("Ma mon hoc: ");
					    MaMH=scan.nextLine();
					    
					    if(new CheckExists().checkExists("MonHoc","MaMH",MaMH)==0){
						System.out.println("Mon Hoc doesn't exists");
					    }
					    else {
						sql="update Lop set MaMH='"+MaMH+"' where MaLop='"+UDMaLop+"'";
						stmt.executeUpdate(sql);
					    }
					}
				    
				    System.out.print("Do you want to update nam hoc? (Type 'y' or 'n'): ");
				    check=scan.nextLine();
				    System.out.println();
				    if(check.equals("y"))
					{
					    System.out.print("Nam hoc (xxxx-xxxx): ");
					    NamHoc=scan.nextLine();
					    sql="update Lop set NamHoc='"+NamHoc+"' where MaLop='"+UDMaLop+"'";
					    stmt.executeUpdate(sql);
					}
				    
				    System.out.print("Do you want to update hoc ky? (Type 'y' or 'n'): ");
				    check=scan.nextLine();
				    System.out.println();
				    if(check.equals("y"))
					{
					    System.out.print("Hoc ky: ");
					    HocKy=scan.nextInt();
					    check=scan.nextLine();//CLEAR BUFFER
					    sql="update Lop set HocKy="+HocKy+" where MaLop='"+UDMaLop+"'";
					    stmt.executeUpdate(sql);
					}
				    
				    System.out.print("Do you want to update giao vien? (Type 'y' or 'n'): ");
				    check=scan.nextLine();
				    System.out.println();
				    if(check.equals("y"))
					{
					    System.out.print("Ma giao vien: ");
					    MaGV=scan.nextLine();
					    
					    if(new CheckExists().checkExists("GiaoVien","MaGV",MaGV)==0){
						System.out.println();
						System.out.println("The teacher doesn't exits");	
					    }
					    else {
						sql="update Lop set MaGV='"+MaGV+"'  where MaLop='"+UDMaLop+"'";//notice the space " where
						stmt.executeUpdate(sql);
					    }
					}
				    
				    sql="select * from Lop where MaLop='"+UDMaLop+"'";
				    ResultSet rs=stmt.executeQuery(sql);
				    
				    while(rs.next()){
	//Retrieve by column name
				System.out.print("MaLop: "+rs.getString("MaLop"));
				System.out.print(", MaMH: "+rs.getString("MaMH"));
				System.out.print(", NamHoc: "+rs.getString("NamHoc"));
				System.out.print(", HocKy: "+rs.getInt("HocKy"));
				System.out.println(", MaGV: "+rs.getString("MaGV"));
				    }
				    rs.close();
				}
				
				System.out.print("Type 'y' to continue, 'n' to exit: ");
				check=scan.nextLine();
				System.out.println();
			    }while(check.equals("y"));
			    break;
			    
			case 3:
			    System.out.println();
			    System.out.println("Bổ sung sinh viên vào lớp");
			    System.out.println();
			    
			    String ISClass=new String();//INSERT CLASS
			    do{
				System.out.print("Input the MaLop you want to add student to: ");
				ISClass=scan.nextLine();
				
				//check if exists
				if(new CheckExists().checkExists("Lop","MaLop",ISClass)==0){
				    System.out.println("The class doesn't exists");
				}
				else {
				    System.out.println();
				    System.out.println("Class: "+ISClass+":");
				    System.out.println();
				    String check2=new String();
				    do
					{
					    System.out.print("Ma sinh vien: ");
					    MaSV=scan.nextLine();
					    
					    //check if exists
					    if(new CheckExists().checkExists("SinhVien","MaSV",MaSV)==0){
						System.out.println("\nThe SinhVien doesn't exists");
					    }
					    else
						{
						    //check if exists
						    if(new CheckExists().checkExists("SinhVienLop","MaSV",MaSV)==1){
                                                        System.out.println();
							System.out.println("The record already exists");
							
						    }
						    else {
							check2=scan.nextLine();//??????????????????????????????????
							
							sql="insert into SinhVienLop (MaSV,MaLop)"
							    +"values('"+MaSV+"','"+ISClass+"')";
							stmt.executeUpdate(sql);
						    }
						}
					    
					    System.out.print("Do you want to continue insert studen? (Type 'y' or 'n'): ");
					    check2=scan.nextLine();
					    System.out.println();
					}while(check2.equals("y"));
				}
				
				System.out.print("Do you want to continue with new class? (Type 'y' or 'n'): ");
				check=scan.nextLine();
				System.out.println();
			    }while(check.equals("y"));
			    
			    break;
			    
			case 4:
			    System.out.println("Loại bỏ sinh viên khỏi lớp");
			    System.out.println();
			    
			    String DLClass=new String();//DELETE CLASS
			    do{
				System.out.print("Input the MaLop you want to delete student from: ");
				DLClass=scan.nextLine();
				
				//check if exists
				if(new CheckExists().checkExists("Lop","MaLop",DLClass)==0){
				    System.out.println("The class doesn't exists");
				}
				else {
				    System.out.println();
				    System.out.println("Class: "+DLClass+":");
				    System.out.println();
				    String check2=new String();
				    do
					{
					    System.out.print("Ma sinh vien want to delete: ");
					    MaSV=scan.nextLine();
					    
					    //check if exists
					    if(new CheckExists().checkExists("SinhVienLop","MaSV",MaSV)==0){
						System.out.println();
						System.out.println("The SinhVien doesn't exists in this class");
					    }
					    else
						{
						    sql="delete from SinhVienLop where MaSV='"+MaSV+"'";
						    stmt.executeUpdate(sql);
						}
					    
					    System.out.print("Do you want to continue delete studen from this class? (Type 'y' or 'n'): ");
					    check2=scan.nextLine();
					    System.out.println();
					}while(check2.equals("y"));
				}
				
				System.out.print("Do you want to continue with new class? (Type 'y' or 'n'): ");
				check=scan.nextLine();
				System.out.println();
			    }while(check.equals("y"));
			    break;
			    
			case 5:
			    System.out.println("Hủy lớp");
			    System.out.println();
			    
			    do{
				System.out.print("Input the MaLop you want to delete: ");
				MaLop=scan.nextLine();
				
				//check if exists
				if(new CheckExists().checkExists("Lop","MaLop",MaLop)==0){
				    System.out.println("The class doesn't exists");
				}
				else {    
				    sql="delete from Lop where MaLop='"+MaLop+"'";
				    stmt.executeUpdate(sql);	   
				}
				
				System.out.print("Do you want to continue with new class? (Type 'y' or 'n'): ");
				check=scan.nextLine();
				System.out.println();
			    }while(check.equals("y"));
			    break;
			    
			case 6:
			    System.out.println("In danh sách lớp");
			    System.out.println();
			    
			    sql="select * from Lop";
			    ResultSet rs=stmt.executeQuery(sql);
			    
			    while(rs.next()){
				//Retrieve by column name
				System.out.print("MaLop: "+rs.getString("MaLop"));
				System.out.print(", MaMH: "+rs.getString("MaMH"));
				System.out.print(", NamHoc: "+rs.getString("NamHoc"));
				System.out.print(", HocKy: "+rs.getInt("HocKy"));
				System.out.println(", MaGV: "+rs.getString("MaGV"));
			    }
			    rs.close();
			    break;
			case 7:
			    System.out.println("Quit");
		System.out.println("**************************************");
				System.out.println();
			    break;
			}
		}while(choice!=7);
	    
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
