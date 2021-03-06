-- MySQL dump 10.13  Distrib 5.7.12, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: bbs
-- ------------------------------------------------------
-- Server version	5.7.19-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bbs`
--

DROP TABLE IF EXISTS `bbs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bbs` (
  `bbsID` int(11) NOT NULL,
  `bbsTitle` varchar(50) DEFAULT NULL,
  `userID` varchar(20) DEFAULT NULL,
  `bbsDate` datetime DEFAULT NULL,
  `bbsContent` varchar(2048) DEFAULT NULL,
  `bbsAvailable` int(11) DEFAULT NULL,
  PRIMARY KEY (`bbsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bbs`
--

LOCK TABLES `bbs` WRITE;
/*!40000 ALTER TABLE `bbs` DISABLE KEYS */;
INSERT INTO `bbs` VALUES (1,'dsfsdf','777','2017-08-02 13:34:45','sdfsdf',1),(2,'안녕','777','2017-08-02 13:35:44','안녕하세욘ㅇㄹㄴㅇㄹㄴㅇㅇㄴㄹ',1),(3,'ㅇㄹㅇㄴㄹ','777','2017-08-06 23:38:58','ㄴㅇㄹㅇㄴㄹㄴ',1),(4,'ㄴㅇㄹㅇㄴ','777','2017-08-06 23:39:04','ㄴㄹㅇㄴㄹ',1),(5,'ㄴㅇㄹㅇㄴ','777','2017-08-06 23:39:06','ㄴㄹㅇㄴㄹ',1),(6,'ㄴㅇㄹㅇㄴ','777','2017-08-06 23:39:08','ㄴㄹㅇㄴㄹ',1),(7,'ㄴㅇㄹㅇㄴ','777','2017-08-06 23:39:11','ㄴㄹㅇㄴㄹ',1),(8,'ㄴㅇㄹㅇㄴ','777','2017-08-06 23:39:13','ㄴㄹㅇㄴㄹ',1),(9,'ㄴㅇㄹㅇㄴ','777','2017-08-06 23:39:16','ㄴㄹㅇㄴㄹ',1),(10,'ㄴㅇㄹㅇㄴ','777','2017-08-06 23:39:19','ㄴㄹㅇㄴㄹ',1),(11,'ㄴㅇㄹㅇㄴ','777','2017-08-06 23:39:21','ㄴㄹㅇㄴㄹ',1),(12,'sadsfsdf','123','2017-08-07 10:13:08','assfsdf\r\n<DS.dsfsdf\r\n(sdfsdf.sdfsdf(qwe\r\n\r\nsf#####3!!',1),(13,'<script> alert(\"hello world!!); </script>','123','2017-08-07 10:16:40','fff',1),(14,'<script> alert(\"hello world!!\"); </script>','123','2017-08-07 10:20:38','sfsdf',1),(15,'434234','777','2017-08-07 10:34:10','24324234324',1);
/*!40000 ALTER TABLE `bbs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` varchar(20) NOT NULL,
  `userPassword` varchar(20) DEFAULT NULL,
  `userName` varchar(20) DEFAULT NULL,
  `userGender` varchar(20) DEFAULT NULL,
  `userEmail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('11111','1111','11111','여자','sfdsfs@gmail.com'),('123','123','123','여자','123@123.com'),('1234','1234','1234','여자','1234@1234.com'),('777','777','777','여자','777@777.com'),('asdf','asdf','11111','여자','sfdsfs@naver.com'),('dddddddd','dddddddd','dddddd','여자','dddd@naver.com'),('dfgg','dfgg','dfgg','여자','dfgg@dfgg.com'),('dfggsdf','sfdfdf','dfgg','여자','dfgg@dfgg.com'),('gildong','123456','홍길동','남자','gildong@naver.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-07 11:06:27
