package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;
import sqlPerforment.*;

public class QuanLyMonHoc{
    
    Statement stmt;
    public QuanLyMonHoc(Statement stmt){
	this.stmt=stmt;
    }
    
    public void exec() throws  SQLException, Exception{
	String sql=new String();
	String MaMH=new String();
	String TenMH=new String();
	int SoTC=0;
	String check=new String();
	
	Scanner scan=new Scanner(System.in);
	
	String[] fieldsNameToPrint;
	
	System.out.println();
	int choice;
	do
	    {
		choice=new Menu().getChoice(Menu.menuQuanLyMonHoc);
		switch(choice)
		    {
		    case 1:
			System.out.println("Thêm môn học mới");
			
			do{
			    System.out.println();
			    try{
				System.out.print("Ma mon hoc: ");
				MaMH=scan.nextLine();
			    
				//check if exists
				if(new CheckExists(stmt).checkExists("MonHoc","MaMH='"+MaMH+"'")==1){
				    System.out.println("The record already exists");
				}else{
				    System.out.print("Ten mon hoc: ");
				    TenMH=scan.nextLine();

				    do{
				    	try{
					    System.out.print("So tin chi: ");


					    
					    SoTC=scan.nextInt();//nếu là số thì nó đã đọc được, nếu là ký tự thì nó sẽ sang catch, ở đó sẽ in ra và hỏi. Có nghĩa là bufer để đọc trong trường hợp mà đầu vào là số sẽ không bao giờ là y để mà xảnh ra conflix - hàm này chuyên dùng để kiểm tra nhập lại với số **********************
					    //nếu đọc là int thì nó nhất định sẽ không đọc xâu, nên pải đọc lại
					}catch(Exception e){
					    SoTC=-1;
					    scan.nextLine();
					    System.out.println("Error\n");
					    System.out.print("Want to continue? (y): ");
					}
				    }while(scan.nextLine().equals("y"));
				    
				    if(SoTC==-1)
					System.out.println("\n*******No add******");
				    else{
					sql="insert into MonHoc "
					    +"values('"+MaMH+"','"+TenMH+"',"+SoTC+")";
					

					
					try{
					    stmt.executeUpdate(sql);
					}catch(SQLException se){
					    se.printStackTrace();scan.nextLine();
					}
				    }
				}
				
			    }catch(Exception e){
				e.printStackTrace();scan.nextLine();
			    }
				
			    System.out.print("\n### Type y to continue/n to exit: ");
			}while(scan.nextLine().equals("y"));
			break;
			    
		    case 2:
			System.out.println("Sửa thông tin môn học\n");

			do{
			    System.out.println();
			    try{
				System.out.print("Ma mon hoc need to be updated: ");
				String UDMaMH=scan.nextLine();
				
				//check if exists
				if(new CheckExists(stmt).checkExists("MonHoc","MaMH='"+UDMaMH+"'")==0){
				    System.out.println("The record doesn't exists\n");
				}
				else{//exitst
				    sql="select * from MonHoc where MaMH='"+UDMaMH+"'";
				
				    fieldsNameToPrint=new String[]{"MaMH","TenMH","SoTC"};
				    new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
				    //start to update
				    
				    System.out.print("\nUpdate ma mon hoc? (y/n): ");
				    check=scan.nextLine();

				    if(check.equals("y"))
					{
					    System.out.print("Ma mon hoc: ");
					    MaMH=scan.nextLine();
					    if(new CheckExists(stmt).checkExists("MonHoc","MaMH='"+MaMH+"'")==1){
						System.out.println("The record already exists\n");
					    }
					    else{//exitst
						sql="update MonHoc set MaMH='"+MaMH+"'  where MaMH='"+UDMaMH+"'";//notice the space " where
						try{
						    stmt.executeUpdate(sql);
						}catch(SQLException se){
						    se.printStackTrace();scan.nextLine();
						}
						
						UDMaMH=MaMH;
					    }
					}
				    
				    System.out.print("\nUpdate ten mon hoc? (y/n): ");
				    check=scan.nextLine();

				    if(check.equals("y")){
					System.out.print("Ten mon hoc: ");
					TenMH=scan.nextLine();
					sql="update MonHoc set TenMH='"+TenMH+"' where MaMH='"+UDMaMH+"'";
					try{
					    stmt.executeUpdate(sql);
					}catch(SQLException se){
					    se.printStackTrace();scan.nextLine();
					}
					  
				    }
				    
				    System.out.print("\nUpdate so tin chi? (y/n): ");
				    check=scan.nextLine();

				    if(check.equals("y")){
					System.out.print("So tin chi: ");
					SoTC=scan.nextInt();
					check=scan.nextLine();
					sql="update MonHoc set SoTC="+SoTC+" where MaMH='"+UDMaMH+"'";
					try{
					    stmt.executeUpdate(sql);
					}catch(SQLException se){
					    se.printStackTrace();scan.nextLine();
					}
				    }
				    
				    sql="select * from MonHoc where MaMH='"+UDMaMH+"'";
				    new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
				}
			    }catch(Exception e){
				e.printStackTrace();scan.nextLine();
			    }
				
			    System.out.print("\n### Type y to continue/n to exit: ");
			}while(scan.nextLine().equals("y"));
			break;
			    
			    
		    case 3:
			System.out.println("In danh sách môn học\n");
			    
			sql="select * from MonHoc";

			fieldsNameToPrint=new String[]{"MaMH","TenMH","SoTC"};
			new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNameToPrint);
			     
			scan.nextLine();
			break;


		    case 4:
			System.out.println("Quit");
			System.out.println("**************************************\n");
			break;
		    }
	    }while(choice!=4);
	//scan.close();
    }
}
