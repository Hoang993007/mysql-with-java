-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: cuoiKi
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB-1:10.4.11+maria~bionic-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `GiaoVien`
--

DROP TABLE IF EXISTS `GiaoVien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GiaoVien` (
  `MaGV` varchar(10) NOT NULL,
  `HoGV` varchar(20) NOT NULL,
  `TenGV` varchar(20) NOT NULL,
  `DonVi` varchar(50) NOT NULL,
  PRIMARY KEY (`MaGV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GiaoVien`
--

LOCK TABLES `GiaoVien` WRITE;
/*!40000 ALTER TABLE `GiaoVien` DISABLE KEYS */;
INSERT INTO `GiaoVien` VALUES ('GV001','Tran','Trung Duc','DHBKHN'),('GV002','Nguyen','Van A','DHBKHN');
/*!40000 ALTER TABLE `GiaoVien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Lop`
--

DROP TABLE IF EXISTS `Lop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Lop` (
  `MaLop` varchar(10) NOT NULL,
  `MaMH` varchar(10) NOT NULL,
  `NamHoc` varchar(10) NOT NULL,
  `HocKy` int(11) NOT NULL,
  `MaGV` varchar(10) NOT NULL,
  PRIMARY KEY (`MaLop`),
  KEY `MaMH` (`MaMH`),
  KEY `MaGV` (`MaGV`),
  CONSTRAINT `Lop_ibfk_1` FOREIGN KEY (`MaMH`) REFERENCES `MonHoc` (`MaMH`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `Lop_ibfk_2` FOREIGN KEY (`MaGV`) REFERENCES `GiaoVien` (`MaGV`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Lop`
--

LOCK TABLES `Lop` WRITE;
/*!40000 ALTER TABLE `Lop` DISABLE KEYS */;
INSERT INTO `Lop` VALUES ('L001','MH001','0000-0000',1,'GV001');
/*!40000 ALTER TABLE `Lop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MonHoc`
--

DROP TABLE IF EXISTS `MonHoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MonHoc` (
  `MaMH` varchar(10) NOT NULL,
  `TenMH` varchar(50) NOT NULL,
  `SoTC` int(11) NOT NULL,
  PRIMARY KEY (`MaMH`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MonHoc`
--

LOCK TABLES `MonHoc` WRITE;
/*!40000 ALTER TABLE `MonHoc` DISABLE KEYS */;
INSERT INTO `MonHoc` VALUES ('MH001','MonHoc1',1),('MH002','MonHoc2',1),('MH003','MonHoc2',2),('MH004','MonHoc2',1),('MH005','MonHoc3',2),('MH006','MonHoc4',5),('MH007','MonHoc6',3),('MH008','MonHoc1',1),('MH009','MonHoc4',2),('MH010','MonHoc6',3),('MH012','MonHoc12',3),('MH013','x',2),('MH015','MonHoc15',2);
/*!40000 ALTER TABLE `MonHoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SinhVien`
--

DROP TABLE IF EXISTS `SinhVien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SinhVien` (
  `MaSV` varchar(10) NOT NULL,
  `HoSV` varchar(20) NOT NULL,
  `TenSV` varchar(20) NOT NULL,
  `NgaySinh` date NOT NULL,
  `NoiSinh` varchar(20) NOT NULL,
  PRIMARY KEY (`MaSV`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SinhVien`
--

LOCK TABLES `SinhVien` WRITE;
/*!40000 ALTER TABLE `SinhVien` DISABLE KEYS */;
INSERT INTO `SinhVien` VALUES ('SV001','Nguyen','Minh Trang','1999-03-12','Ha Noi'),('SV002','Truong','Cong Toan','1999-11-23','Thanh Hoa'),('SV003','Nguyen','Bao Duc','1999-11-11','Ha Noi');
/*!40000 ALTER TABLE `SinhVien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SinhVienLop`
--

DROP TABLE IF EXISTS `SinhVienLop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SinhVienLop` (
  `MaSV` varchar(10) NOT NULL,
  `MaLop` varchar(10) NOT NULL,
  `Diem` int(11) DEFAULT NULL,
  PRIMARY KEY (`MaSV`,`MaLop`),
  KEY `MaLop` (`MaLop`),
  CONSTRAINT `SinhVienLop_ibfk_1` FOREIGN KEY (`MaSV`) REFERENCES `SinhVien` (`MaSV`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `SinhVienLop_ibfk_2` FOREIGN KEY (`MaLop`) REFERENCES `Lop` (`MaLop`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SinhVienLop`
--

LOCK TABLES `SinhVienLop` WRITE;
/*!40000 ALTER TABLE `SinhVienLop` DISABLE KEYS */;
INSERT INTO `SinhVienLop` VALUES ('SV001','L001',10),('SV002','L001',NULL);
/*!40000 ALTER TABLE `SinhVienLop` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-02 20:23:45
