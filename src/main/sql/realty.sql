-- phpMyAdmin SQL Dump
-- version 4.0.5
-- http://www.phpmyadmin.net
--
-- Host: 127.7.230.130:3306
-- Erstellungszeit: 10. Mai 2014 um 09:45
-- Server Version: 5.5.36
-- PHP-Version: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `realty`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `apartment`
--

CREATE TABLE IF NOT EXISTS `apartment` (
  `id` binary(16) NOT NULL,
  `address` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `emeter`
--

CREATE TABLE IF NOT EXISTS `emeter` (
  `id` binary(16) NOT NULL,
  `apartment_id` binary(16) NOT NULL,
  `type` enum('DAY','NIGHT','','') COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `apartment_id` (`apartment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `emeter_value`
--

CREATE TABLE IF NOT EXISTS `emeter_value` (
  `id` binary(16) NOT NULL,
  `emeter_id` binary(16) NOT NULL,
  `value` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `meter_id` (`emeter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `payment`
--

CREATE TABLE IF NOT EXISTS `payment` (
  `id` binary(16) NOT NULL,
  `amount` int(11) NOT NULL,
  `date` date NOT NULL,
  `cash` tinyint(1) DEFAULT NULL,
  `rent_id` binary(16) NOT NULL,
  `info` varchar(500) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rent_id` (`rent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rcost`
--

CREATE TABLE IF NOT EXISTS `rcost` (
  `id` binary(16) NOT NULL,
  `begin` date NOT NULL,
  `end` date NOT NULL,
  `apartment_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `apartment_id` (`apartment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rent`
--

CREATE TABLE IF NOT EXISTS `rent` (
  `id` binary(16) NOT NULL,
  `tenant_id` binary(16) NOT NULL,
  `apartment_id` binary(16) NOT NULL,
  `begin` date DEFAULT NULL,
  `rate` int(11) NOT NULL,
  `end` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tenant_id` (`tenant_id`,`apartment_id`),
  KEY `apartment_id` (`apartment_id`),
  KEY `tenant_id_2` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tenant`
--

CREATE TABLE IF NOT EXISTS `tenant` (
  `id` binary(16) NOT NULL,
  `last_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `middle_name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `wmeter`
--

CREATE TABLE IF NOT EXISTS `wmeter` (
  `id` binary(16) NOT NULL,
  `apartment_id` binary(16) NOT NULL,
  `type` enum('COLD','HOT','','') COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `apartment_id` (`apartment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `wmeter_value`
--

CREATE TABLE IF NOT EXISTS `wmeter_value` (
  `id` binary(16) NOT NULL,
  `wmeter_id` binary(16) NOT NULL,
  `value` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `date` (`date`),
  KEY `wmeter_id` (`wmeter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `emeter`
--
ALTER TABLE `emeter`
  ADD CONSTRAINT `emeter_ibfk_1` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `emeter_value`
--
ALTER TABLE `emeter_value`
  ADD CONSTRAINT `emeter_value_ibfk_1` FOREIGN KEY (`emeter_id`) REFERENCES `emeter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`rent_id`) REFERENCES `rent` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `rcost`
--
ALTER TABLE `rcost`
  ADD CONSTRAINT `rcost_ibfk_1` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `rent`
--
ALTER TABLE `rent`
  ADD CONSTRAINT `rent_ibfk_1` FOREIGN KEY (`tenant_id`) REFERENCES `rent` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `rent_ibfk_2` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `wmeter`
--
ALTER TABLE `wmeter`
  ADD CONSTRAINT `wmeter_ibfk_1` FOREIGN KEY (`apartment_id`) REFERENCES `apartment` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints der Tabelle `wmeter_value`
--
ALTER TABLE `wmeter_value`
  ADD CONSTRAINT `wmeter_value_ibfk_1` FOREIGN KEY (`wmeter_id`) REFERENCES `wmeter` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
