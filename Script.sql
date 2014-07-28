--<ScriptOptions statementTerminator=";"/>


Drop database if exists flight_search2;
create database if not exists flight_search2; 
use flight_search2;

CREATE TABLE flight_data (
	flight_no VARCHAR(10) DEFAULT "ABC" NOT NULL,
	valid_till DATE,
	flight_time VARCHAR(10),
	flight_duration VARCHAR(10),
	fare INT,
	seat_avail VARCHAR(1),
	flight_class VARCHAR(2) DEFAULT "E" NOT NULL,
	dep_loc INT,
	arr_loc INT,
	PRIMARY KEY (flight_class,flight_no)
) ENGINE=InnoDB;

CREATE TABLE `flight_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `location` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `location_UNIQUE` (`location`)
) ENGINE=InnoDB;


