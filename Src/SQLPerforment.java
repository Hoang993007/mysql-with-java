package sqlPerforment;

import java.sql.*;

//my package
import db_choose.*;

public class SQLPerforment{
    public int prtQueryRs(String sql, String[] fields, String[] fieldsNamePerform){
        int count=0;
	//******************************************************************
	Connection conn = null;
	Statement stmt = null;

	try{
	    Class.forName("com.mysql.jdbc.Driver");
	    conn = DriverManager.getConnection(DB_CHOOSE.DB_URL, DB_CHOOSE.USER, DB_CHOOSE.PASS);
	    
	    stmt=conn.createStatement();
	    //**********************************************************
	    ResultSet rs=stmt.executeQuery(sql);

	    System.out.println();
	    while(rs.next()){
                count=1;
		//Retrieve by column name
		for(int i=0;i<fields.length;i++)
		    System.out.print(fieldsNamePerform[i]+": "+rs.getString(fields[i])+"  ");

//note: May you will want to get fields[i] as Int, use getInt(fields[i]).
//But here I get all of value as String type to print. I think it will no effect the result even the original type of value is int, not String

            System.out.println();
	    }
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
        if(count==0) return 0; else return 1;
    }
}
