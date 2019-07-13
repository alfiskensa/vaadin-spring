-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 13, 2019 at 03:13 PM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mhs_fusi`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`hseauto`@`%` PROCEDURE `showIP` ()  NO SQL
BEGIN

SELECT mahasiswa.nim, mahasiswa.nama_mahasiswa, (YEAR(CURDATE()) - YEAR(mahasiswa.tgl_lahir )) as umur, sum(matakuliah.sks) as jumlah_sks, sum(matakuliah.sks*nilai.nilai) as mutu, (sum(matakuliah.sks*nilai.nilai)/sum(matakuliah.sks)) as 'ips(mutu/sks)' from mahasiswa join nilai on nilai.id_mahasiswa = mahasiswa.id_mahasiswa JOIN matakuliah on matakuliah.id_mk = nilai.id_mk GROUP by mahasiswa.id_mahasiswa;

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `dosen`
--

CREATE TABLE `dosen` (
  `id` bigint(11) NOT NULL,
  `nidn` varchar(255) NOT NULL DEFAULT '',
  `nama_dosen` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dosen`
--

INSERT INTO `dosen` (`id`, `nidn`, `nama_dosen`) VALUES
(1, '1234', ' Mia Kamayani, S.T., M.T.'),
(2, '1235', 'Arry Avorizano, S.Kom., M.Kom.');

-- --------------------------------------------------------

--
-- Table structure for table `jurusan`
--

