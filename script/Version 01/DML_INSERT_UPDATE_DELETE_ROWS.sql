# On ne devrait retrouver ici que des requetes d'insertion ou de mise à jour de tables

UPDATE `esecure`.`demande` SET `ETAT_DEMANDE` = 'IN_PROGRESS' WHERE `ETAT_DEMANDE`='';
/*
UPDATE  `esecure`.`demande` SET  `ETAT_DEMANDE` =  'PENDING' WHERE  `demande`.`ID_DEMANDE` =12;
UPDATE  `esecure`.`demande` SET  `ETAT_DEMANDE` =  'PENDING' WHERE  `demande`.`ID_DEMANDE` =2;
UPDATE  `esecure`.`demande` SET  `ETAT_DEMANDE` =  'PENDING' WHERE  `demande`.`ID_DEMANDE` =7;

UPDATE  `esecure`.`demande` SET  `ETAT_DEMANDE` =  'ACCEPTED' WHERE  `demande`.`ID_DEMANDE` =1;
UPDATE  `esecure`.`demande` SET  `ETAT_DEMANDE` =  'ACCEPTED' WHERE  `demande`.`ID_DEMANDE` =3;
UPDATE  `esecure`.`demande` SET  `ETAT_DEMANDE` =  'ACCEPTED' WHERE  `demande`.`ID_DEMANDE` =5;
UPDATE  `esecure`.`demande` SET  `ETAT_DEMANDE` =  'ACCEPTED' WHERE  `demande`.`ID_DEMANDE` =9;

UPDATE  `esecure`.`demande` SET  `ETAT_DEMANDE` =  'REJECTED' WHERE  `demande`.`ID_DEMANDE` =4;
UPDATE  `esecure`.`demande` SET  `ETAT_DEMANDE` =  'REJECTED' WHERE  `demande`.`ID_DEMANDE` =6;
UPDATE  `esecure`.`demande` SET  `ETAT_DEMANDE` =  'REJECTED' WHERE  `demande`.`ID_DEMANDE` =8;
*/


