CREATE DATABASE IF NOT EXISTS `bookstore_app_db`;
USE `bookstore_app_db`;

-- Table for users
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` text DEFAULT NULL,
    `password` text DEFAULT NULL,
    `first_name` text DEFAULT NULL,
    `last_name` text DEFAULT NULL,
    `age` int DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--