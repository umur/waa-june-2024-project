-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (x86_64)
--
-- Host: 127.0.0.1    Database: my_spring_data
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `size` bigint DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgverld5ylkmwkhca8tcjwggps` (`user_id`),
  CONSTRAINT `FKgverld5ylkmwkhca8tcjwggps` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES (3,'this is the descritpion eqeqw 433323','4d32eb4c-e307-4cfc-b27e-35760892b99b.png',750494,'image/png','http://localhost:8080/resources/4d32eb4c-e307-4cfc-b27e-35760892b99b.png',1),(4,'this is the descritpion eqeqw ytrtyrtr','f90105e5-d0de-4350-90d8-0c0698a3dfbe.png',750494,'image/png','http://localhost:8080/resources/f90105e5-d0de-4350-90d8-0c0698a3dfbe.png',1),(5,'this is the descritpion eqeqw 7775433','1e8dec17-e811-46ea-80a5-443421efab12.png',750494,'image/png','http://localhost:8080/resources/1e8dec17-e811-46ea-80a5-443421efab12.png',1),(6,'toan updated here 21312313','fc3313b3-4708-470e-b7be-dfb18ed6e8e5.png',750494,'image/png','http://localhost:8080/resources/fc3313b3-4708-470e-b7be-dfb18ed6e8e5.png',1),(7,'team SMT is doing here','8dfc0bdf-de71-4661-8e78-98b1b01fc78d.png',750494,'image/png','http://localhost:8080/resources/8dfc0bdf-de71-4661-8e78-98b1b01fc78d.png',1),(8,'team SMT is doing here','b004ae6c-bdb9-455e-8c99-6fb8b3fc4d36.png',750494,'image/png','http://localhost:8080/resources/b004ae6c-bdb9-455e-8c99-6fb8b3fc4d36.png',1);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-17 21:16:21
