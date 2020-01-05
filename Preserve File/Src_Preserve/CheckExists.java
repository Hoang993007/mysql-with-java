//CHECK IF A RECORD EXISTS OR NOT IN A TABLE OF CHOSEN DATABASE

package check_exists;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;

public class CheckExists{
    
    private Statement stmt;
    private int exist;
    
    public CheckExists(Statement stmt){
	this.stmt=stmt;
    }
    
    public int checkExists(String fromClause, String whereClause) throws  SQLException, Exception{
	exist=1;//EXISTS
	Scanner scan=new Scanner(System.in);
	String sql=new String();
	try{
	    //check if exists
	    sql="select * from "+fromClause+"  where "+whereClause;
	    
	    ResultSet rs=stmt.executeQuery(sql);
	    
	    if(!rs.next()) exist=0;//NOT EXISTS
	    rs.close();
	}catch(SQLException se){
	    exist=-1;//ERROR
	    se.printStackTrace();
	    scan.nextLine();
	}
	
	return exist;
    }
}
