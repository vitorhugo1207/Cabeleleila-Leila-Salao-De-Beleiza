CREATE DATABASE  IF NOT EXISTS `leilasalao` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `leilasalao`;

CREATE USER 'leilasys'@'localhost' Identified by '010203';
GRANT ALL PRIVILEGES ON leilasalao.* to 'leilasys'@'localhost';
flush PRIVILEGES;

-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: leilasalao
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `about` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Dê uma alisada no seu cabelo, no melhor serviço de progressiva da cidade','Progressiva'),(2,'Seu cabelo está quebrando muito? dê uma hidratada!','Hidratamento'),(3,'Pinte o seu cabelo na sua cor favorita!','Pintura');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_schedule`
--

DROP TABLE IF EXISTS `service_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_schedule` (
  `schedule_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  KEY `FK6r9ra1j4fd7r4tjhs01ath885` (`service_id`),
  KEY `FKlypdtyuk9s7vm8s9awamk7nyl` (`schedule_id`),
  CONSTRAINT `FK6r9ra1j4fd7r4tjhs01ath885` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `FKlypdtyuk9s7vm8s9awamk7nyl` FOREIGN KEY (`schedule_id`) REFERENCES `shedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_schedule`
--

LOCK TABLES `service_schedule` WRITE;
/*!40000 ALTER TABLE `service_schedule` DISABLE KEYS */;
INSERT INTO `service_schedule` VALUES (1,1),(1,3),(2,1),(2,3),(3,1),(3,3),(4,1),(4,3),(5,1),(5,3),(6,1),(6,3),(7,1),(7,3),(8,1),(8,3),(9,1),(9,3),(10,1),(10,3),(11,1),(11,3),(12,1),(12,3),(13,1),(13,3),(14,1),(14,3),(15,1),(15,3),(16,1),(16,3),(17,1),(17,3),(18,1),(18,3),(19,1),(19,3),(20,1),(20,3),(21,1),(21,3),(22,1);
/*!40000 ALTER TABLE `service_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shedule`
--

DROP TABLE IF EXISTS `shedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` binary(16) DEFAULT NULL,
  `accepted` bit(1) DEFAULT NULL,
  `scheduled` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK61k7c51cul3oborbr8hpkujxh` (`id_user`),
  CONSTRAINT `FK61k7c51cul3oborbr8hpkujxh` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shedule`
--

LOCK TABLES `shedule` WRITE;
/*!40000 ALTER TABLE `shedule` DISABLE KEYS */;
INSERT INTO `shedule` VALUES (1,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(2,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(3,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(4,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(5,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(6,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(7,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(8,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(9,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(10,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(11,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(12,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(13,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(14,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(15,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(16,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(17,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(18,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(19,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(20,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(21,_binary '\�p\�L�I\�p�A\�``',NULL,NULL),(22,_binary '\�p\�L�I\�p�A\�``',NULL,NULL);
/*!40000 ALTER TABLE `shedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` binary(16) NOT NULL,
  `create_at` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_type` int(11) NOT NULL,
  `api_key` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKihx34vevdcrh4ikbpfqte03v6` (`user_type`),
  CONSTRAINT `FKihx34vevdcrh4ikbpfqte03v6` FOREIGN KEY (`user_type`) REFERENCES `usertype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (_binary '\�p\�L�I\�p�A\�``','2024-03-12 01:41:58.000000','Vitor Hugo','$2a$12$VmUhQ2/f3.0FDAfaUDHKUucnPLheVp/NCU3rhHp9.TpbdPvOu7.o2','vitor',1,_binary '5�\�\\�\�D\r�\�\\�\�Q�');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usertype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `about` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `perm_see_all_scheadule` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` VALUES (1,'Admin, tem a permissão maxima sobre o sistema.','Administrador',_binary '');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'leilasalao'
--

--
-- Dumping routines for database 'leilasalao'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-13 23:39:44
