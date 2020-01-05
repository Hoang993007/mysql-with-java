//INPUT WHICH DATABASE WILL BE USED FOR THE PROGRAM. MUST BE SET UP FIRST AND CAN NOT BE CHANGE

package db_choose;

import java.util.Scanner;

public class DB_CHOOSE{
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    
    public static final String DB_URL;
    public static final String DBNAME;

    //  Database credentials
    public static final String USER;
    public static final String PASS;

    //start of static block
    static{
	Scanner scan=new Scanner(System.in);
	
	//System.out.print("USER: ");
	USER ="hoangdb";//scan.nextLine();

	//System.out.print("PASS: ");
        PASS ="123";// scan.nextLine();//pass of USER
	
	//System.out.print("DATABASE: ");
	DBNAME="cuoiKi";//scan.nextLine();
	DB_URL = "jdbc:mysql://localhost/"+DBNAME;
    }
    //end of static block
}

