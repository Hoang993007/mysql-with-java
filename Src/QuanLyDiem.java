package muc;

import java.util.Scanner;
import java.sql.*;

//my package
import db_choose.*;
import check_exists.*;
import menu.*;
import sqlPerforment.*;
import getInput.*;

//trong cả chương trình sẽ chỉ có một đối tượng QuanLyDiem duy nhat duoc tao. Nói giống như là chỉ có 1 thằng duy nhất được làm công việc quản lý điểm thôi

//để static statement -> dù có nhiều đối tượng đi nữa thì cũng sẽ thao tác chung với 1 CSDL tại 1 thời điểm -> không thể quản lý trên nhiều CSDL cùng lúc
//=> không để static

//để public stacic void... -> mỗi method là 1 công cụ như cái búa. Tựa như chúng chỉ có thể sử dụng 1 cái búa vào một thời điểm -> ko bị conflic khi mà ko thể dùng 2 cái búa đóng vào 1 cái đinh trong cùng 1 thời điểm

//public static void QuanLyDiemChooseAction() Dùng để kích hoạt một hoạt động tại một thời điểm -> không thể kích hoạt nhiều haotj động tại 1 thời điểm -> sau khi 1 cái kết thúc -> break mới có thể kích hoạt cái khác
//=> để là     public static void QuanLyDiemChooseAction()
//Nhưng cũng có trường hợp tranh đoạt cùng 1 công cụ tại 1 thời điểm -> thêm đèn báo

//hàm static đc truy nhập dễ dàng bên trong class vì nó là cố định
//VD:  case 3:
//		inBangDiem(stmt);
//		break;


public class QuanLyDiem{
    
   // private Statement stmt;
    //public QuanLyDiem(Statement stmt){
	//this.stmt=stmt;
    //}
    //nếu để như trên thì tưởng tượng ra là mình có thể tạo ra hàng trăm cái đói tượng dùng chung mấy cái búa này, mỗi đối tượng lại sở hữu một stmt riêng, cái búa không thể biết được stmt nào là của đối tượng nào.
//=> GIẢI QUYẾT: QuanLyDiemChooseAction là hàm gọi thực hiện hành động tương ứng với mỗi đối tượng. Vậy khi một hàm thực h ienj hành động gọi lấy một công cụ thì thông qua hàm đó truyền luôn thông số vào, vậy là có thể biết được là thông số đó thuộc về đối tượng sở hữu cái hàm đó

    //ĐÈN BÁO
    private static int light_nhap_suaDiem=0;
    private static int light_inBangDiem=0;
    private static int light_inBangDiemSinhVien=0;
    //vì là static nên =0 chỉ là cho lần đầu khởi tạo thôi, nó sẽ ko cho bằng 0 trong các lần gọi tiếp theo
    public void QuanLyDiemChooseAction(Statement stmt) throws  SQLException, Exception{
	System.out.println();
	int choice;
	do{
	    choice=new Menu().getChoice(Menu.menuQuanLyDiem);
	    switch(choice){
	    case 1:
	    case 2://choice=1 or choice==2
		if(light_nhap_suaDiem==0){//công cụ chưa được dùng
		    light_nhap_suaDiem=1;
		    nhap_suaDiem(stmt, choice);
		    light_nhap_suaDiem=0;
		}
		break;
			
	    case 3:
		if(light_inBangDiem==0){
		    light_inBangDiem=1;
		    inBangDiem(stmt);
		    light_inBangDiem=0;
		}
		break;
					
	    case 4:
		if(light_inBangDiemSinhVien==0){
		    light_inBangDiemSinhVien=1;
		    inBangDiemSinhVien(stmt);
		    light_inBangDiemSinhVien=0;
		}
		break;

	    case 5:
		System.out.println("Quit\n**************************************\n");
		break;
	    }
	}while(choice!=5);
    }

