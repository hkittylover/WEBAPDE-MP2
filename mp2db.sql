CREATE DATABASE  IF NOT EXISTS `mp2db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mp2db`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: mp2db
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `allowedusers`
--

DROP TABLE IF EXISTS `allowedusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allowedusers` (
  `photoId` int(11) NOT NULL,
  `allowedUser` varchar(255) DEFAULT NULL,
  KEY `FK6d9pa1qew1p33kiv9y1q66aut` (`photoId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allowedusers`
--

LOCK TABLES `allowedusers` WRITE;
/*!40000 ALTER TABLE `allowedusers` DISABLE KEYS */;
/*!40000 ALTER TABLE `allowedusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photo` (
  `photoId` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `filepath` varchar(255) DEFAULT NULL,
  `privacy` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`photoId`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` VALUES (1,'Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)','A picture of the game.','img/sv.png','public','StarDew'),(2,'Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)','A picture of the game.','img/sv.png','public','StarDew'),(3,'Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)','A picture of a POGI.','img/j.jpg','public','Gentleman'),(4,'Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)','A picture of the game.','img/sv.png','public','StarDew'),(5,'Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)','A picture of a POGI.','img/j.jpg','public','Gentleman'),(6,'Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)','A picture of the game.','img/sv.png','public','StarDew'),(7,'Thu Jul 27 2017 18:10:09 GMT+0800 (Malay Peninsula Standard Time)','A picture of a POGI.','img/j.jpg','public','Gentleman');
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tagphoto`
--

DROP TABLE IF EXISTS `tagphoto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tagphoto` (
  `photoId` int(11) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  KEY `FKc67pwxjb374suonl7j2yhdty` (`photoId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tagphoto`
--

LOCK TABLES `tagphoto` WRITE;
/*!40000 ALTER TABLE `tagphoto` DISABLE KEYS */;
INSERT INTO `tagphoto` VALUES (3,'Cute');
/*!40000 ALTER TABLE `tagphoto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('mae','','allyzahehe');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userphoto`
--

DROP TABLE IF EXISTS `userphoto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userphoto` (
  `username` varchar(255) DEFAULT NULL,
  `photoId` int(11) NOT NULL,
  PRIMARY KEY (`photoId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userphoto`
--

LOCK TABLES `userphoto` WRITE;
/*!40000 ALTER TABLE `userphoto` DISABLE KEYS */;
INSERT INTO `userphoto` VALUES ('mae',1),('mae',2),('mae',3),('mae',4),('mae',5),('mae',6),('mae',7);
/*!40000 ALTER TABLE `userphoto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-28  1:42:22
