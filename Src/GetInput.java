//GET AND CHECK IF THE INPUT IS CORRECT OR NOT

package getInput;

import java.util.Scanner;

public class GetInput{

    private int intVal;
    private double doubleVal;
    private int error;
    
    public void getInt() throws  Exception{
	Scanner scan=new Scanner(System.in);
	
	try{
	    intVal=scan.nextInt();
	    scan.nextLine();
	    error=0;//NO ERRORS
	    return;
	}catch(Exception e0){
	    scan.nextLine();//đọc xâu thừa mà ở kia do là nextInt nên ko đọc
	    System.out.println("\nError\n");
	    System.out.print("\nReinput (y)? ");
	    if(!scan.nextLine().equals("y")){
		error=1;//ERRORS
	    }else{
		do{
		    System.out.print("New value: ");
		    try{
			intVal=scan.nextInt();
			scan.nextLine();
			error=0;//NO ERRORS
			return;
		    }catch(Exception e1){
			scan.nextLine();//đọc xâu thừa mà ở kia do là nextInt nên ko đọc
			System.out.println("\nError\n");
			System.out.print("\nReinput (y)? ");
		    }
		}while(scan.nextLine().equals("y"));
		error=1;//ERRORS
	    }
	}
    }

    public int getIntValue()
    {
	return intVal;
    }

        public void getDouble() throws  Exception{
	Scanner scan=new Scanner(System.in);
	
	try{
	    doubleVal=scan.nextDouble();
	    scan.nextLine();
	    error=0;//NO ERRORS
	    return;
	}catch(Exception e0){
	    scan.nextLine();//đọc xâu thừa mà ở kia do là nextInt nên ko đọc
	    System.out.println("\nError\n");
	    System.out.print("\nReinput (y)? ");
	    if(!scan.nextLine().equals("y")){
	    }else{
		do{
		    System.out.print("New value: ");
		    try{
			doubleVal=scan.nextDouble();
			scan.nextLine();
			error=0;//NO ERRORS
			return;
		    }catch(Exception e1){
			scan.nextLine();//đọc xâu thừa mà ở kia do là nextInt nên ko đọc
			System.out.println("\nError\n");
			System.out.print("\nReinput (y)? ");
		    }
		}while(scan.nextLine().equals("y"));
	    }
	}
    }

        public double getDoubleValue()
    {
	return doubleVal;
    }

    
        public int getError()
    {
	return error;
    }
}
