//MAIN CLASS

package main;

import java.sql.*;
import java.util.Scanner;
import java.io.*;

//my package
import menu.*;
import muc.*;
import db_choose.*;

class Main{//do not let QuanLy be a public class
    public static void main(String args[]){
	//***********************************************************
	Connection conn = null;
	Statement stmt = null;
	Scanner scan=new Scanner(System.in);
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection(DB_CHOOSE.DB_URL, DB_CHOOSE.USER, DB_CHOOSE.PASS);
	    
	    stmt=conn.createStatement();
	    //**********************************************************
	
	    new DB_CHOOSE();
	
	    int choice;
	    do{
		choice=new Menu().getChoice(Menu.mainMenu);
		switch(choice){
		case 1:
		    new QuanLyMonHoc(stmt).exec();
		    break;
		case 2:
		    new QuanLyGiaoVien(stmt).exec();
		    break;
		case 3:
		    new QuanLySinhVien(stmt).exec();
		    break;
		case 4:
		    new QuanLyLop(stmt).exec();
		    break;
		case 5:
		    new QuanLyDiem(stmt).exec();
		    break;
		case 6:
		    new QuanTriHeThong(stmt).exec();
		    break;
		case 7:
		    System.out.println("Quit");
		    break;
		}
	    }while(choice!=7);
	    //***********************************************
	    //ko cần SQLException ở đây vì ko có câu lệnh truy vấn nào được thực hiện ở đây
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
	    scan.close();
	}//end try
	//*******************************************************
    }
}

