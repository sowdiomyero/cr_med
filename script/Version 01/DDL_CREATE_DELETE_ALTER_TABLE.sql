
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;



CREATE TABLE IF NOT EXISTS `accuse_reception` (
  `ID_ACCUSE_RECEPTION` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_ACCUSE_RECEPTION` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MESSAGE` varchar(255) DEFAULT NULL,
  `TYPE_RETOUR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ACCUSE_RECEPTION`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;



CREATE TABLE IF NOT EXISTS `compte` (
  `ID_COMPTE` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_CREATION` date DEFAULT NULL,
  `ETAT` int(11) DEFAULT NULL,
  `LOGIN` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_COMPTE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;


CREATE TABLE IF NOT EXISTS `compte_rendu` (
  `ID_COMPTE_RENDU` bigint(20) NOT NULL AUTO_INCREMENT,
  `DATE_COMPTE_RENDU` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MESSAGE` varchar(255) DEFAULT NULL,
  `TYPE_RETOUR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_COMPTE_RENDU`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;


CREATE TABLE IF NOT EXISTS `banque` (
  `ID_BANQUE` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADRESSE` varchar(255) DEFAULT NULL,
  `NUMERO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_BANQUE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;


CREATE TABLE IF NOT EXISTS `carte_bancaire` (
  `ID_CARTE_BANCAIRE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CRYPTO` int(11) DEFAULT NULL,
  `DATE_EXPIR` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `NUM_CARTE` varchar(255) DEFAULT NULL,
  `TYPE_CARTE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_CARTE_BANCAIRE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;


CREATE TABLE IF NOT EXISTS `carte_transaction` (
  `ID_TRANSACTION` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE_TRANSACTION` int(11) DEFAULT NULL,
  `DATE_TRANSACTION` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MONTANT` double DEFAULT NULL,
  `ID_ADRESSE` bigint(20) DEFAULT NULL,
  `ID_CARTE_BANCAIRE` bigint(20) DEFAULT NULL,
  `NOM_COMMERCANT` varchar(255) DEFAULT NULL,
  `SITE_WEB_COMMERCANT` varchar(255) DEFAULT NULL,
  `etat` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`ID_TRANSACTION`),
  KEY `FK_ID_CARTE_BANCAIRE` (`ID_CARTE_BANCAIRE`),
  KEY `FK_ID_ADRESSE` (`ID_ADRESSE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;


CREATE TABLE IF NOT EXISTS `client` (
  `ID_CLIENT` bigint(20) NOT NULL AUTO_INCREMENT,
  `NOM_CLIENT` varchar(255) DEFAULT NULL,
  `PRENOM_CLIENT` varchar(255) DEFAULT NULL,
  `NUMERO_COMPTE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `ID_CARTE_BANCAIRE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_CLIENT`),
  KEY `FK_ID_CLIENT_CARTE_BANCAIRE` (`ID_CARTE_BANCAIRE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;


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
  KEY `FK_ID_CLIENT` (`ID_CLIENT`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;



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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;


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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------


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


CREATE TABLE IF NOT EXISTS `role` (
  `ID_ROLE` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(255) DEFAULT NULL,
  `ROLE_DESC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_ROLE`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;


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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3;



CREATE TABLE IF NOT EXISTS `user_role` (
  `ID_USER` bigint(20) NOT NULL,
  `ID_ROLE` bigint(20) NOT NULL,
  KEY `FK_ID_ROLE` (`ID_ROLE`),
  KEY `FK_ID_USER` (`ID_USER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `adresse`
  ADD CONSTRAINT `FK_ID_CLIENT` FOREIGN KEY (`ID_CLIENT`) REFERENCES `client` (`ID_CLIENT`);


ALTER TABLE `carte_transaction`
  ADD CONSTRAINT `FK_ID_ADRESSE` FOREIGN KEY (`ID_ADRESSE`) REFERENCES `adresse` (`ID_ADRESSE`),
  ADD CONSTRAINT `FK_ID_CARTE_BANCAIRE` FOREIGN KEY (`ID_CARTE_BANCAIRE`) REFERENCES `carte_bancaire` (`ID_CARTE_BANCAIRE`);


ALTER TABLE `client`
  ADD CONSTRAINT `FK_ID_CLIENT_CARTE_BANCAIRE` FOREIGN KEY (`ID_CARTE_BANCAIRE`) REFERENCES `carte_bancaire` (`ID_CARTE_BANCAIRE`);

ALTER TABLE `demande`
  ADD CONSTRAINT `FK_ID_AR_DEMANDE` FOREIGN KEY (`ID_ACCUSE_RECEPTION`) REFERENCES `accuse_reception` (`ID_ACCUSE_RECEPTION`),
  ADD CONSTRAINT `FK_ID_COMPTE_RENDU_DEMANDE` FOREIGN KEY (`ID_COMPTE_RENDU`) REFERENCES `compte_rendu` (`ID_COMPTE_RENDU`),
  ADD CONSTRAINT `FK_ID_INFO_FORM_DEMANDE` FOREIGN KEY (`ID_INFO_FORMULAIRE`) REFERENCES `info_formulaire` (`ID_INFO_FORMULAIRE`),
  ADD CONSTRAINT `FK_ID_TRANSACTION_DEMANDE` FOREIGN KEY (`ID_TRANSACTION`) REFERENCES `carte_transaction` (`ID_TRANSACTION`);


ALTER TABLE `user`
  ADD CONSTRAINT `FK_ID_COMPTE` FOREIGN KEY (`ID_COMPTE`) REFERENCES `compte` (`ID_COMPTE`);


ALTER TABLE `user_role`
  ADD CONSTRAINT `FK_ID_ROLE` FOREIGN KEY (`ID_ROLE`) REFERENCES `role` (`ID_ROLE`),
  ADD CONSTRAINT `FK_ID_USER` FOREIGN KEY (`ID_USER`) REFERENCES `user` (`ID_USER`);


ALTER TABLE  `demande` ADD  `ETAT_DEMANDE` VARCHAR( 20 ) NOT NULL AFTER  `ID_DEMANDE`;
ALTER TABLE  `carte_transaction` ADD  `HREF_LOGO_COMMERCANT` VARCHAR( 64 ) NULL AFTER  `SITE_WEB_COMMERCANT`;
ALTER TABLE  `carte_bancaire` ADD  `ID_CLIENT` BIGINT(20) NOT NULL AFTER  `TYPE_CARTE` , ADD INDEX (  `ID_CLIENT` ) ;

ALTER TABLE  `carte_bancaire` ADD FOREIGN KEY (  `ID_CLIENT` ) REFERENCES  `esecure`.`client` (`ID_CLIENT`) ON DELETE RESTRICT ON UPDATE RESTRICT ;



DROP  TABLE IF EXISTS `banque` ;
DROP  TABLE IF EXISTS `parametre` ;

CREATE TABLE IF NOT EXISTS `parametre` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `ID_MESSAGERIE` bigint(20) DEFAULT NULL,
  `ID_RAPPORT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`),
  UNIQUE KEY `FK_ID_RAPPORT` (`ID_RAPPORT`),
  UNIQUE KEY `FK_ID_MESSAGERIE` (`ID_MESSAGERIE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `banque` (
  `ID_BANQUE` bigint(20) NOT NULL AUTO_INCREMENT,
  `CIGLE_BANQUE` varchar(255) DEFAULT NULL,
  `CODE_BANQUE` varchar(255) DEFAULT NULL,
  `CONTACT_BANQUE` varchar(255) DEFAULT NULL,
  `FRONT_END_URL` varchar(255) DEFAULT NULL,
  `VALIDATION_FORM_URL` varchar(255) DEFAULT NULL,
  `WS_ENDPOINT_URL` varchar(255) DEFAULT NULL,
  `PASSWORD_WS_BANQUE` varchar(255) DEFAULT NULL,
  `USERNAME_WS_BANQUE` varchar(255) DEFAULT NULL,
  `ID_PARAM` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID_BANQUE`),
  KEY `FK_ID_PARAM` (`ID_PARAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `messagerie_param` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARAM_SMTP_EMAIL` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_HOST` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PASSWORD` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_PORT` int(11) DEFAULT NULL,
  `PARAM_SMTP_SSL` bit(1) DEFAULT NULL,
  `PARAM_SMTP_TRANSPORT` varchar(255) DEFAULT NULL,
  `PARAM_SMTP_USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `rapport_param` (
  `ID_PARAM` bigint(20) NOT NULL AUTO_INCREMENT,
  `PARAM_RAPPORT_BENEFICIAIRE` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_DONNES` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_FORMAT` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_FREQUENCE` varchar(255) DEFAULT NULL,
  `PARAM_RAPPORT_REPERTOIRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_PARAM`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


ALTER TABLE `parametre`
  ADD CONSTRAINT `FK_ID_MESSAGERIE` FOREIGN KEY (`ID_MESSAGERIE`) REFERENCES `messagerie_param` (`ID_PARAM`),
  ADD CONSTRAINT `FK_ID_RAPPORT` FOREIGN KEY (`ID_RAPPORT`) REFERENCES `rapport_param` (`ID_PARAM`);

ALTER TABLE `banque`
  ADD CONSTRAINT `FK_ID_PARAM` FOREIGN KEY (`ID_PARAM`) REFERENCES `parametre` (`ID_PARAM`);