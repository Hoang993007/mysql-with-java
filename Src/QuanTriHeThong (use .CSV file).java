package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;

class Sql{
    String export_sql(String CSVName, String table_name, String field, String field_choose)
    {
	System.out.println();
	System.out.println("exporting table "+table_name+" into a CSV file...");
	return "select "+field
	    +"union "
	    +"select "+field_choose+" "
	    +"from "+table_name+" "
	    +"into outfile '"+CSVName+"_"+table_name+".csv' "
	    +"fields enclosed by '\"' "
	    +"terminated by ';' "
	    +"escaped by '\"' "
	    +"lines terminated by '\r\n'  ";
    }
    
    String import_sql(String CSVName, String table_name)
    {
	System.out.println();
	System.out.println("Importing data into table "+table_name+"...");
	return "load data infile '"+CSVName+"_"+table_name+".csv' "
	    +"into table "+table_name+" "
	    +"fields terminated by ';' "
	    +"enclosed by '\"' "
	    +"lines terminated by '\r\n' "
	    +"ignore 1 rows";
    }

    String delete_sql(String table_name)
    {
	return "delete from "+table_name;
    }
    }

public class QuanTriHeThong{
    public void exec(){
	//***********************************************************
	Connection conn = null;
	Statement stmt = null;
	try{
	    //STEP 2: Register JDBC driver
	    Class.forName("com.mysql.jdbc.Driver");
	    
	    //STEP 3: Open a connection
	    //System.out.println("Connecting to a selected database...");
	    conn = DriverManager.getConnection(DB_CHOOSE.DB_URL, DB_CHOOSE.USER, DB_CHOOSE.PASS);
	    //System.out.println("Connected database successfully...");
	    
	    //STEP 4: Execute querys
	    
	    stmt=conn.createStatement();
	    String sql=new String();
	    Scanner scan=new Scanner(System.in);
	    String SQLFileName=new String();
	    //String check=new String();
	    //**********************************************************
	    int choice;
	    do
		{
		    choice=new Menu().getChoice("menuQuanTriHeThong");
		    switch(choice)
			{
			case 1:
			    System.out.println();
			    System.out.println("Sao lưu dữ liệu");
			    System.out.println();
			    System.out.print("Input the name of back up file: ");
			    SQLFileName=scan.nextLine();

			   
			    
			    sql=new Sql().export_sql(CSVName, "SinhVien","'MaSV','HoSV','TenSV','NgaySinh','NoiSinh' ","*");
			    stmt.executeQuery(sql);
			    System.out.println();
			    System.out.println("Success!");
			    
			    sql=new Sql().export_sql(CSVName, "GiaoVien","'MaGV','HoGV','TenGV','DonVi' ","*");
			    stmt.executeQuery(sql);
			    System.out.println();
			    System.out.println("Success!");
			    
			    sql=new Sql().export_sql(CSVName, "MonHoc","'MaMH','TenMH','SoTC' ","*");
			    stmt.executeQuery(sql);
			    System.out.println();
			    System.out.println("Success!");
			    
			    sql=new Sql().export_sql(CSVName, "Lop","'MaLop','MaMH','NamHoc','HocKy','MaGV' ","*");
			    stmt.executeQuery(sql);
			    System.out.println();
			    System.out.println("Success!");
			    
			    sql=new Sql().export_sql(CSVName, "SinhVienLop","'MaSV','MaLop','Diem' ","MaSV,MaLop,IFNULL(Diem, 'N/A')");
			    stmt.executeQuery(sql);
			    System.out.println();
			    System.out.println("Success!");
			    
			    //store with more detail

			    break;
			    
			case 2:
			    System.out.println();
			    System.out.println("Phục hồi dữ liệu");
			    System.out.println();
			    System.out.print("Input the name of file restore: ");
			    SQLFileName=scan.nextLine();
			    
			    
			    //NOTE: BECAUSE WE HAVE FOREIGN KEY HERE SO WE HAVE TO DELETE TABLE WHICH HAVE FOREIGN KEY FIRST, THEN WE ARE ABLE TO DELETE TABLE HAVE PRIMARY KEY THAT THOSE FOREIGN KEY REFERTO
			    System.out.print("Do you want to clear current database? (Type 'y' or 'n'): ");
			    check=scan.nextLine();
			    System.out.println();
			    if(check.equals("y"))
				{
				    sql=new Sql().delete_sql("SinhVienLop");
				    stmt.executeUpdate(sql);
				    
				    sql=new Sql().delete_sql("Lop");
				    stmt.executeUpdate(sql);
				    
				    sql=new Sql().delete_sql("MonHoc");
				    stmt.executeUpdate(sql);
				    
				    sql=new Sql().delete_sql("GiaoVien");
				    stmt.executeUpdate(sql);
				    
				    sql=new Sql().delete_sql("SinhVien");
				    stmt.executeUpdate(sql);
				}
			    System.out.println();
			    
			    sql=new Sql().import_sql(CSVName,"SinhVien");
			    stmt.executeQuery(sql);
			    System.out.println();
			    System.out.println("Success!");
			    
			    sql=new Sql().import_sql(CSVName,"GiaoVien");
			    stmt.executeQuery(sql);
			    System.out.println();
			    System.out.println("Success!");
			    
			    sql=new Sql().import_sql(CSVName,"MonHoc");
			    stmt.executeQuery(sql);
			    System.out.println();
			    System.out.println("Success!");
			    
			    sql=new Sql().import_sql(CSVName,"Lop");
			    stmt.executeQuery(sql);
			    System.out.println();
			    System.out.println("Success!");
			    
			    sql=new Sql().import_sql(CSVName,"SinhVienLop");
			    stmt.executeQuery(sql);
			    System.out.println();
			    System.out.println("Success!");
		    
			    break;
			case 3:
			    System.out.println("Quit");
			    break;
			}
		}while(choice!=3);
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
