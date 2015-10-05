-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Ven 20 Février 2015 à 16:57
-- Version du serveur: 5.5.35
-- Version de PHP: 5.4.36-0+deb7u3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;


INSERT INTO `accuse_reception` (`ID_ACCUSE_RECEPTION`, `DATE_ACCUSE_RECEPTION`, `MESSAGE`, `TYPE_RETOUR`) VALUES
(9, '2015-02-19 15:26:04', 'transaction passée avec succés', 'ok'),
(12, '2015-02-19 19:02:33', 'Demande enregistrée avec succès', 'OK'),
(17, '2015-02-20 12:31:45', 'transaction passée avec succés', 'ok');

INSERT INTO `compte_rendu` (`ID_COMPTE_RENDU`, `DATE_COMPTE_RENDU`, `MESSAGE`, `TYPE_RETOUR`) VALUES
(1, '2015-02-05 00:00:00', 'cest bon', 'ok'),
(2, '2015-02-05 00:00:00', 'test insertion cr', 'OK'),
(3, '2015-02-05 00:00:00', 'test insertion cr', 'OK');
--
-- Contenu de la table `banque`
--
INSERT INTO `rapport_param` (`ID_PARAM`, `PARAM_RAPPORT_BENEFICIAIRE`, `PARAM_RAPPORT_DONNES`, `PARAM_RAPPORT_FORMAT`, `PARAM_RAPPORT_FREQUENCE`, `PARAM_RAPPORT_REPERTOIRE`) VALUES
(1, 'sowdiomyero@gmail.com', 'TRANSACTIONS_FAILED', 'PDF', 'TOUS_LES_LUNDIS', 'C:\\dev\\sauvegardes\\ESECURE\\rapports');

INSERT INTO `parametre` (`ID_PARAM`, `ID_MESSAGERIE`, `ID_RAPPORT`) VALUES
(1,NULL,1);

INSERT INTO `banque` (`ID_BANQUE`, `CIGLE_BANQUE`, `CODE_BANQUE`, `CONTACT_BANQUE`, `PASSWORD_WS_BANQUE`, `USERNAME_WS_BANQUE`, `ID_PARAM`, `FRONT_END_URL`, `VALIDATION_FORM_URL`, `WS_ENDPOINT_URL`) VALUES
(1, 'BNP', '3000320', '00331 18 75 42 63', 'esecure', 'esecure', 1, 'http://localhost:8080/esecure/', 'http://localhost:8080/esecure/clientform?idDemande=', '');


INSERT INTO `client` (`ID_CLIENT`, `NOM_CLIENT`, `PRENOM_CLIENT`, `NUMERO_COMPTE`, `EMAIL`) VALUES
(7, 'ELBAZ', 'Nicoals', '123654', 'n.elbaz@gmail.com'),
(13, 'DEVILLE', 'Pierre', '123456', 'dpierre@gmail.com'),
(15, 'LEROY', 'Chantal', '123456', 'lchantall@gmail.com'),
(18, 'LAURE', 'Manadou', '123456', 'lmanadoul@gmail.com'),
(20, 'NAFSY', 'Ikbel', '123456', 'nikbel@gmail.com'),
(22, 'SALL', 'Rahman', '123456', 'srahman@gmail.com');


INSERT INTO `adresse` (`ID_ADRESSE`, `NUMERO_RUE`, `NOM_RUE`, `PORTE`, `TYPE_RESIDENCE`, `NOM_RESIDENCE`, `NUMERO_ETAGE`, `CODE_POSTAL`, `VILLE`, `ID_CLIENT`) VALUES
(1, 24, 'chemin d''aranchette', 301, 'Residence', 'campus des  sciences', 3, '31400', 'Toulouse', NULL),
(2, 24, 'chemin d''aranchette', 301, 'campus universitaire', 'aranchettte', 3, '64100', 'bayonne', NULL),
(3, 24, 'chemin du bassin', 301, 'CitÃ© universiaire', 'claude delorme', 3, '13000', 'Marseille', NULL);


INSERT INTO `carte_bancaire` (`ID_CARTE_BANCAIRE`, `CRYPTO`, `DATE_EXPIR`, `NUM_CARTE`, `TYPE_CARTE`, `ID_CLIENT`) VALUES
(16, 123, '2015-02-19 15:26:04', '6452 3214 2314 5623', 'NON_DEFINI', 13),
(18, 0, NULL, '987-654-321', 'NON_DEFINI', 7),
(24, 123, '2015-02-20 12:31:45', '6452 3214 2314 5623', 'NON_DEFINI', 15);


