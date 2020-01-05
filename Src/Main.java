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
	try{
	    //STEP 2: Register JDBC driver
	    Class.forName("com.mysql.jdbc.Driver");
	}catch(Exception e){
	    //Handle errors for Class.forName
	     e.printStackTrace();
	}
	
	new DB_CHOOSE();
	
	int choice;
	do
	    {
		choice=new Menu().getChoice(Menu.mainMenu);
		switch(choice)
		    {
		    case 1:
			new QuanLyMonHoc().exec();
			break;
		    case 2:
			new QuanLyGiaoVien().exec();
			break;
		    case 3:
			new QuanLySinhVien().exec();
			break;
		    case 4:
			new QuanLyLop().exec();
			break;
		    case 5:
			new QuanLyDiem().exec();
			break;
		    case 6:
			new QuanTriHeThong().exec();
			break;
		    case 7:
			System.out.println("Quit");
			break;
		    }
	    }while(choice!=7);
    }
}

