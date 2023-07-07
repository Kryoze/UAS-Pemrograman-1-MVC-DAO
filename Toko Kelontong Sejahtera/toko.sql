-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 07, 2023 at 02:20 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `toko`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id` varchar(50) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `stok` int(11) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id`, `nama`, `stok`, `harga`) VALUES
('BRG001', 'Gula', 18, 10000),
('BRG002', 'Kopi', 25, 15000),
('BRG003', 'Mie Instan', 37, 5000),
('BRG004', 'Sabun Mandi', 20, 8000),
('BRG005', 'Minyak Goreng', 40, 12000),
('BRG006', 'Susu', 15, 15000),
('BRG007', 'Roti', 3, 5000),
('BRG008', 'Telur', 50, 3000),
('BRG009', 'Garam', 15, 2000),
('BRG010', 'Teh', 28, 10000),
('BRG011', 'Mentega', 18, 12000),
('BRG012', 'Sampo', 30, 15000),
('BRG013', 'Tisu', 36, 5000),
('BRG014', 'Beras', 70, 20000),
('BRG015', 'Minyak Sayur', 60, 10000);

-- --------------------------------------------------------

--
-- Table structure for table `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id` int(11) NOT NULL,
  `jml_barang` int(11) NOT NULL,
  `id_barang` varchar(50) DEFAULT NULL,
  `id_transaksi` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id`, `jml_barang`, `id_barang`, `id_transaksi`) VALUES
(5, 5, 'BRG002', 'TRX20230707184646487'),
(4, 10, 'BRG014', 'TRX20230707184646487'),
(6, 3, 'BRG003', 'TRX20230707184646487'),
(7, 1, 'BRG013', 'TRX20230707184646487'),
(8, 2, 'BRG001', 'TRX20230707184646487'),
(9, 2, 'BRG011', 'TRX20230707184646487'),
(10, 3, 'BRG013', 'TRX20230707184815185'),
(11, 7, 'BRG010', 'TRX20230707184815185'),
(12, 15, 'BRG007', 'TRX20230707184838983'),
(13, 10, 'BRG006', 'TRX20230707184838983');

--
-- Triggers `detail_transaksi`
--
DELIMITER $$
CREATE TRIGGER `penjualan` AFTER INSERT ON `detail_transaksi` FOR EACH ROW BEGIN
UPDATE barang SET stok=stok-new.jml_barang
WHERE Id=new.id_barang;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id` varchar(50) NOT NULL,
  `tgl_transaksi` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id`, `tgl_transaksi`) VALUES
('TRX20230707184815185', '2023-07-07'),
('TRX20230707184646487', '2023-07-07'),
('TRX20230707184838983', '2023-07-07');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_transaksi` (`id_transaksi`),
  ADD KEY `id_barang` (`id_barang`),
  ADD KEY `id_barang_2` (`id_barang`),
  ADD KEY `id_transaksi_2` (`id_transaksi`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