INSERT INTO `carte_transaction` (`ID_TRANSACTION`, `CODE_TRANSACTION`, `DATE_TRANSACTION`, `MONTANT`, `ID_ADRESSE`, `ID_CARTE_BANCAIRE`, `NOM_COMMERCANT`, `SITE_WEB_COMMERCANT`, `HREF_LOGO_COMMERCANT`, `etat`) VALUES
(24, 1, '2015-02-19 15:26:04', 2015, 1, 16, 'EBAY', 'www.ebay.com', NULL, 'CANCELLED'),
(26, 0, '2015-02-19 19:02:32', 550.68, 2, 18, 'SOW', 'www.gandal.com', 'http://assets.fontsinuse.com/', 'PROCESSING'),
(25, 0, '2015-02-19 19:02:32', 550.68, 2, 18, 'GANDAL', 'www.gandal.com', 'http://assets.fontsinuse.com/', 'PROCESSING'),
(31, 1, '2015-02-20 12:31:45', 2015, 3, 24, 'EBAY', 'www.ebay.com', NULL, 'REJECTED');


INSERT INTO `info_formulaire` (`ID_INFO_FORMULAIRE`, `NOM_CLIENT`, `PRENOM_CLIENT`, `NUMERO_COMPTE`, `NUMERO_CARTE_BANCAIRE`, `EMAIL`, `NUMERO_RUE`, `NOM_RUE`, `NOM_RESIDENCE`, `TYPE_RESIDENCE`, `NUMERO_ETAGE`, `CODE_POSTAL`, `VILLE`) VALUES
(7, 'seydou', 'niang', '1245 1234 2530', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(13, 'seydou', 'niang', '1245 1234 2530', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(14, 'seydou', 'niang', '1245 1234 2530', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(15, 'seydou', 'niang', '1245 1234 2530', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(16, 'seydou', 'niang', '1245 1234 2530', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(17, 'seydou', 'niang', '1245 1234 2530', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro'),
(18, 'seydou', 'niang', '1245 1234 2530', '123456', 'douseyi@live.com', '24', 'medina centre', 'le cayor', 'appartement', 0, '10000', 'nioro');


INSERT INTO `demande` (`ID_DEMANDE`, `ETAT_DEMANDE`, `NUMERO_JETON`, `ID_ACCUSE_RECEPTION`, `ID_COMPTE_RENDU`, `ID_INFO_FORMULAIRE`, `ID_TRANSACTION`) VALUES
(1, 'IN_PROGRESS', 1, 9, 1, 7, 24),
(6, 'PENDING', 0, 12, 2, NULL, 26),
(7, 'REJECTED', 1, 17, 3, 13, 31);


INSERT INTO `role` (`ID_ROLE`, `ROLE_NAME`, `ROLE_DESC`) VALUES
(1, 'ROLE_SUPER_ADMIN', 'Super administrateur'),
(2, 'ROLE_ADMIN', 'administrateur'),
(3, 'ROLE_USER', 'Utilisateur Simple');


INSERT INTO `compte` (`ID_COMPTE`, `DATE_CREATION`, `ETAT`, `LOGIN`, `PASSWORD`) VALUES
(7, '2015-02-20', 1, 'sowdiomyero', '77cb141d760ec14a09e1a4f1e3373d5865b41d300adb854200ddd6096c9ed55f'),
(2, '2015-02-20', 1, 'm.nassur', 'e01e45d89036f2c7ee4719b54e0f44d66d5fcb498f390c1246dea0e9b30475c4'),
(6, '2015-02-20', 1, 'esecure', 'ce68c5e2fb3a5f00489fc6f2c5d27640f4989a5158887eed038fcd4a565a08f1'),
(1, '2015-02-20', 1, 'dysow', '77cb141d760ec14a09e1a4f1e3373d5865b41d300adb854200ddd6096c9ed55f'),
(13, '2015-02-20', 1, 'ibra', 'd1d0abe7dc8fcff02f17c3a26d0e3281f41d149e2d7fb85acd1d61db7bf5a287');

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`ID_USER`, `USER_LOGGED`, `USER_MAIL`, `USER_NOM`, `USER_PHONE`, `USER_PRENOM`, `ID_COMPTE`) VALUES
(1, 1, 'dysow@groupeidyal.com', 'Global', '33669932494', 'Diom Yero', 1),
(2, 1, 'm.nassur@almteam-consulting.com', 'MHOUMADI', '33669932494', 'Nassur', 2),
(3, 1, 'idyal.esecure@gmail.com', 'Esecure', '33669932494', 'Esecure', 6),
(4, 1, 'sowdiomyero@gmail.com', 'SOW', '33669932494', 'Diom Yero', 7),
(5, 1, 'ibra@khidma.org', 'Yade', '33669932494', 'Hibrahima', 13);

--
-- Contenu de la table `user_role`
--

INSERT INTO `user_role` (`ID_USER`, `ID_ROLE`) VALUES
(1, 2),
(1, 1),
(2, 2),
(3, 2),
(3, 1),
(4, 3),
(5, 2),
(5, 1);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
