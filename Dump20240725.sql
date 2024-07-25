-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: paris_sportif
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `match`
--

DROP TABLE IF EXISTS `match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `match` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_heure` datetime(6) DEFAULT NULL,
  `equipea` varchar(255) DEFAULT NULL,
  `equipeb` varchar(255) DEFAULT NULL,
  `resultat` varchar(255) DEFAULT NULL,
  `sport_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk0gekrr6aj8mx8xj0og8572wa` (`sport_id`),
  CONSTRAINT `FKk0gekrr6aj8mx8xj0og8572wa` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match`
--

LOCK TABLES `match` WRITE;
/*!40000 ALTER TABLE `match` DISABLE KEYS */;
INSERT INTO `match` VALUES (5,'2024-07-16 14:30:00.000000','Equipe A','Equipe B','victoire',1),(6,'2024-07-16 14:30:00.000000','Equipe A','Equipe B','victoire',1),(7,'2024-07-16 14:30:00.000000','Equipe A','Equipe B','victoire',1);
/*!40000 ALTER TABLE `match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_entity`
--

DROP TABLE IF EXISTS `match_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `match_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `equipea` varchar(255) DEFAULT NULL,
  `equipeb` varchar(255) DEFAULT NULL,
  `resultat_equipea` varchar(255) DEFAULT NULL,
  `resultat_equipeb` varchar(255) DEFAULT NULL,
  `sport_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjupd83mx9acvjrkc7ncnubmp7` (`sport_id`),
  CONSTRAINT `FKjupd83mx9acvjrkc7ncnubmp7` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_entity`
--

LOCK TABLES `match_entity` WRITE;
/*!40000 ALTER TABLE `match_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `match_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paiement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date_heure` datetime(6) DEFAULT NULL,
  `montant` double NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `parieur_id` bigint DEFAULT NULL,
  `utilisateur_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK65t9jjiig1p743bx884p1iko8` (`parieur_id`),
  KEY `FK33phkv6oefby5j5b1v2v5f4oo` (`utilisateur_id`),
  CONSTRAINT `FK33phkv6oefby5j5b1v2v5f4oo` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`),
  CONSTRAINT `FK65t9jjiig1p743bx884p1iko8` FOREIGN KEY (`parieur_id`) REFERENCES `parieur` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paiement`
--

LOCK TABLES `paiement` WRITE;
/*!40000 ALTER TABLE `paiement` DISABLE KEYS */;
/*!40000 ALTER TABLE `paiement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pari`
--

DROP TABLE IF EXISTS `pari`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pari` (
  `id` bigint NOT NULL,
  `cote` double NOT NULL,
  `montant` double NOT NULL,
  `resultat` varchar(255) DEFAULT NULL,
  `type_pari` varchar(255) DEFAULT NULL,
  `match_id` bigint DEFAULT NULL,
  `parieur_id` bigint DEFAULT NULL,
  `gagne` bit(1) NOT NULL,
  `montant_gagne` double NOT NULL,
  `utilisateur_id` bigint DEFAULT NULL,
  `match_entity_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKepagfmgmp34n7ra7xno5tv08x` (`parieur_id`),
  KEY `FKbg5ix2xdh7wynx1ju3fikdu7h` (`match_id`),
  KEY `FK9lu790up9d9yxotlsvfjj1pg2` (`utilisateur_id`),
  KEY `FKbd9010vb9a3gm9scrp3vlvfqm` (`match_entity_id`),
  CONSTRAINT `FK9lu790up9d9yxotlsvfjj1pg2` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`),
  CONSTRAINT `FKbd9010vb9a3gm9scrp3vlvfqm` FOREIGN KEY (`match_entity_id`) REFERENCES `match_entity` (`id`),
  CONSTRAINT `FKbg5ix2xdh7wynx1ju3fikdu7h` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`),
  CONSTRAINT `FKepagfmgmp34n7ra7xno5tv08x` FOREIGN KEY (`parieur_id`) REFERENCES `parieur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pari`
--

LOCK TABLES `pari` WRITE;
/*!40000 ALTER TABLE `pari` DISABLE KEYS */;
/*!40000 ALTER TABLE `pari` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parieur`
--

DROP TABLE IF EXISTS `parieur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parieur` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `solde` double NOT NULL,
  `utilisateur_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9uj76oqh25tssny4y37nc1ix` (`utilisateur_id`),
  CONSTRAINT `FKox6uft697q7o8e9ikpe5xbaq` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parieur`
--

LOCK TABLES `parieur` WRITE;
/*!40000 ALTER TABLE `parieur` DISABLE KEYS */;
/*!40000 ALTER TABLE `parieur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sport`
--

DROP TABLE IF EXISTS `sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sport` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport`
--

LOCK TABLES `sport` WRITE;
/*!40000 ALTER TABLE `sport` DISABLE KEYS */;
INSERT INTO `sport` VALUES (1,'Football');
/*!40000 ALTER TABLE `sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistique`
--

DROP TABLE IF EXISTS `statistique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statistique` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `equipe` varchar(255) DEFAULT NULL,
  `gagne` bit(1) NOT NULL,
  `resultat_match` varchar(255) DEFAULT NULL,
  `match_id` bigint DEFAULT NULL,
  `match_entity_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKawaqtdco7s61h3mgr496wyih1` (`match_entity_id`),
  CONSTRAINT `FKawaqtdco7s61h3mgr496wyih1` FOREIGN KEY (`match_entity_id`) REFERENCES `match_entity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistique`
--

LOCK TABLES `statistique` WRITE;
/*!40000 ALTER TABLE `statistique` DISABLE KEYS */;
/*!40000 ALTER TABLE `statistique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `solde` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (1,'testuser@example.com','testuser',NULL,NULL,0);
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-25 22:49:29
