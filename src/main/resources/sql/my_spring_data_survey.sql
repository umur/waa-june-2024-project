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
-- Table structure for table `survey`
--

DROP TABLE IF EXISTS `survey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `survey` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rate` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK51x6iogwvw5n6pa7sl339ltju` (`user_id`),
  CONSTRAINT `FK51x6iogwvw5n6pa7sl339ltju` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `survey`
--

LOCK TABLES `survey` WRITE;
/*!40000 ALTER TABLE `survey` DISABLE KEYS */;
INSERT INTO `survey` VALUES (1,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Wolf LLC','user01@example.com','Test Hermiston Group',1,'Survey test Fresh',1),(2,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Stark Inc','user01@example.com','Test Klein, Goodwin and Casper',2,'Survey test Frozen',1),(3,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Kiehn and Sons','user01@example.com','Test Carroll Group',3,'Survey test Granite',1),(4,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Kuhlman, Macejkovic and Ward','user01@example.com','Test Corkery and Sons',4,'Survey test Granite',1),(5,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Kirlin - Bayer','user01@example.com','Test Hyatt, Champlin and Graham',4,'Survey test Fresh',1),(6,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Ledner, Wunsch and Brown','user01@example.com','Test Cormier Group',4,'Survey test Rubber',1),(7,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Watsica - Schuster','user01@example.com','Test Swift Inc',4,'Survey test Steel',1),(8,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Feil Inc','user01@example.com','Test Grady - Douglas',4,'Survey test Steel',1),(9,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Ankunding - Zboncak','user01@example.com','Test Terry - Wyman',4,'Survey test Plastic',1),(10,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Senger Inc','user01@example.com','Test Shields LLC',4,'Survey test Rubber',1),(11,'give any idea for the activity 3','2024-07-17','this is the survey test 3 - for student activity Gutmann - Bergnaum','user01@example.com','Test Sauer - Davis',4,'Survey test Plastic',1),(12,'comment 13344','2024-07-17','dasda','user01@example.com','t',2,'test 2132',1);
/*!40000 ALTER TABLE `survey` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-17 21:16:23
