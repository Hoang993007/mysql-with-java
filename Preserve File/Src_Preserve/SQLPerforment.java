package sqlPerforment;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;

public class SQLPerforment{
    
    
    Statement stmt;
    public SQLPerforment(Statement stmt){
	this.stmt=stmt;
    }
    
    //PRINT THE RESULT RETURNED BY THE SELECT QUERY
    public int prtQueryRs(String sql, String[] fields, String[] fieldsNamePerform)  throws  SQLException, Exception{
        int count=0;
	Scanner scan=new Scanner(System.in);
	try{
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
	    
	}catch(SQLException se){
	    se.printStackTrace();
	    scan.nextLine();
	}	
        if(count==0) return 0; else return 1;
    }
}