    private static void nhap_suaDiem(Statement stmt, int choice) throws  SQLException, Exception{
	String sql=new String();
	String MaSV=new String();
	String ISClass=new String();//INSERT CLASS
	double Diem=0;
	
	Scanner scan=new Scanner(System.in);

	if(choice==1)
	    System.out.println("Nhập điểm\n");
	else
	    System.out.println("Sửa điểm\n");
			
	do{
	    try_case_12:
	    try{
		System.out.print("\nInput the MaLop you want to input score: ");
		ISClass=scan.nextLine();
		if(ISClass.equals("")) break try_case_12;
			    
		//check if exists
		if(new CheckExists(stmt).checkExists("Lop","MaLop='"+ISClass+"'")==0)
		    System.out.println("The class doesn't exists");
		else {
		    System.out.println("\nClass: "+ISClass+":\n");

		    do {
			System.out.print("Ma sinh vien: ");
			MaSV=scan.nextLine();
			if(MaSV.equals("")) break try_case_12;
					
			//check if exists
			if(new CheckExists(stmt).checkExists("SinhVienLop","MaSV='"+MaSV+"' and "+"MaLop='"+ISClass+"'")==0)
			    System.out.println("\nThe SinhVien doesn't exists in this class");
			else  if(choice==1&&new CheckExists(stmt).checkExists("SinhVienLop","MaSV='"+MaSV+"' and "+"MaLop='"+ISClass+"' and Diem IS NULL")==0){
			    System.out.println("\nDiem had inputed\n");
			}else  if(choice==2&&new CheckExists(stmt).checkExists("SinhVienLop","MaSV='"+MaSV+"' and "+"MaLop='"+ISClass+"' and Diem IS NULL")==1){
			    System.out.println("\nDiem haven't inputed\n");
			}else{
			    System.out.print("Diem: ");
			    GetInput getDiem=new GetInput();
			    getDiem.getDouble();
			    if(getDiem.getError()==1) break try_case_12;
			    else Diem=getDiem.getDoubleValue();
			    
			    sql="update SinhVienLop set Diem='"+Diem+"' where MaSV='"+MaSV+"' and MaLop='"+ISClass+"'";
			    try{
				stmt.executeUpdate(sql);
			    }catch(SQLException se){
				se.printStackTrace();scan.nextLine();
			    }
			}
			System.out.print("\nDo you want to continue insert score? (y/n): ");
		    }while(scan.nextLine().equals("y"));
		}
	    }catch(Exception e){
		e.printStackTrace();scan.nextLine();
	    }			    
	    System.out.print("\nDo you want to continue with new class? (y/n): ");
	}while(scan.nextLine().equals("y"));
    }



    private static  void inBangDiem(Statement stmt) throws  SQLException, Exception{
	System.out.println("In bảng điểm cho lớp\n");

	String sql=new String();
	String MaLop=new String();
	double Diem;
	
	String[] fieldsNameToPrint;
	String[] fieldsNamePrint;
	
	Scanner scan=new Scanner(System.in);

	try_case_3:
	try{	
	    System.out.print("Input the MaLop: ");
	    MaLop=scan.nextLine();
	    if(MaLop.equals("")) break try_case_3;
		    
	    if(new CheckExists(stmt).checkExists("Lop","MaLop='"+MaLop+"'")==0){
		System.out.println("The class doesn't exists");
		scan.nextLine();
	    }
	    else {
		System.out.println("\nClass: "+MaLop+":\n");
			    
		sql="select "
		    +"c.MaSV,c.HoSV,c.TenSV,c.NgaySinh,c.NoiSinh,b.Diem "
		    +"from "
		    +"Lop a inner join SinhVienLop b on a.MaLop=b.MaLop "
		    +"inner join SinhVien c on c.MaSV=b.MaSV "
		    +"where "
		    +"b.MaLop='"+MaLop+"'";
			    
		fieldsNameToPrint=new String[]{"c.MaSV","c.HoSV","c.TenSV","c.NgaySinh","c.NoiSinh","b.Diem"};
		fieldsNamePrint=new String[]{"MaSV","HoSV","TenSV","NgaySinh","NoiSinh","Diem"};
		new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNamePrint);
			    
		scan.nextLine();
	    }
	}catch(Exception e){
	    e.printStackTrace();
	    scan.nextLine();
	}
    }

    private static  void inBangDiemSinhVien(Statement stmt) throws  SQLException, Exception{
	System.out.println("In bảng điểm cho sinh viên\n");

	String sql=new String();
	String MaSV=new String();
	int Diem;
	
	String[] fieldsNameToPrint;
	String[] fieldsNamePrint;
	
	Scanner scan=new Scanner(System.in);

	try_case_4:
	try{
	    //lặp lại cho sinh viên khác nữa
			
	    System.out.print("Input the MaSV: ");
	    MaSV=scan.nextLine();
	    if(MaSV.equals("")) break try_case_4;
		    
	    if(new CheckExists(stmt).checkExists("SinhVien","MaSV='"+MaSV+"'")==0){
		System.out.println("This student doesn't exists");
		scan.nextLine();
	    }else {
		System.out.println("\nMaSV: "+MaSV+":\n");
			    
		sql="select "
		    +"a.MaLop,a.MaMH,d.TenMH,b.Diem "
		    +"from "
		    +"Lop a inner join SinhVienLop b on a.MaLop=b.MaLop "
		    +"inner join SinhVien c on c.MaSV=b.MaSV "
		    +"inner join MonHoc d on a.MaMH=d.MaMH "
		    +"where b.MaSV='"+MaSV+"'";
			    
		fieldsNameToPrint=new String[]{"a.MaLop","a.MaMH","d.TenMH","b.Diem"};
		fieldsNamePrint=new String[]{"MaLop","MaMH","TenMH","Diem"};
		new SQLPerforment(stmt).prtQueryRs(sql,fieldsNameToPrint,fieldsNamePrint);
			    
		scan.nextLine();
	    }
	}catch(Exception e){
	    e.printStackTrace();scan.nextLine();
	}
    }
    
}
