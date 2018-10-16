/*
SQLyog Community v12.5.1 (64 bit)
MySQL - 10.1.30-MariaDB : Database - db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `db`;

/*Table structure for table `koriscenjeusluge` */

DROP TABLE IF EXISTS `koriscenjeusluge`;

CREATE TABLE `koriscenjeusluge` (
  `KoriscenjeID` int(11) NOT NULL,
  `KorisnikID` int(11) NOT NULL,
  `DatumOd` date NOT NULL,
  `DatumDo` date NOT NULL,
  `UslugaID` int(11) NOT NULL,
  PRIMARY KEY (`KoriscenjeID`,`KorisnikID`),
  KEY `KorisnikID` (`KorisnikID`),
  KEY `UslugaID` (`UslugaID`),
  CONSTRAINT `koriscenjeusluge_ibfk_1` FOREIGN KEY (`KorisnikID`) REFERENCES `korisnik` (`KorisnikID`) ON DELETE CASCADE,
  CONSTRAINT `koriscenjeusluge_ibfk_2` FOREIGN KEY (`UslugaID`) REFERENCES `usluga` (`UslugaID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `koriscenjeusluge` */

insert  into `koriscenjeusluge`(`KoriscenjeID`,`KorisnikID`,`DatumOd`,`DatumDo`,`UslugaID`) values 
(1,1,'2018-03-22','2018-06-22',1),
(2,1,'2018-04-22','2018-05-22',2),
(5,3,'2018-05-05','2018-08-05',1),
(6,3,'2018-05-05','2018-06-05',2),
(7,3,'2018-05-05','2018-06-05',5),
(19,2,'2018-05-05','2018-06-05',4);

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `KorisnikID` int(11) NOT NULL,
  `Ime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Prezime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Telefon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Adresa` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ZaposleniID` int(11) NOT NULL,
  `PTTBroj` int(11) NOT NULL,
  PRIMARY KEY (`KorisnikID`),
  KEY `ZaposleniID` (`ZaposleniID`),
  KEY `PTTBroj` (`PTTBroj`),
  CONSTRAINT `korisnik_ibfk_1` FOREIGN KEY (`ZaposleniID`) REFERENCES `zaposleni` (`ZaposleniID`),
  CONSTRAINT `korisnik_ibfk_2` FOREIGN KEY (`PTTBroj`) REFERENCES `mesto` (`PTTBroj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `korisnik` */

insert  into `korisnik`(`KorisnikID`,`Ime`,`Prezime`,`Telefon`,`Adresa`,`ZaposleniID`,`PTTBroj`) values 
(1,'Pera','Peric','0642315873','Jove Ilica 13',1,21000),
(2,'Miljan','Miljanic','0657865125','Cara Dusana 13',2,32000),
(3,'Nemanja','Nemanjic','0613587892','Sime Mrkalja 64',3,14000);

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `PTTBroj` int(11) NOT NULL,
  `Naziv` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`PTTBroj`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `mesto` */

insert  into `mesto`(`PTTBroj`,`Naziv`) values 
(11000,'Beograd'),
(14000,'Valjevo'),
(18000,'Niš'),
(21000,'Novi Sad'),
(31000,'Užice'),
(32000,'Čačak');

/*Table structure for table `usluga` */

DROP TABLE IF EXISTS `usluga`;

CREATE TABLE `usluga` (
  `UslugaID` int(11) NOT NULL,
  `NazivUsluge` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ZaposleniID` int(11) NOT NULL,
  PRIMARY KEY (`UslugaID`),
  KEY `ZaposleniID` (`ZaposleniID`),
  CONSTRAINT `usluga_ibfk_1` FOREIGN KEY (`ZaposleniID`) REFERENCES `zaposleni` (`ZaposleniID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `usluga` */

insert  into `usluga`(`UslugaID`,`NazivUsluge`,`ZaposleniID`) values 
(1,'Teretana',1),
(2,'Kardio',1),
(3,'Relaks Masaža',1),
(4,'Sauna',4),
(5,'Restoran',3);

/*Table structure for table `zaposleni` */

DROP TABLE IF EXISTS `zaposleni`;

CREATE TABLE `zaposleni` (
  `ZaposleniID` int(11) NOT NULL,
  `Ime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Prezime` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `KorisnickoIme` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Lozinka` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`ZaposleniID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `zaposleni` */

insert  into `zaposleni`(`ZaposleniID`,`Ime`,`Prezime`,`KorisnickoIme`,`Lozinka`) values 
(1,'Miloš','Mastilović','milos','milos'),
(2,'Djordje','Komlen','djole','djole'),
(3,'Milica','Komlen','milica','milica'),
(4,'Božidar','Mastilović','boza','boza');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
