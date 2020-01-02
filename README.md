Author: Nguyễn Minh Hoàng

In this project I will use Java 8.0, mySQL and JDBC to connect mySQL and Java to create a simple database for school.

#About files I have in this project

BakUp_sqlFile: I saved my export and import database in .sql files here.

Package: I put all packages consist of java .class files here.

Src: My source code, contain .java files.

original cuoiKi_sqlFile: The original database structure the teacher gave me.

used_package: it contain mysql-connector-java-8.0.17.jar package which is used for JDBC.

Note_when_write_code: My nodes I wrote down while I make this project, It all about code.

#How to run? 
@use terminal to run

~$ cd Src

~$ javac -d ../Package/ CheckExists.java Menu.java QuanLy.java QuanLyDiem.java QuanLyGiaoVien.java QuanLyLop.java QuanLyMonHoc.java QuanLySinhVien.java QuanTriHeThong.java DB_CHOOSE.java

~$ cd ../Package/

~$ java -cp .:../used_package/mysql-connector-java-8.0.17.jar quanLy.QuanLy

#Some knowledges I got while I made this project

@Clear terminal Screen (linux)
System.out.print("\033[H\033[2J");

    'H' means move to top of the screen

    '2J' means "clear entire screen"
