-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 13 Février 2015 à 17:52
-- Version du serveur: 5.5.35
-- Version de PHP: 5.4.36-0+deb7u3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `esecure`
--

-- --------------------------------------------------------

--
-- Structure de la table `accuse_reception`
--

CREATE TABLE IF NOT EXISTS `accuse_reception` (
  `ID_ACCUSE_RECEPTION` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_ACCUSE_RECEPTION` varchar(255) DEFAULT NULL,
  `MESSAGE` varchar(255) DEFAULT NULL,
  `TYPE_RETOUR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ACCUSE_RECEPTION`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Contenu de la table `accuse_reception`
--

INSERT INTO `accuse_reception` (`ID_ACCUSE_RECEPTION`, `DATE_ACCUSE_RECEPTION`, `MESSAGE`, `TYPE_RETOUR`) VALUES
(1, '02/02/2015', 'Message depuis le controllerfr.banking.esecure.controller.BasicController', 'OK'),
(2, '02/02/2015', 'Message depuis le controllerfr.banking.esecure.controller.BasicController', 'OK'),
(3, '02/02/2015', 'Message depuis le controllerfr.banking.esecure.controller.BasicController', 'OK'),
(4, '02/02/2015', 'Message depuis le controllerfr.banking.esecure.controller.BasicController', 'OK'),
(5, '2015-02-05', 'transaction passée avec succés', 'ok'),
(6, '2015-02-05', 'transaction passée avec succés', 'ok');

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE IF NOT EXISTS `adresse` (
  `ID_ADRESSE` bigint(20) NOT NULL AUTO_INCREMENT,
  `NUMERO_RUE` int(11) DEFAULT NULL,
  `NOM_RUE` varchar(255) DEFAULT NULL,
  `PORTE` int(11) DEFAULT NULL,
  `TYPE_RESIDENCE` varchar(255) DEFAULT NULL,
  `NOM_RESIDENCE` varchar(255) DEFAULT NULL,
  `NUMERO_ETAGE` int(11) DEFAULT NULL,
  `CODE_POSTAL` varchar(255) DEFAULT NULL,
  `VILLE` varchar(255) DEFAULT NULL,
  `ID_CLIENT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_ADRESSE`),
  KEY `FK_ID_CLIENT` (`ID_CLIENT`),
  KEY `FKBC5730AF4B84D41A` (`ID_CLIENT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `adresse`
--

INSERT INTO `adresse` (`ID_ADRESSE`, `NUMERO_RUE`, `NOM_RUE`, `PORTE`, `TYPE_RESIDENCE`, `NOM_RESIDENCE`, `NUMERO_ETAGE`, `CODE_POSTAL`, `VILLE`, `ID_CLIENT`) VALUES
(1, 24, 'chemin d''aranchette', 301, 'Residence', 'campus des  sciences', 3, '31400', 'Toulouse', NULL),
(2, 24, 'chemin d''aranchette', 301, 'campus universitaire', 'aranchettte', 3, '64100', 'bayonne', NULL),
(3, 24, 'chemin du bassin', 301, 'Cité universiaire', 'claude delorme', 3, '13000', 'Marseille', NULL),
(4, 24, 'chemin d''aranchette', 301, NULL, NULL, 3, NULL, 'bayonne', NULL),
(5, 24, 'chemin d''aranchette', 301, NULL, NULL, 3, NULL, 'bayonne', NULL),
(6, 24, 'chemin d''aranchette', 301, NULL, NULL, 3, NULL, 'bayonne', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `avenant`
--

CREATE TABLE IF NOT EXISTS `avenant` (
  `ID_AVENANT` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_CONTRAT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_AVENANT`),
  KEY `FK_ID_CONTRAT` (`ID_CONTRAT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `avenant_clause`
--

CREATE TABLE IF NOT EXISTS `avenant_clause` (
  `ID_AVENANT` bigint(20) NOT NULL,
  `ID_CLAUSE` bigint(20) NOT NULL,
  KEY `FK_ID_AVENANT` (`ID_AVENANT`),
  KEY `FK_ID_CLAUSE` (`ID_CLAUSE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `banque`
--

CREATE TABLE IF NOT EXISTS `banque` (
  `ID_BANQUE` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADRESSE` varchar(255) DEFAULT NULL,
  `NUMERO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_BANQUE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `banque`
--

INSERT INTO `banque` (`ID_BANQUE`, `ADRESSE`, `NUMERO`) VALUES
(1, 'Boulevard de la republique', '12'),
(2, 'mermoz extension', '12'),
(3, 'societe generale', '42'),
(4, 'crédit mutuel', '32'),
(5, 'lcl', '42'),
(6, 'BHS', '32');

-- --------------------------------------------------------

--
-- Structure de la table `banque_conseiller`
--

CREATE TABLE IF NOT EXISTS `banque_conseiller` (
  `ID_BANQUE` bigint(20) NOT NULL,
  `ID_CONSEILLER` bigint(20) NOT NULL,
  KEY `FK_ID_BANQUE` (`ID_BANQUE`),
  KEY `FK_ID_CONSEILLER` (`ID_CONSEILLER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `carte_bancaire`
--

CREATE TABLE IF NOT EXISTS `carte_bancaire` (
  `ID_CARTE_BANCAIRE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRYPTO` int(11) DEFAULT NULL,
  `DATE_EXPIR` varchar(255) DEFAULT NULL,
  `NUM_CARTE` varchar(255) DEFAULT NULL,
  `TYPE_CARTE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_CARTE_BANCAIRE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `carte_bancaire`
--

INSERT INTO `carte_bancaire` (`ID_CARTE_BANCAIRE`, `CRYPTO`, `DATE_EXPIR`, `NUM_CARTE`, `TYPE_CARTE`) VALUES
(1, 123, '2015-02-05', '6452 3214 2314 5623', 'visa'),
(2, 123, '2015-02-05', '6452 3214 2314 5623', 'visa'),
(3, 123, '2015-02-05', '1002 0014 2314 5623', 'visa'),
(4, 123, '2015-02-06', '6452 3214 2314 5623', 'visa'),
(5, 123, '2015-02-06', '6452 3214 2314 5623', 'visa'),
(6, 123, '2015-02-06', '1052 3214 2314 4123', 'visa'),
(7, 123, '2015-02-13', '6452 3214 2314 5623', 'visa');

-- --------------------------------------------------------

--
-- Structure de la table `carte_bancaire_carte_transaction`
--

CREATE TABLE IF NOT EXISTS `carte_bancaire_carte_transaction` (
  `carte_bancaire_ID_CARTE_BANCAIRE` bigint(20) NOT NULL,
  `transactions_ID_TRANSACTION` bigint(20) NOT NULL,
  UNIQUE KEY `transactions_ID_TRANSACTION` (`transactions_ID_TRANSACTION`),
  KEY `FK8319066E44B4D8B` (`carte_bancaire_ID_CARTE_BANCAIRE`),
  KEY `FK8319066E8D945BE2` (`transactions_ID_TRANSACTION`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `carte_transaction`
--

CREATE TABLE IF NOT EXISTS `carte_transaction` (
  `ID_TRANSACTION` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE_TRANSACTION` int(11) DEFAULT NULL,
  `DATE_TRANSACTION` datetime DEFAULT NULL,
  `MONTANT` double DEFAULT NULL,
  `ID_ADRESSE` bigint(20) DEFAULT NULL,
  `ID_CARTE_BANCAIRE` bigint(20) DEFAULT NULL,
  `NOM_COMMERCANT` varchar(255) DEFAULT NULL,
  `SITE_WEB_COMMERCANT` varchar(255) DEFAULT NULL,
  `etat` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID_TRANSACTION`),
  KEY `FK_ID_CARTE_BANCAIRE` (`ID_CARTE_BANCAIRE`),
  KEY `FK_ID_ADRESSE` (`ID_ADRESSE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `carte_transaction`
--

INSERT INTO `carte_transaction` (`ID_TRANSACTION`, `CODE_TRANSACTION`, `DATE_TRANSACTION`, `MONTANT`, `ID_ADRESSE`, `ID_CARTE_BANCAIRE`, `NOM_COMMERCANT`, `SITE_WEB_COMMERCANT`, `etat`) VALUES
(1, 1, '2015-02-05 00:00:00', 1250, 1, 1, 'cdiscount', 'www.cdiscount.com', 'VALIDATED'),
(2, 2, '2015-02-05 00:00:00', 10000, 2, 2, 'amazone', 'www.amazone.com', 'REJECTED'),
(3, 1, '2015-02-05 00:00:00', 7800, 3, 3, 'asos', 'www.asos.com', 'VALIDATED'),
(4, 1, '2015-02-06 00:00:00', 20000, 4, 4, 'amazone', 'www.amazone.com', 'VALIDATED'),
(5, 1, '2015-02-06 00:00:00', 500, 5, 5, 'kiabi', 'www.kiabi.com', 'VALIDATED'),
(6, 1, '2015-02-06 00:00:00', 20000, 6, 6, 'amazone', 'www.amazone.com', 'REJECTED');

-- --------------------------------------------------------

--
-- Structure de la table `clause`
--

CREATE TABLE IF NOT EXISTS `clause` (
  `ID_CLAUSE` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID_CLAUSE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `ID_CLIENT` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM_CLIENT` varchar(255) DEFAULT NULL,
  `PRENOM_CLIENT` varchar(255) DEFAULT NULL,
  `NUMERO_COMPTE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ID_CONSEILLER` bigint(20) DEFAULT NULL,
  `ID_CARTE_BANCAIRE` bigint(20) DEFAULT NULL,
  `ID_CONTRAT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENT`),
  KEY `FK_ID_CLIENT_CARTE_BANCAIRE` (`ID_CARTE_BANCAIRE`),
  KEY `FK_ID_CLIENT_CONTRAT` (`ID_CONTRAT`),
  KEY `FK_ID_CLIENT_CONSEILLER` (`ID_CONSEILLER`),
  KEY `FKAF12F3CB2FED6B62` (`ID_CONTRAT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`ID_CLIENT`, `NOM_CLIENT`, `PRENOM_CLIENT`, `NUMERO_COMPTE`, `EMAIL`, `ID_CONSEILLER`, `ID_CARTE_BANCAIRE`, `ID_CONTRAT`) VALUES
(1, 'niang', 'seydou', '1234 4523 5421 4268', 'douseyi@gmail.fr', 1, 1, 1),
(2, 'ndiaye', 'demba', '1230 7453 5124 3214', 'gggg@live.fr', 1, 2, 2),
(3, 'sow', 'yero', '1235 4523 7412 10254', 'yero@gmail.com', 1, 3, 3),
(4, 'sow', 'diom', '4561 7895 1230 4521', 'diom@yahoo.fr', 2, 4, 4),
(5, 'diop', 'yero', '1235 4523 7412 10254', 'yero@gmail.com', 1, 5, 5),
(6, 'kane', 'kamal', '4561 7895 1230 4521', 'diom@yahoo.fr', 2, 6, 6);

-- --------------------------------------------------------

--
-- Structure de la table `client_adresse`
--

CREATE TABLE IF NOT EXISTS `client_adresse` (
  `ID_CLIENT` bigint(20) NOT NULL,
  `ID_ADRESSE` bigint(20) NOT NULL,
  KEY `FK_ID_CLIENT_CLIENT` (`ID_CLIENT`),
  KEY `FK_ID_CLIENT_ADRESSE` (`ID_ADRESSE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `ID_COMPTE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` date DEFAULT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_COMPTE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=205 ;

--
-- Contenu de la table `compte`
--

INSERT INTO `compte` (`ID_COMPTE`, `DATE_CREATION`, `ETAT`, `LOGIN`, `PASSWORD`) VALUES
(124, '2015-02-12', 1, 'ibra', 'd1d0abe7dc8fcff02f17c3a26d0e3281f41d149e2d7fb85acd1d61db7bf5a287'),
(201, '2015-02-13', 1, 'm.nassur', 'e01e45d89036f2c7ee4719b54e0f44d66d5fcb498f390c1246dea0e9b30475c4'),
(202, '2015-02-13', 1, 'esecure', 'ce68c5e2fb3a5f00489fc6f2c5d27640f4989a5158887eed038fcd4a565a08f1');

-- --------------------------------------------------------

--
-- Structure de la table `compte_rendu`
--

CREATE TABLE IF NOT EXISTS `compte_rendu` (
  `ID_COMPTE_RENDU` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_COMPTE_RENDU` varchar(255) DEFAULT NULL,
  `MESSAGE` varchar(255) DEFAULT NULL,
  `TYPE_RETOUR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_COMPTE_RENDU`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=165 ;

--
-- Contenu de la table `compte_rendu`
--

INSERT INTO `compte_rendu` (`ID_COMPTE_RENDU`, `DATE_COMPTE_RENDU`, `MESSAGE`, `TYPE_RETOUR`) VALUES
(35, '2015-02-05', 'c''est bon', 'ok'),
(36, NULL, NULL, NULL),
(37, NULL, NULL, NULL),
(38, '2015-02-05', 'test insert cr', 'OK'),
(39, '2015-02-05', 'test insert cr', 'OK'),
(40, '2015-02-05', 'le compte rendu avec bean id commented', 'ok'),
(41, '2015-02-05', 'c''est bon', 'ok'),
(42, '2015-02-06', 'test insert cr', 'OK'),
(43, '2015-02-06', 'test insert cr', 'OK'),
(44, '2015-02-06', 'test insert cr', 'OK'),
(45, '2015-02-06', 'test insert cr', 'OK'),
(46, '2015-02-06', 'test insert cr', 'OK'),
(47, '2015-02-06', 'test insert cr', 'OK'),
(48, '2015-02-06', 'c''est bon', 'ok'),
(49, '2015-02-06', 'test insert cr', 'OK'),
(50, '2015-02-06', 'test insert cr', 'OK'),
(51, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(52, '2015-02-06', 'test insert cr', 'OK'),
(53, '2015-02-06', 'test insert cr', 'OK'),
(54, '2015-02-06', 'test insert cr', 'OK'),
(55, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(56, '2015-02-06', 'test insert cr', 'OK'),
(57, '2015-02-06', 'test insert cr', 'OK'),
(58, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(59, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(60, '2015-02-06', 'test insert cr', 'OK'),
(61, '2015-02-06', 'test insert cr', 'OK'),
(62, '2015-02-06', 'test insert cr', 'OK'),
(63, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(64, '2015-02-06', 'test insert cr', 'OK'),
(65, '2015-02-06', 'test insert cr', 'OK'),
(66, '2015-02-06', 'test insert cr', 'OK'),
(67, '2015-02-06', 'test insert cr', 'OK'),
(68, '2015-02-06', 'test insert cr', 'OK'),
(69, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(70, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(71, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(72, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(73, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(74, '2015-02-06', 'test insert cr', 'OK'),
(75, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(76, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(77, '2015-02-06', 'test insert cr', 'OK'),
(78, '2015-02-06', 'test insert cr', 'OK'),
(79, '2015-02-06', 'test insert cr', 'OK'),
(80, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(81, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(82, '2015-02-06', 'c''est bon', 'ok'),
(83, '2015-02-06', 'le compte rendu avec bean id commented', 'ok'),
(84, '2015-02-06', 'c''est bon', 'ok'),
(85, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(86, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(87, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(88, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(89, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(90, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(91, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(92, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(93, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(94, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(95, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(96, '2015-02-09 14:31:56', 'le compte rendu avec bean id commented', 'ok'),
(97, '2015-02-09 14:33:28', 'le compte rendu avec bean id commented', 'ok'),
(98, '2015-02-09', 'c''est bon', 'ok'),
(99, '2015-02-09', 'c''est bon', 'ok'),
(100, '2015-02-09', 'c''est bon', 'ok'),
(101, '2015-02-09', 'c''est bon', 'ok'),
(102, '2015-02-09', 'c''est bon', 'ok'),
(103, '2015-02-09', 'c''est bon', 'ok'),
(104, '2015-02-09', 'c''est bon', 'ok'),
(105, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(106, '2015-02-09', 'le compte rendu avec bean id commented', 'ok'),
(107, '2015-02-11 07:40:12', 'le compte rendu avec bean id commented', 'ok'),
(108, '2015-02-11 07:46:59', 'le compte rendu avec bean id commented', 'ok'),
(109, '2015-02-11 08:29:16', 'le compte rendu avec bean id commented', 'ok'),
(110, '2015-02-11 08:49:54', 'le compte rendu avec bean id commented', 'ok'),
(111, '2015-02-11 12:16:22', 'le compte rendu avec bean id commented', 'ok'),
(112, '2015-02-11 09:42:33', 'le compte rendu avec bean id commented', 'ok'),
(113, '2015-02-11 12:14:22', 'le compte rendu avec bean id commented', 'ok'),
(114, '2015-02-11 12:22:37', 'le compte rendu avec bean id commented', 'ok'),
(115, '2015-02-11 12:27:06', 'le compte rendu avec bean id commented', 'ok'),
(116, '2015-02-11 12:30:24', 'le compte rendu avec bean id commented', 'ok'),
(117, '2015-02-11 15:32:25', 'le compte rendu avec bean id commented', 'ok'),
(118, '2015-02-11 16:16:31', 'le compte rendu avec bean id commented', 'ok'),
(119, '2015-02-11 18:16:18', 'le compte rendu avec bean id commented', 'ok'),
(120, '2015-02-12 09:15:23', 'le compte rendu avec bean id commented', 'ok'),
(121, '2015-02-12 07:24:57', 'le compte rendu avec bean id commented', 'ok'),
(122, '2015-02-12 12:15:23', 'le compte rendu avec bean id commented', 'ok'),
(123, '2015-02-12 14:01:39', 'le compte rendu avec bean id commented', 'ok'),
(124, '2015-02-12 11:56:10', 'le compte rendu avec bean id commented', 'ok'),
(125, '2015-02-12 12:04:26', 'le compte rendu avec bean id commented', 'ok'),
(126, '2015-02-12 12:04:49', 'le compte rendu avec bean id commented', 'ok'),
(127, '2015-02-12 12:05:21', 'le compte rendu avec bean id commented', 'ok'),
(128, '2015-02-12 15:15:22', 'le compte rendu avec bean id commented', 'ok'),
(129, '2015-02-12 12:18:36', 'le compte rendu avec bean id commented', 'ok'),
(130, '2015-02-12 16:15:22', 'le compte rendu avec bean id commented', 'ok'),
(131, '2015-02-13 07:57:16', 'le compte rendu avec bean id commented', 'ok'),
(132, '2015-02-13 09:19:04', 'le compte rendu avec bean id commented', 'ok'),
(133, '2015-02-13 09:22:03', 'le compte rendu avec bean id commented', 'ok'),
(134, '2015-02-13 09:30:04', 'le compte rendu avec bean id commented', 'ok'),
(135, '2015-02-13 10:41:16', 'le compte rendu avec bean id commented', 'ok'),
(136, '2015-02-13 11:15:44', 'le compte rendu avec bean id commented', 'ok'),
(137, '2015-02-13 08:19:42', 'le compte rendu avec bean id commented', 'ok'),
(138, '2015-02-13 08:21:08', 'le compte rendu avec bean id commented', 'ok'),
(139, '2015-02-13 09:08:11', 'le compte rendu avec bean id commented', 'ok'),
(140, '2015-02-13 09:46:55', 'le compte rendu avec bean id commented', 'ok'),
(141, '2015-02-13 10:39:54', 'le compte rendu avec bean id commented', 'ok'),
(142, '2015-02-13 14:15:31', 'le compte rendu avec bean id commented', 'ok'),
(143, '2015-02-13 11:49:54', 'le compte rendu avec bean id commented', 'ok'),
(144, '2015-02-13 11:53:09', 'le compte rendu avec bean id commented', 'ok'),
(145, '2015-02-13 11:53:58', 'le compte rendu avec bean id commented', 'ok'),
(146, '2015-02-13 11:59:34', 'le compte rendu avec bean id commented', 'ok'),
(147, '2015-02-13 12:43:50', 'le compte rendu avec bean id commented', 'ok'),
(148, '2015-02-13 12:44:14', 'le compte rendu avec bean id commented', 'ok'),
(149, '2015-02-13 12:51:51', 'le compte rendu avec bean id commented', 'ok'),
(150, '2015-02-13 13:00:54', 'le compte rendu avec bean id commented', 'ok'),
(151, '2015-02-13 16:15:47', 'le compte rendu avec bean id commented', 'ok'),
(152, '2015-02-13 13:27:37', 'le compte rendu avec bean id commented', 'ok'),
(153, '2015-02-13 13:30:28', 'le compte rendu avec bean id commented', 'ok'),
(154, '2015-02-13 14:01:49', 'le compte rendu avec bean id commented', 'ok'),
(155, '2015-02-13 14:03:28', 'le compte rendu avec bean id commented', 'ok'),
(156, '2015-02-13 17:05:46', 'le compte rendu avec bean id commented', 'ok'),
(157, '2015-02-13 17:15:27', 'le compte rendu avec bean id commented', 'ok'),
(158, '2015-02-13 14:18:40', 'le compte rendu avec bean id commented', 'ok'),
(159, '2015-02-13 14:21:32', 'le compte rendu avec bean id commented', 'ok'),
(160, '2015-02-13 14:22:05', 'le compte rendu avec bean id commented', 'ok'),
(161, '2015-02-13 14:23:55', 'le compte rendu avec bean id commented', 'ok'),
(162, '2015-02-13 14:27:23', 'le compte rendu avec bean id commented', 'ok'),
(163, '2015-02-13 14:29:37', 'le compte rendu avec bean id commented', 'ok'),
(164, '2015-02-13 17:49:42', 'le compte rendu avec bean id commented', 'ok');

-- --------------------------------------------------------

--
-- Structure de la table `conseiller`
--

CREATE TABLE IF NOT EXISTS `conseiller` (
  `ID_CONSEILLER` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM_CONSEILLER` varchar(255) DEFAULT NULL,
  `PRENOM_CONSEILLER` varchar(255) DEFAULT NULL,
  `ID_BANQUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_CONSEILLER`),
  KEY `FK_ID_BANQUE` (`ID_BANQUE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `conseiller`
--

INSERT INTO `conseiller` (`ID_CONSEILLER`, `NOM_CONSEILLER`, `PRENOM_CONSEILLER`, `ID_BANQUE`) VALUES
(1, 'bonnifet', 'cecile', 1),
(2, 'ngom', 'fatou', 2),
(3, 'diop', 'tamsir', 1),
(4, 'ndiaye', 'abdou', 2);

-- --------------------------------------------------------

--
-- Structure de la table `contrat`
--

CREATE TABLE IF NOT EXISTS `contrat` (
  `ID_CONTRAT` bigint(20) NOT NULL AUTO_INCREMENT,
  `TYPE_CONTRAT` varchar(255) DEFAULT NULL,
  `ID_CLIENT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_CONTRAT`),
  KEY `FK_ID_CLIENT` (`ID_CLIENT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `contrat`
--

INSERT INTO `contrat` (`ID_CONTRAT`, `TYPE_CONTRAT`, `ID_CLIENT`) VALUES
(1, 'cdd', 1),
(2, 'cdi', 2),
(3, 'CDI', 3),
(4, 'CDD', 4),
(5, 'CDI', 3),
(6, 'CDD', 4),
(7, 'CDI', 5),
(8, 'CDD', 6);

-- --------------------------------------------------------

--
-- Structure de la table `contrat_clause`
--

CREATE TABLE IF NOT EXISTS `contrat_clause` (
  `ID_CONTRAT` bigint(20) NOT NULL,
  `ID_CLAUSE` bigint(20) NOT NULL,
  KEY `FK_ID_CLAUSE_CONTRAT_CLAUSE` (`ID_CLAUSE`),
  KEY `FK_ID_CONTRAT_CONTRAT_CLAUSE` (`ID_CONTRAT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `demande`
--

CREATE TABLE IF NOT EXISTS `demande` (
  `ID_DEMANDE` bigint(20) NOT NULL AUTO_INCREMENT,
  `NUMERO_JETON` int(11) DEFAULT NULL,
  `ID_ACCUSE_RECEPTION` bigint(20) DEFAULT NULL,
  `ID_COMPTE_RENDU` bigint(20) DEFAULT NULL,
  `ID_INFO_FORMULAIRE` bigint(20) DEFAULT NULL,
  `ID_TRANSACTION` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_DEMANDE`),
  KEY `FK_ID_AR_DEMANDE` (`ID_ACCUSE_RECEPTION`),
  KEY `FK_ID_TRANSACTION_DEMANDE` (`ID_TRANSACTION`),
  KEY `FK_ID_INFO_FORM_DEMANDE` (`ID_INFO_FORMULAIRE`),
  KEY `FK_ID_COMPTE_RENDU_DEMANDE` (`ID_COMPTE_RENDU`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `demande`
--

INSERT INTO `demande` (`ID_DEMANDE`, `NUMERO_JETON`, `ID_ACCUSE_RECEPTION`, `ID_COMPTE_RENDU`, `ID_INFO_FORMULAIRE`, `ID_TRANSACTION`) VALUES
(1, 1, 1, 35, 1, 2),
(2, 1, 2, 41, 2, 3),
(4, 1, 3, 82, 4, 5),
(5, 1, 4, 84, 5, 6);

-- --------------------------------------------------------

--
-- Structure de la table `demoUsers`
--

CREATE TABLE IF NOT EXISTS `demoUsers` (
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(200) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `demoUsers`
--

INSERT INTO `demoUsers` (`username`, `password`, `enabled`, `role`) VALUES
('kamal', 'f57dbb83bc838bda3c0cf7d6091e9a3c16f824cef7ebef8faad554bf5ebc7ad4', 1, 'role_admin'),
('test', 'test', 1, 'ROLE_ADMIN'),
('dysow', 'dysow', 1, 'ROLE_ADMIN'),
('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1, 'role_admin'),
('esecure2', 'ce68c5e2fb3a5f00489fc6f2c5d27640f4989a5158887eed038fcd4a565a08f1', 1, 'role_admin'),
('esecuremd5', 'd5d6e42f6bad5c9870720cda0b0b3657', 1, 'role_admin'),
('esecure', 'esecure', 1, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Structure de la table `info_formulaire`
--

CREATE TABLE IF NOT EXISTS `info_formulaire` (
  `ID_INFO_FORMULAIRE` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM_CLIENT` varchar(255) DEFAULT NULL,
  `PRENOM_CLIENT` varchar(255) DEFAULT NULL,
  `NUMERO_COMPTE` varchar(255) DEFAULT NULL,
  `NUMERO_CARTE_BANCAIRE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NUMERO_RUE` varchar(255) DEFAULT NULL,
  `NOM_RUE` varchar(255) DEFAULT NULL,
  `NOM_RESIDENCE` varchar(255) DEFAULT NULL,
  `TYPE_RESIDENCE` varchar(255) DEFAULT NULL,
  `NUMERO_ETAGE` int(11) DEFAULT NULL,
  `CODE_POSTAL` varchar(255) DEFAULT NULL,
  `VILLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_INFO_FORMULAIRE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Contenu de la table `info_formulaire`
--

INSERT INTO `info_formulaire` (`ID_INFO_FORMULAIRE`, `NOM_CLIENT`, `PRENOM_CLIENT`, `NUMERO_COMPTE`, `NUMERO_CARTE_BANCAIRE`, `EMAIL`, `NUMERO_RUE`, `NOM_RUE`, `NOM_RESIDENCE`, `TYPE_RESIDENCE`, `NUMERO_ETAGE`, `CODE_POSTAL`, `VILLE`) VALUES
(1, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(2, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(3, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(4, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(5, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(6, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(7, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(8, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(9, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(10, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(11, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(12, 'seydou', 'niang', '1245 1234 2569', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro');

-- --------------------------------------------------------

--
-- Structure de la table `parametre`
--

CREATE TABLE IF NOT EXISTS `parametre` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `HOSTNAME` varchar(255) DEFAULT NULL,
  `IP_ADRESSE` varchar(255) DEFAULT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PORT` int(11) DEFAULT NULL,
  `ID_BANQUE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`),
  KEY `FK_ID_BANQUE_PARAMETRE` (`ID_BANQUE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `parametre`
--

INSERT INTO `parametre` (`ID_PARAM`, `HOSTNAME`, `IP_ADRESSE`, `LOGIN`, `PASSWORD`, `PORT`, `ID_BANQUE`) VALUES
(1, 'localhost', '127.0.0.1', 'admin', 'admin', 80, 1),
(2, 'localhost', '127.0.0.1', 'admin', 'admin', 80, 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `ID_ROLE` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  `ROLE_DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ROLE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`ID_ROLE`, `ROLE_NAME`, `ROLE_DESC`) VALUES
(1, 'ROLE_ADMIN', 'administrateur'),
(2, 'ROLE_USER', 'Utilisateur Simple');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `ID_USER` bigint(20) NOT NULL AUTO_INCREMENT,
  `USER_LOGGED` int(11) DEFAULT NULL,
  `USER_MAIL` varchar(255) DEFAULT NULL,
  `USER_NOM` varchar(255) DEFAULT NULL,
  `USER_PHONE` varchar(255) DEFAULT NULL,
  `USER_PRENOM` varchar(255) DEFAULT NULL,
  `ID_COMPTE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_USER`),
  KEY `FK_ID_COMPTE` (`ID_COMPTE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=205 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`ID_USER`, `USER_LOGGED`, `USER_MAIL`, `USER_NOM`, `USER_PHONE`, `USER_PRENOM`, `ID_COMPTE`) VALUES
(123, 1, 'iyade@groupeidyal.com', 'Ibrahima', '33669934494', 'Yade', 124),
(201, 0, 'm.nassur@almteam-consulting.com', 'MHOUMADI', NULL, 'Nassur', 201),
(202, 0, 'esecure@esecure.com', 'ALM TEAM', NULL, 'Nassur', 202);

-- --------------------------------------------------------

--
-- Structure de la table `user_role`
--

CREATE TABLE IF NOT EXISTS `user_role` (
  `ID_USER` bigint(20) NOT NULL,
  `ID_ROLE` bigint(20) NOT NULL,
  KEY `FK_ID_ROLE` (`ID_ROLE`),
  KEY `FK_ID_USER` (`ID_USER`),
  KEY `FK143BF46AAAE33E70` (`ID_ROLE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `user_role`
--

INSERT INTO `user_role` (`ID_USER`, `ID_ROLE`) VALUES
(123, 1),
(201, 2),
(202, 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `FKBC5730AF4B84D41A` FOREIGN KEY (`ID_CLIENT`) REFERENCES `client` (`ID_CLIENT`);

--
-- Contraintes pour la table `avenant`
--
ALTER TABLE `avenant`
  ADD CONSTRAINT `FKDA5B0D892FED6B62` FOREIGN KEY (`ID_CONTRAT`) REFERENCES `contrat` (`ID_CONTRAT`);

--
-- Contraintes pour la table `avenant_clause`
--
ALTER TABLE `avenant_clause`
  ADD CONSTRAINT `FK308CE0E54B7E0762` FOREIGN KEY (`ID_CLAUSE`) REFERENCES `clause` (`ID_CLAUSE`),
  ADD CONSTRAINT `FK308CE0E57334BF0E` FOREIGN KEY (`ID_AVENANT`) REFERENCES `avenant` (`ID_AVENANT`);

--
-- Contraintes pour la table `banque_conseiller`
--
ALTER TABLE `banque_conseiller`
  ADD CONSTRAINT `FKCEE4DD4F46EA0528` FOREIGN KEY (`ID_BANQUE`) REFERENCES `banque` (`ID_BANQUE`),
  ADD CONSTRAINT `FKCEE4DD4FC8764108` FOREIGN KEY (`ID_CONSEILLER`) REFERENCES `conseiller` (`ID_CONSEILLER`);

--
-- Contraintes pour la table `carte_bancaire_carte_transaction`
--
ALTER TABLE `carte_bancaire_carte_transaction`
  ADD CONSTRAINT `FK8319066E8D945BE2` FOREIGN KEY (`transactions_ID_TRANSACTION`) REFERENCES `carte_transaction` (`ID_TRANSACTION`),
  ADD CONSTRAINT `FK8319066E44B4D8B` FOREIGN KEY (`carte_bancaire_ID_CARTE_BANCAIRE`) REFERENCES `carte_bancaire` (`ID_CARTE_BANCAIRE`);

--
-- Contraintes pour la table `carte_transaction`
--
ALTER TABLE `carte_transaction`
  ADD CONSTRAINT `FK7A695AE4372D055A` FOREIGN KEY (`ID_ADRESSE`) REFERENCES `adresse` (`ID_ADRESSE`),
  ADD CONSTRAINT `FK7A695AE4FB9BA201` FOREIGN KEY (`ID_CARTE_BANCAIRE`) REFERENCES `carte_bancaire` (`ID_CARTE_BANCAIRE`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FKAF12F3CB2FED6B62` FOREIGN KEY (`ID_CONTRAT`) REFERENCES `contrat` (`ID_CONTRAT`),
  ADD CONSTRAINT `FKAF12F3CBC8764108` FOREIGN KEY (`ID_CONSEILLER`) REFERENCES `conseiller` (`ID_CONSEILLER`),
  ADD CONSTRAINT `FKAF12F3CBFB9BA201` FOREIGN KEY (`ID_CARTE_BANCAIRE`) REFERENCES `carte_bancaire` (`ID_CARTE_BANCAIRE`);

--
-- Contraintes pour la table `client_adresse`
--
ALTER TABLE `client_adresse`
  ADD CONSTRAINT `FK4D9AD03B372D055A` FOREIGN KEY (`ID_ADRESSE`) REFERENCES `adresse` (`ID_ADRESSE`),
  ADD CONSTRAINT `FK4D9AD03B4B84D41A` FOREIGN KEY (`ID_CLIENT`) REFERENCES `client` (`ID_CLIENT`);

--
-- Contraintes pour la table `conseiller`
--
ALTER TABLE `conseiller`
  ADD CONSTRAINT `FKDA949B4246EA0528` FOREIGN KEY (`ID_BANQUE`) REFERENCES `banque` (`ID_BANQUE`);

--
-- Contraintes pour la table `contrat_clause`
--
ALTER TABLE `contrat_clause`
  ADD CONSTRAINT `FKE2B5277B2FED6B62` FOREIGN KEY (`ID_CONTRAT`) REFERENCES `contrat` (`ID_CONTRAT`),
  ADD CONSTRAINT `FKE2B5277B4B7E0762` FOREIGN KEY (`ID_CLAUSE`) REFERENCES `clause` (`ID_CLAUSE`);

--
-- Contraintes pour la table `demande`
--
ALTER TABLE `demande`
  ADD CONSTRAINT `FK5C765F7A5543B7D` FOREIGN KEY (`ID_COMPTE_RENDU`) REFERENCES `compte_rendu` (`ID_COMPTE_RENDU`),
  ADD CONSTRAINT `FK5C765F7A5595EBB8` FOREIGN KEY (`ID_TRANSACTION`) REFERENCES `carte_transaction` (`ID_TRANSACTION`),
  ADD CONSTRAINT `FK5C765F7AADAD6FA7` FOREIGN KEY (`ID_INFO_FORMULAIRE`) REFERENCES `info_formulaire` (`ID_INFO_FORMULAIRE`),
  ADD CONSTRAINT `FK5C765F7AC4406D03` FOREIGN KEY (`ID_ACCUSE_RECEPTION`) REFERENCES `accuse_reception` (`ID_ACCUSE_RECEPTION`);

--
-- Contraintes pour la table `parametre`
--
ALTER TABLE `parametre`
  ADD CONSTRAINT `FK747EB52F46EA0528` FOREIGN KEY (`ID_BANQUE`) REFERENCES `banque` (`ID_BANQUE`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK36EBCB4BDD5804` FOREIGN KEY (`ID_COMPTE`) REFERENCES `compte` (`ID_COMPTE`);

--
-- Contraintes pour la table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK143BF46AAAE33E70` FOREIGN KEY (`ID_ROLE`) REFERENCES `role` (`ID_ROLE`),
  ADD CONSTRAINT `FK_ID_USER` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