CREATE TABLE `jurusan` (
  `id_jurusan` bigint(20) NOT NULL,
  `nama_jurusan` varchar(255) DEFAULT NULL,
  `deskripsi` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jurusan`
--

INSERT INTO `jurusan` (`id_jurusan`, `nama_jurusan`, `deskripsi`) VALUES
(1, 'Teknik Elektro', 'elektronika'),
(2, 'Teknik Informatika', 'informatika'),
(3, 'Teknik Mesin', 'mesin'),
(4, 'Teknik Kimia', '');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `id_mahasiswa` bigint(20) NOT NULL,
  `nim` varchar(255) NOT NULL,
  `nama_mahasiswa` varchar(255) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `no_telp` varchar(20) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `id_jurusan` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`id_mahasiswa`, `nim`, `nama_mahasiswa`, `tgl_lahir`, `no_telp`, `alamat`, `id_jurusan`) VALUES
(1, '1403015006', 'Alfi Ramdhani', '1996-01-25', '083841281477', 'Rangkasbitung', 2),
(2, '1503015052', 'Fahmi Rais', '1997-04-12', '089649338089', 'Bekasi', 2),
(3, '1503015059', 'Hanifah Febriyana', '1997-04-04', '082213889407', 'Bekasi', 2),
(4, '1503015089', 'M Andre Prasetya', '1997-04-02', '08988621276', 'Bekasi', 2),
(5, '123456', 'nada fajar asgiri', '2016-09-09', '00000000', 'jakarta', 2),
(8, '1403015019', 'puad', '1997-01-25', '083841281477', 'depok', 2),
(11, '6543212', 'godim', '2016-09-09', '00000000', 'jakarta', 1);

-- --------------------------------------------------------

--
-- Table structure for table `matakuliah`
--

CREATE TABLE `matakuliah` (
  `id_mk` bigint(20) NOT NULL,
  `nama_mk` varchar(255) NOT NULL,
  `sks` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matakuliah`
--

INSERT INTO `matakuliah` (`id_mk`, `nama_mk`, `sks`) VALUES
(1, 'RPL', 4),
(2, 'Sistem Basis Data', 4),
(3, 'Kapita Selekta', 2),
(4, 'OOP', 4),
(5, 'RPL', 4);

-- --------------------------------------------------------

--
-- Table structure for table `nilai`
--

CREATE TABLE `nilai` (
  `id` int(11) NOT NULL,
  `id_mahasiswa` bigint(20) NOT NULL DEFAULT 0,
  `id_mk` bigint(1) NOT NULL DEFAULT 0,
  `nilai` varchar(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nilai`
--

INSERT INTO `nilai` (`id`, `id_mahasiswa`, `id_mk`, `nilai`) VALUES
(2, 1, 3, '4'),
(3, 1, 4, '4'),
(4, 2, 2, '3'),
(5, 2, 3, '2'),
(6, 2, 4, '4'),
(7, 3, 1, '3'),
(8, 3, 2, '3'),
(9, 3, 4, '3'),
(10, 4, 1, '4'),
(11, 4, 2, '2'),
(12, 4, 3, '4'),
(13, 1, 1, '3'),
(14, 8, 2, '3'),
(15, 8, 1, '3'),
(16, 1, 2, '0');

-- --------------------------------------------------------

--
-- Table structure for table `user_dosen`
--

CREATE TABLE `user_dosen` (
  `id` bigint(11) NOT NULL,
  `id_dosen` bigint(1) NOT NULL DEFAULT 0,
  `username` varchar(255) NOT NULL DEFAULT '',
  `password` varchar(255) DEFAULT '',
  `role` varchar(255) DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_dosen`
--

INSERT INTO `user_dosen` (`id`, `id_dosen`, `username`, `password`, `role`) VALUES
(1, 1, 'dosen', 'dosen', 'dosen');

-- --------------------------------------------------------

--
-- Table structure for table `user_mahasiswa`
--

CREATE TABLE `user_mahasiswa` (
  `id` bigint(11) NOT NULL,
  `id_mahasiswa` bigint(20) NOT NULL DEFAULT 0,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT 'mahasiswa'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_mahasiswa`
--

INSERT INTO `user_mahasiswa` (`id`, `id_mahasiswa`, `username`, `password`, `role`) VALUES
(1, 1, 'user', 'user', 'mahasiswa');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `jurusan`
--
ALTER TABLE `jurusan`
  ADD PRIMARY KEY (`id_jurusan`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`id_mahasiswa`),
  ADD UNIQUE KEY `mahsiswa_nim_uindex` (`nim`),
  ADD KEY `mahsiswa_jurusan_id_jurusan_fk` (`id_jurusan`);

--
-- Indexes for table `matakuliah`
--
ALTER TABLE `matakuliah`
  ADD PRIMARY KEY (`id_mk`);

--
-- Indexes for table `nilai`
--
ALTER TABLE `nilai`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_mahasiswa` (`id_mahasiswa`),
  ADD KEY `id_mk` (`id_mk`);

--
-- Indexes for table `user_dosen`
--
ALTER TABLE `user_dosen`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKslvvt06679ra66u8vg6tbkcxk` (`id_dosen`);

--
-- Indexes for table `user_mahasiswa`
--
ALTER TABLE `user_mahasiswa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_mahasiswa` (`id_mahasiswa`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `dosen`
--
ALTER TABLE `dosen`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `jurusan`
--
ALTER TABLE `jurusan`
  MODIFY `id_jurusan` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `id_mahasiswa` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `matakuliah`
--
ALTER TABLE `matakuliah`
  MODIFY `id_mk` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `nilai`
--
ALTER TABLE `nilai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `user_dosen`
--
ALTER TABLE `user_dosen`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_mahasiswa`
--
ALTER TABLE `user_mahasiswa`
  MODIFY `id` bigint(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `mahsiswa_jurusan_id_jurusan_fk` FOREIGN KEY (`id_jurusan`) REFERENCES `jurusan` (`id_jurusan`);

--
-- Constraints for table `nilai`
--
ALTER TABLE `nilai`
  ADD CONSTRAINT `nilai_ibfk_1` FOREIGN KEY (`id_mahasiswa`) REFERENCES `mahasiswa` (`id_mahasiswa`),
  ADD CONSTRAINT `nilai_ibfk_2` FOREIGN KEY (`id_mk`) REFERENCES `matakuliah` (`id_mk`);

--
-- Constraints for table `user_dosen`
--
ALTER TABLE `user_dosen`
  ADD CONSTRAINT `FKslvvt06679ra66u8vg6tbkcxk` FOREIGN KEY (`id_dosen`) REFERENCES `dosen` (`id`);

--
-- Constraints for table `user_mahasiswa`
--
ALTER TABLE `user_mahasiswa`
  ADD CONSTRAINT `user_mahasiswa_ibfk_1` FOREIGN KEY (`id_mahasiswa`) REFERENCES `mahasiswa` (`id_mahasiswa`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
