//CHECK IF A RECORD EXISTS OR NOT IN A TABLE OF CHOSEN DATABASE

package check_exists;

import java.sql.*;

//my package
import db_choose.*;

public class CheckExists{
    public int checkExists(String table_name, String colum_name, String Ma){
	    int feedback=1;	
	//******************************************************************
	Connection conn = null;
	Statement stmt = null;
	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection(DB_CHOOSE.DB_URL, DB_CHOOSE.USER, DB_CHOOSE.PASS);
	    
	    stmt=conn.createStatement();
	    //**********************************************************

	    String sql=new String();

	    
	    //check if exists
	    sql="select * from "+table_name+"  where "+colum_name+"='"+Ma+"'";
	    ResultSet rs=stmt.executeQuery(sql);
	    if(!rs.next()) feedback=0;
	    rs.close();

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
	
	return feedback;
    }
}
