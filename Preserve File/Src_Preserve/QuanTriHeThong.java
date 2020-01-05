package muc;

import java.util.Scanner;
import java.sql.*;
import java.io.*;

//my package
import db_choose.*;
import menu.*;

public class QuanTriHeThong{

    Statement stmt;
    public QuanTriHeThong(Statement stmt){
	this.stmt=stmt;
    }

    public void exec() throws  SQLException, Exception{

	String sql=new String();
	Scanner scan=new Scanner(System.in);
	String SQLFileName=new String();
	String path_to_store_backup_file="BackUp_sqlFile/";
	String path_to_take_backup_file="BackUp_sqlFile/";
	    
	System.out.println();
	int choice;
	do {
	    choice=new Menu().getChoice(Menu.menuQuanTriHeThong);
	    switch(choice){
	    case 1:
		System.out.println("\nExport data\n");
		System.out.print("Input the name of back up file: ");
		SQLFileName=scan.nextLine();

		String executeCmd = "mysqldump -u "+DB_CHOOSE.USER+" -p"+DB_CHOOSE.PASS+" "+DB_CHOOSE.DBNAME+"  -r "+path_to_store_backup_file+SQLFileName+".sql";
			    
		Process runtimeProcess =Runtime.getRuntime().exec(executeCmd);
		int processComplete = runtimeProcess.waitFor();
		if(processComplete == 0){
		    System.out.println("Backup taken successfully");
		} else {
		    System.out.println("Could not take mysql backup");
		}
		scan.nextLine();
		break;
			    

	    case 2://read and execute command in sql file
		System.out.println("\nRestore data\n");
		System.out.print("Input the name of file restore: ");
		SQLFileName=scan.nextLine();
			    
		/*C1: read file .sql by myself and execute every command line.
		  File fileRestoreFrom = new File(path_to_take_backup_file+SQLFileName+".sql");
		  try (FileReader FR = new FileReader(fileRestoreFrom)){
				
		  BufferedReader BR = new BufferedReader(FR);
		  while ((sql = BR.readLine()) != null){
		  if(sql.length()<1) {//do nothing - empty line
		  }else if(sql.charAt(0)==' '){//do nothing
		  }else {//detect that it is an initialization of a table
		  if(sql.charAt(0)=='C'){//C in 'CREATE TABLE...'
		  String sqlTmp;
		  sqlTmp=sql;
		  do{
		  sql=BR.readLine();
		  sqlTmp=sqlTmp+sql;
		  }while(sql.charAt(0)!=')');//) IN ')ENGINE...'
		  sql=sqlTmp;
		  }
		  //System.out.println(line);//USE FOR CHECKING
		  stmt.execute(sql);
		  }
		  }
		  //if there is no problem with backup, the catch won't be actived, this line will be printed
		  System.out.println("Restore successfully");
		  }catch (Exception e){
		  e.printStackTrace();
		  }
		*/


		///{"/bin/sh","-c", "mysql","--user="+"hoangdb","--password="+"123","cuoiKi","<","BackUp.sql"};
			    
		//"mysql -u "+DB_CHOOSE.USER+" --password="+DB_CHOOSE.PASS+" "+DB_CHOOSE.DBNAME+" < BackUp.sql";
			    
		try{
		    String[] executeCmd2= new String[]{"/bin/sh", "-c", "mysql -u " + DB_CHOOSE.USER+ " -p"+DB_CHOOSE.PASS+" "+ DB_CHOOSE.DBNAME+" < "+path_to_take_backup_file+SQLFileName+".sql"};
		    //Note when write code: the password must wirte right next to -p without space. "-p123" is correct and "-p123" is false
			
		    System.out.println(executeCmd2[2]);

		    Process runtimeProcess2 =Runtime.getRuntime().exec(executeCmd2);
		    int processComplete2 = runtimeProcess2.waitFor();
		    if(processComplete2 == 0){
			System.out.println("success");
		    } else {
			System.out.println("restore failure");
		    }
		}catch (Exception e){
		    e.printStackTrace();
		    scan.nextLine();
		}
		scan.nextLine();
		break;


	    case 3:
		System.out.println("Quit\n**************************************\n");
		break;
	    }
	}while(choice!=3);
	//scan.close();
    }
}
