DROP SCHEMA IF EXISTS `hibernate_demo`;
CREATE SCHEMA `hibernate_demo`;
use `hibernate_demo`;

DROP TABLE IF EXISTS `hibernate_demo`;
CREATE TABLE `employee` (
	`id` int(11) NOT NULL AUTO_INCREMENT,
	`first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `company` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;