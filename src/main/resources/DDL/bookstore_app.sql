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
    `age` int,
    `phone_number` text DEFAULT NULL,
    `address` text DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--

-- Table for book categories
DROP TABLE IF EXISTS `book_categories`;

CREATE TABLE `book_categories` (
    `id` int NOT NULL AUTO_INCREMENT,
    `category_name` text DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--

-- Table for books
DROP TABLE IF EXISTS `books`;

CREATE TABLE `books` (
    `id` int NOT NULL AUTO_INCREMENT,
    `title` text DEFAULT NULL,
    `category_id` int,
    `user_id` int,

     PRIMARY KEY (`id`),
     FOREIGN KEY (`category_id`) REFERENCES book_categories(`id`)
     ON DELETE CASCADE
     ON UPDATE CASCADE,
     FOREIGN KEY (`user_id`) REFERENCES users(`id`)
     ON DELETE CASCADE
     ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--

-- Table for book authors
DROP TABLE IF EXISTS `book_authors`;

CREATE TABLE `book_authors` (
    `id` int NOT NULL AUTO_INCREMENT,
    `author_name` text DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--

-- Table for author writes book relationship
DROP TABLE IF EXISTS `writes_book`;

CREATE TABLE `writes_book` (
    `author_id` int NOT NULL,
    `book_id` int NOT NULL,

    PRIMARY KEY (`author_id`, `book_id`),
    FOREIGN KEY (`author_id`) REFERENCES book_authors(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`book_id`) REFERENCES books(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
--

-- Table for favorite authors relationship
DROP TABLE IF EXISTS `favorite_authors`;

CREATE TABLE `favorite_authors` (
    `user_id` int NOT NULL,
    `author_id` int NOT NULL,

    PRIMARY KEY (`user_id`, `author_id`),
    FOREIGN KEY (`user_id`) REFERENCES users(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`author_id`) REFERENCES book_authors(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
--

-- Table for favorite categories relationship
DROP TABLE IF EXISTS `favorite_categories`;

CREATE TABLE `favorite_categories` (
    `user_id` int NOT NULL,
    `category_id` int NOT NULL,

    PRIMARY KEY (`user_id`, `category_id`),
    FOREIGN KEY (`user_id`) REFERENCES users(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`category_id`) REFERENCES book_categories(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
--

-- Table for requests
DROP TABLE IF EXISTS `requests_book`;

CREATE TABLE `requests_book` (
    `user_id` int NOT NULL,
    `book_id` int NOT NULL,

    PRIMARY KEY (`user_id`, `book_id`),
    FOREIGN KEY (`user_id`) REFERENCES users(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (`book_id`) REFERENCES books(`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
--