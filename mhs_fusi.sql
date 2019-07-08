# Host: localhost  (Version 5.5.5-10.1.21-MariaDB)
# Date: 2019-06-17 12:01:08
# Generator: MySQL-Front 6.0  (Build 2.20)


#
# Structure for table "dosen"
#

DROP TABLE IF EXISTS `dosen`;
CREATE TABLE `dosen` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `nidn` varchar(255) NOT NULL DEFAULT '',
  `nama_dosen` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

#
# Data for table "dosen"
#

INSERT INTO `dosen` VALUES (1,'1234',' Mia Kamayani, S.T., M.T.'),(2,'1235','Arry Avorizano, S.Kom., M.Kom.');

#
# Structure for table "jurusan"
#

DROP TABLE IF EXISTS `jurusan`;
CREATE TABLE `jurusan` (
  `id_jurusan` bigint(20) NOT NULL AUTO_INCREMENT,
  `nama_jurusan` varchar(255) DEFAULT NULL,
  `deskripsi` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_jurusan`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

#
# Data for table "jurusan"
#

INSERT INTO `jurusan` VALUES (1,'Teknik Elektro','elektronika'),(2,'Teknik Informatika','informatika'),(3,'Teknik Mesin','mesin');

#
# Structure for table "mahasiswa"
#

DROP TABLE IF EXISTS `mahasiswa`;
CREATE TABLE `mahasiswa` (
  `id_mahasiswa` bigint(20) NOT NULL AUTO_INCREMENT,
  `nim` varchar(255) NOT NULL,
  `nama_mahasiswa` varchar(255) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `no_telp` varchar(20) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `id_jurusan` bigint(20) NOT NULL,
  PRIMARY KEY (`id_mahasiswa`),
  UNIQUE KEY `mahsiswa_nim_uindex` (`nim`),
  KEY `mahsiswa_jurusan_id_jurusan_fk` (`id_jurusan`),
  CONSTRAINT `mahsiswa_jurusan_id_jurusan_fk` FOREIGN KEY (`id_jurusan`) REFERENCES `jurusan` (`id_jurusan`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

#
# Data for table "mahasiswa"
#

INSERT INTO `mahasiswa` VALUES (1,'1403015006','Alfi Ramdhani','1996-01-25','083841281477','Rangkasbitung',2),(2,'1503015052','Fahmi Rais','1997-04-12','089649338089','Bekasi',2),(3,'1503015059','Hanifah Febriyana','1997-04-04','082213889407','Bekasi',2),(4,'1503015089','M Andre Prasetya','1997-04-02','08988621276','Bekasi',2),(5,'123456','nada fajar asgiri','2016-09-09','00000000','jakarta',2),(8,'1403015019','puad','1997-01-25','083841281477','depok',2),(11,'6543212','godim','2016-09-09','00000000','jakarta',1);

#
# Structure for table "matakuliah"
#

DROP TABLE IF EXISTS `matakuliah`;
CREATE TABLE `matakuliah` (
  `id_mk` bigint(20) NOT NULL AUTO_INCREMENT,
  `nama_mk` varchar(255) NOT NULL,
  `sks` int(11) NOT NULL,
  PRIMARY KEY (`id_mk`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

#
# Data for table "matakuliah"
#

INSERT INTO `matakuliah` VALUES (1,'Kalkulus',3),(2,'Sistem Basis Data',4),(3,'Kapita Selekta',2),(4,'OOP',4);

#
# Structure for table "nilai"
#

DROP TABLE IF EXISTS `nilai`;
CREATE TABLE `nilai` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_mahasiswa` bigint(20) NOT NULL DEFAULT '0',
  `id_mk` bigint(1) NOT NULL DEFAULT '0',
  `nilai` varchar(255) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `id_mahasiswa` (`id_mahasiswa`),
  KEY `id_mk` (`id_mk`),
  CONSTRAINT `nilai_ibfk_1` FOREIGN KEY (`id_mahasiswa`) REFERENCES `mahasiswa` (`id_mahasiswa`),
  CONSTRAINT `nilai_ibfk_2` FOREIGN KEY (`id_mk`) REFERENCES `matakuliah` (`id_mk`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

#
# Data for table "nilai"
#

INSERT INTO `nilai` VALUES (1,1,2,'3'),(2,1,3,'4'),(3,1,4,'4'),(4,2,2,'3'),(5,2,3,'2'),(6,2,4,'4'),(7,3,1,'3'),(8,3,2,'3'),(9,3,4,'3'),(10,4,1,'4'),(11,4,2,'2'),(12,4,3,'4'),(13,1,1,'3'),(14,8,2,'3'),(15,8,1,'3');

#
# Structure for table "user_dosen"
#

DROP TABLE IF EXISTS `user_dosen`;
CREATE TABLE `user_dosen` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `id_dosen` bigint(1) NOT NULL DEFAULT '0',
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `role` varchar(255) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Data for table "user_dosen"
#

INSERT INTO `user_dosen` VALUES (1,1,'dosen','dosen','dosen');

#
# Structure for table "user_mahasiswa"
#

DROP TABLE IF EXISTS `user_mahasiswa`;
CREATE TABLE `user_mahasiswa` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `id_mahasiswa` bigint(20) NOT NULL DEFAULT '0',
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'mahasiswa',
  PRIMARY KEY (`id`),
  KEY `id_mahasiswa` (`id_mahasiswa`),
  CONSTRAINT `user_mahasiswa_ibfk_1` FOREIGN KEY (`id_mahasiswa`) REFERENCES `mahasiswa` (`id_mahasiswa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Data for table "user_mahasiswa"
#

INSERT INTO `user_mahasiswa` VALUES (1,1,'user','user','mahasiswa');

#
# Procedure "showIP"
#

DROP PROCEDURE IF EXISTS `showIP`;
CREATE PROCEDURE `showIP`()
    NO SQL
BEGIN

SELECT mahasiswa.nim, mahasiswa.nama_mahasiswa, (YEAR(CURDATE()) - YEAR(mahasiswa.tgl_lahir )) as umur, sum(matakuliah.sks) as jumlah_sks, sum(matakuliah.sks*nilai.nilai) as mutu, (sum(matakuliah.sks*nilai.nilai)/sum(matakuliah.sks)) as 'ips(mutu/sks)' from mahasiswa join nilai on nilai.id_mahasiswa = mahasiswa.id_mahasiswa JOIN matakuliah on matakuliah.id_mk = nilai.id_mk GROUP by mahasiswa.id_mahasiswa;

END;
