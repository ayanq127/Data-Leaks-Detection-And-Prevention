-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2021 at 03:22 AM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `rbac_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `fileinfo`
--

CREATE TABLE IF NOT EXISTS `fileinfo` (
  `user` text,
  `filename` text,
  `data` longtext,
  `Size` text,
  `contenttype` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `jobcreating`
--

CREATE TABLE IF NOT EXISTS `jobcreating` (
  `IpAdd` text,
  `ServerName` text,
  `MemoryLoad` longtext,
  `Incomingload` text,
  `VMUsage` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobcreating`
--

INSERT INTO `jobcreating` (`IpAdd`, `ServerName`, `MemoryLoad`, `Incomingload`, `VMUsage`) VALUES
('192.168.2.1', 'VM_1', '8192', NULL, NULL),
('192.168.2.2', 'VM_2', '52224', NULL, NULL),
('192.168.2.3', 'VM_3', '94208', NULL, NULL),
('192.168.2.4', 'VM_4', '100352', NULL, NULL),
('192.168.2.5', 'VM_5', '37', NULL, NULL),
('192.168.2.6', 'VM_6', '65', NULL, NULL),
('192.168.2.7', 'VM_7', '67', NULL, NULL),
('192.168.2.8', 'VM_8', '72', NULL, NULL),
('192.168.2.9', 'VM_9', '78', NULL, NULL),
('192.168.2.10', 'VM_10', '100', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `masterinfo`
--

CREATE TABLE IF NOT EXISTS `masterinfo` (
  `id_T` int(11) NOT NULL AUTO_INCREMENT,
  `jobid` text,
  `user` text,
  `filename` text,
  `servername` text,
  `vmname` text,
  `Time` text,
  `downloadtime` text,
  `algoid` text,
  PRIMARY KEY (`id_T`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `serverinfo`
--

CREATE TABLE IF NOT EXISTS `serverinfo` (
  `IpAdd` text,
  `ServerName` text,
  `Totalload` longtext,
  `Incomingload` text,
  `VMUsage` text,
  `Remainingload` text,
  `VMavailableuse` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `serverinfo`
--

INSERT INTO `serverinfo` (`IpAdd`, `ServerName`, `Totalload`, `Incomingload`, `VMUsage`, `Remainingload`, `VMavailableuse`) VALUES
('192.168.2.1', 'VM1', '1024', '415.9', '40.62', '608.1', '59.38'),
('192.168.2.2', 'VM2', '1024', '3666.01', '358.01', '-2642.01', '-258.01'),
('192.168.2.3', 'VM3', '1024', '3666.01', '358.01', '-2642.01', '-258.01'),
('192.168.2.4', 'VM4', '1024', '5572.16', '544.16', '-4548.16', '-444.16');

-- --------------------------------------------------------

--
-- Table structure for table `userregistration`
--

CREATE TABLE IF NOT EXISTS `userregistration` (
  `Uname` varchar(100) DEFAULT NULL,
  `Uaddress` varchar(100) DEFAULT NULL,
  `Umobno` varchar(100) DEFAULT NULL,
  `Uemail` varchar(100) DEFAULT NULL,
  `Upassword` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userregistration`
--

INSERT INTO `userregistration` (`Uname`, `Uaddress`, `Umobno`, `Uemail`, `Upassword`) VALUES
('Sayali', 'Rahuri', '9876543234', 'sayali@gmail.com', '1122');

-- --------------------------------------------------------

--
-- Table structure for table `vm1`
--

CREATE TABLE IF NOT EXISTS `vm1` (
  `ChunkID` text,
  `FileName` text,
  `Data` text,
  `Size` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vm2`
--

CREATE TABLE IF NOT EXISTS `vm2` (
  `ChunkID` text,
  `FileName` text,
  `Data` text,
  `Size` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vm3`
--

CREATE TABLE IF NOT EXISTS `vm3` (
  `ChunkID` text,
  `FileName` text,
  `Data` text,
  `Size` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vm4`
--

CREATE TABLE IF NOT EXISTS `vm4` (
  `ChunkID` text,
  `FileName` text,
  `Data` text,
  `Size` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vm5`
--

CREATE TABLE IF NOT EXISTS `vm5` (
  `ChunkID` text,
  `FileName` text,
  `Data` text,
  `Size` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vm6`
--

CREATE TABLE IF NOT EXISTS `vm6` (
  `ChunkID` text,
  `FileName` text,
  `Data` text,
  `Size` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vm7`
--

CREATE TABLE IF NOT EXISTS `vm7` (
  `ChunkID` text,
  `FileName` text,
  `Data` text,
  `Size` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vm8`
--

CREATE TABLE IF NOT EXISTS `vm8` (
  `ChunkID` text,
  `FileName` text,
  `Data` text,
  `Size` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vm9`
--

CREATE TABLE IF NOT EXISTS `vm9` (
  `ChunkID` text,
  `FileName` text,
  `Data` text,
  `Size` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vm10`
--

CREATE TABLE IF NOT EXISTS `vm10` (
  `ChunkID` text,
  `FileName` text,
  `Data` text,
  `Size` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
