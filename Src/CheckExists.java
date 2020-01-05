//CHECK IF A RECORD EXISTS OR NOT IN A TABLE OF CHOSEN DATABASE

package check_exists;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;

public class CheckExists{
    
    Statement stmt;
    public CheckExists(Statement stmt){
	this.stmt=stmt;
    }
    
    public int checkExists(String fromClause, String whereClause) throws  SQLException, Exception{
	int feedback=1;	
	Scanner scan=new Scanner(System.in);
	String sql=new String();
	try{
	    //check if exists
	    sql="select * from "+fromClause+"  where "+whereClause;
	    ResultSet rs=stmt.executeQuery(sql);
	    if(!rs.next()) feedback=0;
	    rs.close();
	}catch(SQLException se){
	    se.printStackTrace();scan.nextLine();
	}
//scan.close();
	return feedback;
    }
}
