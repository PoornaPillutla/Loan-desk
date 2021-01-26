-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: loandeskdb
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.16.04.1

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
-- Table structure for table `Customer`
--

DROP TABLE IF EXISTS `Customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `full_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone_number` varchar(10) NOT NULL,
  `address` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`,`phone_number`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Customer`
--

LOCK TABLES `Customer` WRITE;
/*!40000 ALTER TABLE `Customer` DISABLE KEYS */;
INSERT INTO `Customer` VALUES (1,'surya.krishnan','surya.krishanan@gmail.com','9959919701','whitefields,kondapur'),(2,'surya.krishnan','surya.krishanan@gmail.com','9959919702','whitefields,kondapur'),(3,'akhila dhanunjaya','akhila.dhanunjaya@gmail.com','9959929709','whitefields,kondapur'),(4,'ram charan','cherry.cherry@gmail.com','9959929705','hyderabad,telangana'),(5,'balayya','balayya.nbk@gmail.com','8919935601','jub'),(6,'balayya1','balayya.nbk1@gmail.com','8919935602','whitefields,kondapur'),(7,'bahubali','bahubali.mahendra@gmail.com','8919935600','mahismathi'),(8,'indiana jones','groundbreaker678@gmail.com','9948483220','hyderabad,telangana'),(9,'indiana jones','bahubali.mahendra@gmail.com','9948483222','mahismathi'),(10,'balayya','bahubali.mahendra@gmail.com','9948483223','mahismathi'),(11,'indiana jones','indiana.jones@gmail.com','9948483224','whitefields,kondapur'),(12,'indiana jones','akhila.dhanunjaya@gmail.com','9948483225','whitefields,kondapur'),(13,'chiranjeevi','megastar.chiranjeevi@gmail.com','9849229021','jub'),(14,'rana daggubati','rana.rana@gmai.com','9849229022','pune,maharashtra'),(15,'amaraendra','akhila.dhanunjaya@gmail.com','9999888822','mahismathi');
/*!40000 ALTER TABLE `Customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loan_application`
--

DROP TABLE IF EXISTS `loan_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loan_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(100) DEFAULT NULL,
  `salary` varchar(45) DEFAULT NULL,
  `emi` varchar(45) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_id_idx` (`customer_id`),
  CONSTRAINT `fk_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loan_application`
--

LOCK TABLES `loan_application` WRITE;
/*!40000 ALTER TABLE `loan_application` DISABLE KEYS */;
INSERT INTO `loan_application` VALUES (1,'geetha.inc','30000','9000',1),(2,'geetha.inc','30000','9000',2),(3,'geetha.inc','30000','9000',1),(4,'rrj.inc','24500','3500',2),(5,'warangal','90000','15000',12),(6,'konidela inc','90000','5611',13),(7,'chitramala','1299120','456',14),(8,'warangal','90000','15000',15);
/*!40000 ALTER TABLE `loan_application` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-08-25 20:01:14
