drop database if exists cuoiKi;

create database cuoiKi;

use cuoiKi;

drop table if exists MonHoc;
create table if not exists MonHoc(
MaMH varchar(10) not null primary key,
TenMH varchar(50) not null,
SoTC int not null
);

drop table if exists GiaoVien;
create table if not exists GiaoVien(
MaGV  varchar(10) not null primary key,
HoGV varchar(20) not null,
TenGV varchar(20) not null,
DonVi varchar(50) not null
);
	    
drop table if exists SinhVien;
create table if not exists SinhVien(
MaSV varchar(10) not null primary key,
HoSV varchar(20) not null,
TenSV varchar(20) not null,
NgaySinh date not null,
NoiSinh varchar(20) not null
);
	    
drop table if exists Lop;
create table if not exists Lop(
MaLop varchar(10) not null primary key,
MaMH varchar(10) not null,
NamHoc varchar(10) not null,
HocKy int not null,
MaGV varchar(10) not null,
foreign key (MaMH) references MonHoc(MaMH) on delete cascade on update cascade,
foreign key (MaGV) references GiaoVien(MaGV) on delete cascade on update cascade
);
	    
drop table if exists SinhVienLop;
create table if not exists SinhVienLop(
MaSV varchar(10) not null,
MaLop varchar(10) not null,
Diem double,
primary key(MaSV, MaLop),
foreign key (MaSV) references SinhVien(MaSV) on delete cascade on update cascade,
foreign key (MaLop) references Lop(MaLop) on delete cascade on update cascade
);
