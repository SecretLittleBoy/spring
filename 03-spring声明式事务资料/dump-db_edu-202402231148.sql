-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (arm64)
--
-- Host: localhost    Database: db_edu
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Temporary view structure for view `bt_s`
--

DROP TABLE IF EXISTS `bt_s`;
/*!50001 DROP VIEW IF EXISTS `bt_s`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `bt_s` AS SELECT 
 1 AS `sno`,
 1 AS `sname`,
 1 AS `sbirth`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `cno` bigint NOT NULL COMMENT '课程号',
  `cname` varchar(100) DEFAULT NULL COMMENT '课程名',
  `cpno` bigint DEFAULT NULL COMMENT '先行课',
  `ccredit` int DEFAULT NULL COMMENT '学分',
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'数据库',5,4),(2,'数学',NULL,2),(3,'信息系统',1,4),(4,'操作系统',6,3),(5,'数据结构',7,4),(6,'数据处理',NULL,2),(7,'PASCAL语言',6,4),(8,'DBADesign',NULL,3);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dept_age`
--

DROP TABLE IF EXISTS `dept_age`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept_age` (
  `sdept` char(15) DEFAULT NULL,
  `avg_age` smallint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept_age`
--

LOCK TABLES `dept_age` WRITE;
/*!40000 ALTER TABLE `dept_age` DISABLE KEYS */;
INSERT INTO `dept_age` VALUES ('CS',20),('MA',23),('IS',22);
/*!40000 ALTER TABLE `dept_age` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `f_student`
--

DROP TABLE IF EXISTS `f_student`;
/*!50001 DROP VIEW IF EXISTS `f_student`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `f_student` AS SELECT 
 1 AS `f_sno`,
 1 AS `name`,
 1 AS `sex`,
 1 AS `age`,
 1 AS `dept`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `is_s1`
--

DROP TABLE IF EXISTS `is_s1`;
/*!50001 DROP VIEW IF EXISTS `is_s1`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `is_s1` AS SELECT 
 1 AS `sno`,
 1 AS `sname`,
 1 AS `grade`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `is_s2`
--

DROP TABLE IF EXISTS `is_s2`;
/*!50001 DROP VIEW IF EXISTS `is_s2`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `is_s2` AS SELECT 
 1 AS `sno`,
 1 AS `sname`,
 1 AS `grade`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `is_student`
--

DROP TABLE IF EXISTS `is_student`;
/*!50001 DROP VIEW IF EXISTS `is_student`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `is_student` AS SELECT 
 1 AS `sno`,
 1 AS `sname`,
 1 AS `sage`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `s_g`
--

DROP TABLE IF EXISTS `s_g`;
/*!50001 DROP VIEW IF EXISTS `s_g`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `s_g` AS SELECT 
 1 AS `sno`,
 1 AS `sgrad`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `sc`
--

DROP TABLE IF EXISTS `sc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sc` (
  `sno` bigint NOT NULL COMMENT '学号',
  `cno` bigint NOT NULL,
  `grade` int DEFAULT NULL COMMENT '成绩',
  PRIMARY KEY (`sno`,`cno`),
  KEY `sc_FK_1` (`cno`),
  CONSTRAINT `sc_FK` FOREIGN KEY (`sno`) REFERENCES `student` (`sno`),
  CONSTRAINT `sc_FK_1` FOREIGN KEY (`cno`) REFERENCES `course` (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sc`
--

LOCK TABLES `sc` WRITE;
/*!40000 ALTER TABLE `sc` DISABLE KEYS */;
INSERT INTO `sc` VALUES (201215121,1,88),(201215121,2,90),(201215122,2,65),(201215123,3,70),(201215125,1,87);
/*!40000 ALTER TABLE `sc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `sno` bigint NOT NULL COMMENT '学号',
  `sname` varchar(100) DEFAULT NULL COMMENT '姓名',
  `ssex` char(1) DEFAULT NULL COMMENT '性别',
  `sage` int DEFAULT NULL COMMENT '年龄',
  `sdept` varchar(100) DEFAULT NULL COMMENT '所在系',
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (0,'xiaoming0','男',20,'CS'),(1,'xiaoming1','男',20,'CS'),(2,'xiaoming2','男',20,'CS'),(3,'xiaoming3','男',20,'CS'),(4,'xiaoming4','男',20,'CS'),(10011101,'小王','男',30,'CS'),(201215121,'李勇','男',26,'CS'),(201215122,'刘晨','女',19,'CS'),(201215123,'王敏','女',19,'MA'),(201215125,'刘辰','男',21,'IS'),(201215138,'陈冬东','男',19,NULL),(201215168,'小钱','男',56,'MA');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_account`
--

DROP TABLE IF EXISTS `t_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_account` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `money` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_account`
--

LOCK TABLES `t_account` WRITE;
/*!40000 ALTER TABLE `t_account` DISABLE KEYS */;
INSERT INTO `t_account` VALUES (1,'xiaoming',5000),(2,'xiaohong',5000),(3,'xiaoqiang',5000),(4,'xiaolei',5000),(5,'xiaoliu',5000);
/*!40000 ALTER TABLE `t_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `t_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'xiaoming','123456'),(2,'小红','123456'),(3,'小红','123456');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'db_edu'
--

--
-- Final view structure for view `bt_s`
--

/*!50001 DROP VIEW IF EXISTS `bt_s`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `bt_s` (`sno`,`sname`,`sbirth`) AS select `student`.`sno` AS `sno`,`student`.`sname` AS `sname`,(2023 - `student`.`sage`) AS `2023-sage` from `student` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `f_student`
--

/*!50001 DROP VIEW IF EXISTS `f_student`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `f_student` (`f_sno`,`name`,`sex`,`age`,`dept`) AS select `student`.`sno` AS `sno`,`student`.`sname` AS `sname`,`student`.`ssex` AS `ssex`,`student`.`sage` AS `sage`,`student`.`sdept` AS `sdept` from `student` where (`student`.`ssex` = '女') */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `is_s1`
--

/*!50001 DROP VIEW IF EXISTS `is_s1`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `is_s1` (`sno`,`sname`,`grade`) AS select `s`.`sno` AS `sno`,`s`.`sname` AS `sname`,`sc`.`grade` AS `grade` from (`student` `s` join `sc`) where ((`s`.`sno` = `sc`.`sno`) and (`sc`.`cno` = 1)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `is_s2`
--

/*!50001 DROP VIEW IF EXISTS `is_s2`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `is_s2` AS select `is_s1`.`sno` AS `sno`,`is_s1`.`sname` AS `sname`,`is_s1`.`grade` AS `grade` from `is_s1` where (`is_s1`.`grade` >= 90) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `is_student`
--

/*!50001 DROP VIEW IF EXISTS `is_student`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `is_student` AS select `student`.`sno` AS `sno`,`student`.`sname` AS `sname`,`student`.`sage` AS `sage` from `student` where (`student`.`sdept` = 'IS') */
/*!50002 WITH CASCADED CHECK OPTION */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `s_g`
--

/*!50001 DROP VIEW IF EXISTS `s_g`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `s_g` (`sno`,`sgrad`) AS select `sc`.`sno` AS `sno`,avg(`sc`.`grade`) AS `avg(grade)` from `sc` group by `sc`.`sno` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-23 11:48:47
