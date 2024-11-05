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
-- Database: `server1`
--

-- --------------------------------------------------------

--
-- Table structure for table `csp`
--

CREATE TABLE IF NOT EXISTS `csp` (
  `user` varchar(50) DEFAULT NULL,
  `part` longtext,
  `filename` varchar(50) DEFAULT NULL,
  `filesize` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `csp1`
--

CREATE TABLE IF NOT EXISTS `csp1` (
  `user` varchar(50) DEFAULT NULL,
  `part` longtext,
  `filename` varchar(50) DEFAULT NULL,
  `filesize` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `csp2`
--

CREATE TABLE IF NOT EXISTS `csp2` (
  `user` varchar(50) DEFAULT NULL,
  `part` longtext,
  `filename` varchar(50) DEFAULT NULL,
  `filesize` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `csp3`
--

CREATE TABLE IF NOT EXISTS `csp3` (
  `user` varchar(50) DEFAULT NULL,
  `part` longtext,
  `filename` varchar(50) DEFAULT NULL,
  `filesize` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `csp4`
--

CREATE TABLE IF NOT EXISTS `csp4` (
  `user` varchar(50) DEFAULT NULL,
  `part` longtext,
  `filename` varchar(50) DEFAULT NULL,
  `filesize` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
