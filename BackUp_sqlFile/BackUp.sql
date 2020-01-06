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
INSERT INTO `GiaoVien` VALUES ('GV001','Phuong','Nguyen Hong','SoICT'),('GV002','Duc','Nguyen Huu','SoICT'),('GV003','Huong','Le Thanh','SoICT');
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
INSERT INTO `Lop` VALUES ('L001','MH001','2019',1,'GV001'),('L002','MH002','2019',1,'GV002'),('L003','MH003','2019',1,'GV003');
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
INSERT INTO `MonHoc` VALUES ('MH001','Tin Hoc Dai Cuong',4),('MH002','Co So Du Lieu',3),('MH003','Tri Tue Nhan Tao',3);
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
INSERT INTO `SinhVien` VALUES ('SV001','Nguyen Van','A','0220-01-01','Ha Noi'),('SV002','Nguyen Van','B','0220-01-02','Ha Noi'),('SV003','Nguyen Van','C','0220-01-03','Ha Noi'),('SV004','Pham Thi','D','0220-01-04','Ha Noi');
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
  `Diem` double DEFAULT NULL,
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
INSERT INTO `SinhVienLop` VALUES ('SV001','L001',7),('SV001','L002',7),('SV001','L003',7),('SV002','L001',6),('SV002','L002',6),('SV002','L003',6),('SV003','L001',9),('SV003','L002',9),('SV004','L003',8);
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

-- Dump completed on 2020-01-06 18:15:22
