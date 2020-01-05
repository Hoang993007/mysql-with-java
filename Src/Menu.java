//PRINT SELECTION OF CHOSEN MENU - GET CHOICE AND RETURN TO THE FUNCTION

package menu;

import java.util.Scanner;

public class Menu{

    public static String[] mainMenu= new String[]{"-----------------Menu-----------------","Mục 1: Quản lý môn học","Mục 2: Quản lý giáo viên","Mục 3: Quản lý sinh viên","Mục 4: Quản lý lớp","Mục 5: Quản lý điểm","Mục 6: Quản trị hệ thống","Mục 7: Quit"};
    
    public static String[] menuQuanLyMonHoc=new String[]{"Quan ly mon hoc","Chọn 1: Thêm môn học mới","Chọn 2: Sửa thông tin môn học","Chọn 3: In danh sách môn học","Chọn 4: Quit"};
    
    public static String[] menuQuanLyGiaoVien=new String[]{"Quan ly giao vien","Chọn 1: Thêm hồ sơ giáo viên","Chọn 2: Sửa thông tin giáo viên","Chọn 3: In danh sách giáo viên","Chọn 4: Tìm kiếm giáo viên","Chọn 5: Quit"};
    
    public static String[] menuQuanLySinhVien=new String[]{"Quan ly sinh vien","Chọn 1: Thêm hồ sơ sinh viên","Chọn 2: Sửa thông tin sinh viên","Chọn 3: Tìm kiếm sinh viên","Chon 4: Quit"};
    
    public static String[] menuQuanLyLop=new String[]{"Quan ly lop","Chọn 1: Tạo lớp mới","Chọn 2: Sửa đổi thông tin lớp","Chọn 3: Bổ sung sinh viên vào lớp","Chọn 4: Loại bỏ sinh viên khỏi lớp","Chọn 5: Hủy lớp","Chọn 6: In danh sách lớp","Chọn 7: Quit"};
    
    public static String[] menuQuanLyDiem=new String[]{"Quan ly diem","Chọn 1: Nhập điểm","Chọn 2: Sửa điểm","Chọn 3: In bảng điểm cho lớp","Chọn 4: In bảng điểm cho sinh viên","Chọn 5: Quit"};
    
    public static String[] menuQuanTriHeThong=new String[]{"Quan tri he thong","Chọn 1: Sao lưu dữ liệu","Chọn 2: Phục hồi dữ liệu","Chọn 3: Quit"};
    
    public int getChoice(String[] menu){
	System.out.print("\033[H\033[2J");//clear terminal
	
	printMenu(menu);
	int option=menu.length;
	
	int choice;
	Scanner scan=new Scanner(System.in);
	
	do{
	    System.out.print("Please choose one: ");
	    String choiceString=scan.nextLine();
	    if(choiceString.length()!=1||choiceString.charAt(0)>option+'0'||choiceString.charAt(0)<'1')
		choice=-1; else choice=choiceString.charAt(0)-'0';

	    if(choice>option||choice<1)
		System.out.println("Ban da nhap sai, moi ban nhap lai!");
	    
	}while(choice>option||choice<1);
	
	System.out.println("**************************************");
	return choice;
    }
    
    private void printMenu(String[] menu){
	System.out.println("**************************************");
	System.out.println(menu[0]);
	System.out.println("**************************************");
	
	for(int i=1;i<menu.length;i++){
	    System.out.println(menu[i]);
	}
	
	System.out.println("**************************************");
    }
}
