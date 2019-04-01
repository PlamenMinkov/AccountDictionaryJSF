-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time:  1 апр 2019 в 23:22
-- Версия на сървъра: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `account_dictionary`
--

-- --------------------------------------------------------

--
-- Структура на таблица `account`
--

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Схема на данните от таблица `account`
--

INSERT INTO `account` (`account_id`, `username`, `email`, `password`, `active`) VALUES
(32, 'kika', 'kika@abv,bg', 'qwerty', 0),
(33, 'admin', 'admin@abv.bg', 'qwerty', 1),
(34, 'plamen', 'plamen@abv.bg', 'qwerty', 1),
(35, 'nina', 'nina@abv.bg', 'qwerty', 1),
(36, 'Ani', 'ani@abv.bg', 'qwerty', 1),
(37, 'petq', 'petq@abv.bg', 'qwerty', 1),
(38, 'petq11', 'petq131@abv.bg', 'qwerty', 1),
(39, 'anita', 'ani@abv.bg', 'qwerty', 0),
(40, 'ivan', 'ivan@abv.bg', 'qwerty', 0);

-- --------------------------------------------------------

--
-- Структура на таблица `account_role`
--

CREATE TABLE `account_role` (
  `id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Схема на данните от таблица `account_role`
--

INSERT INTO `account_role` (`id`, `account_id`, `role_id`) VALUES
(3, 34, 1),
(5, 36, 1),
(6, 37, 1),
(7, 38, 1),
(8, 39, 1),
(9, 40, 1),
(10, 33, 2);

-- --------------------------------------------------------

--
-- Структура на таблица `address`
--

CREATE TABLE `address` (
  `account_id` int(11) NOT NULL,
  `country` varchar(30) NOT NULL,
  `town` varchar(30) NOT NULL,
  `neighborhood` varchar(30) NOT NULL,
  `street` varchar(40) NOT NULL,
  `number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Схема на данните от таблица `address`
--

INSERT INTO `address` (`account_id`, `country`, `town`, `neighborhood`, `street`, `number`) VALUES
(32, 'bulgaria', 'Sofia', 'Ivan Vazov', 'Nadejda', 1244),
(33, 'England', 'London', 'Memory', 'Memorial', 13),
(34, 'Bulgaria', 'Gabrovo', 'Ivan Vazov', 'Memorial', 13),
(35, 'Bulgaria', 'Gabrovo', 'Ivan Vazov', 'Nadejda', 13),
(36, 'Bulgaria', 'Plovdiv', 'Kolodrum', 'Kolodrum', 34),
(37, 'Bulgaria', 'Sofia', 'Ivan Vazov', 'Nadejda', 111),
(38, 'Bulgaria', 'Sofia', 'Ivan Vazov', 'Nadejda', 11),
(39, 'Bulgaria', 'Sofia', 'Ivan Vazov', 'Nadejda', 12),
(40, 'Bulgaria', 'Plovdiv', 'Ivan Vazov', 'Memorial', 12);

-- --------------------------------------------------------

--
-- Структура на таблица `role`
--

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `description` varchar(50) NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Схема на данните от таблица `role`
--

INSERT INTO `role` (`role_id`, `name`, `description`, `active`) VALUES
(1, 'USER_ROLE', 'user role', 1),
(2, 'ADMIN_ROLE', 'admin role', 1),
(3, 'GUEST_ROLE', 'guest role', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`);

--
-- Indexes for table `account_role`
--
ALTER TABLE `account_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `role_id` (`role_id`),
  ADD KEY `user_id` (`account_id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD KEY `account_id` (`account_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `account_role`
--
ALTER TABLE `account_role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Ограничения за дъмпнати таблици
--

--
-- Ограничения за таблица `account_role`
--
ALTER TABLE `account_role`
  ADD CONSTRAINT `account_role_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  ADD CONSTRAINT `account_role_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);

--
-- Ограничения за таблица `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `address_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
