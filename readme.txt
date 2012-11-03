Was wird benötigt um entwickeln zu können:
- eclipse   (http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/heliossr1)
- GUI Builder (http://www.cloudgarden.com/jigloo/ oder http://code.google.com/intl/de-DE/javadevtools/download-wbpro.html)
- mysql Datenbank (http://www.mysql.de/downloads/mysql/)

Zum empfangen von POSAC Meldungen benötigen wir
- monitord (http://monitord.de/ ) 



SQL-Statements zum Anlegen der Datenbank.

CREATE DATABASE `monitord` /*!40100 DEFAULT CHARACTER SET latin1 */

CREATE TABLE `Alarmierungen` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `uhrzeit` datetime NOT NULL,
  `text` varchar(500) NOT NULL,
  `kennung` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `eot` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_kennung` (`kennung`),
  KEY `idx_uhrzeit` (`uhrzeit`)
) ENGINE=InnoDB AUTO_INCREMENT=1063 DEFAULT CHARSET=latin1

CREATE TABLE `Kalender` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `datum` datetime NOT NULL,
  `text` varchar(100) NOT NULL,
  `typ` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `new_fk_constraint` (`typ`),
  KEY `idx_datum` (`datum`),
  CONSTRAINT `new_fk_constraint` FOREIGN KEY (`typ`) REFERENCES `KalenderTyp` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1

CREATE TABLE `KalenderTyp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bezeichnung` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1

CREATE TABLE `POSAC_Status` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `posac_status` smallint(6) NOT NULL,
  `text` varchar(40) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `idx_posac` (`posac_status`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC


CREATE TABLE `monitord_fms` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uhrzeit` datetime NOT NULL,
  `status` smallint(2) unsigned DEFAULT NULL,
  `kennung` varchar(9) COLLATE latin1_german1_ci NOT NULL,
  `richtung` char(10) COLLATE latin1_german1_ci NOT NULL,
  `text` varchar(255) COLLATE latin1_german1_ci NOT NULL,
  `tki` char(1) COLLATE latin1_german1_ci NOT NULL DEFAULT '',
  `quelle` varchar(2) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci

CREATE TABLE `monitord_pocsag` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `uhrzeit` datetime NOT NULL,
  `kennung` int(10) NOT NULL,
  `sub` varchar(45) COLLATE latin1_german1_ci NOT NULL,
  `text` varchar(500) COLLATE latin1_german1_ci NOT NULL,
  `quelle` tinyint(2) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4333 DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci

CREATE TABLE `monitord_zvei` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uhrzeit` datetime NOT NULL,
  `kennung` varchar(5) COLLATE latin1_german1_ci NOT NULL,
  `typ` char(1) COLLATE latin1_german1_ci NOT NULL,
  `text` varchar(80) COLLATE latin1_german1_ci NOT NULL,
  `quelle` varchar(2) COLLATE latin1_german1_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_german1_ci


Anlegen des Trigger der alle Daten entfernt die nicht überwacht werden:
delimiter $
DROP TRIGGER IF EXISTS RemoveUnused;
CREATE TRIGGER RemoveUnused AFTER INSERT ON monitord_pocsag

	FOR EACH ROW BEGIN
	   DECLARE foundKennung int;
	   SET foundKennung = ( Select count(*) FROM Kennungen Where RIC = NEW.kennung) ;
					
	   IF foundKennung <= 0 Then
	      DELETE FROM monitord_pocsag Where id = NEW.id;	
	   END IF;
	END;   
$
delimiter ;