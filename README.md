How to run: use terminal

~$ cd Src

~$ javac -d ../Package/ CheckExists.java Menu.java QuanLy.java QuanLyDiem.java QuanLyGiaoVien.java QuanLyLop.java QuanLyMonHoc.java QuanLySinhVien.java QuanTriHeThong.java DB_CHOOSE.java

~$ cd ../Package/

~$ java -cp .:../used_package/mysql-connector-java-8.0.17.jar quanLy.QuanLy


Clear terminal Screen (linux)
System.out.print("\033[H\033[2J");

    'H' means move to top of the screen

    '2J' means "clear entire screen"
