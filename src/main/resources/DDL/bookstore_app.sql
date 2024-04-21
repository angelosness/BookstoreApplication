CREATE DATABASE IF NOT EXISTS `bookstore_app_db`;
USE `bookstore_app_db`;

DROP TABLE IF EXISTS `favorite_authors`;
DROP TABLE IF EXISTS `favorite_categories`;
DROP TABLE IF EXISTS `requests_book`;
DROP TABLE IF EXISTS `writes_book`;
DROP TABLE IF EXISTS `books`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `book_authors`;
DROP TABLE IF EXISTS `book_categories`;

-- Table for users
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
CREATE TABLE `book_categories` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` text DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--

-- Table for books
CREATE TABLE `books` (
    `id` int NOT NULL AUTO_INCREMENT,
    `user_id` int,
    `title` text DEFAULT NULL,
    `category_id` int,
    `summary` text DEFAULT NULL,

     PRIMARY KEY (`id`),
     FOREIGN KEY (`user_id`) REFERENCES users(`id`)
     ON DELETE CASCADE
     ON UPDATE CASCADE,
     FOREIGN KEY (`category_id`) REFERENCES book_categories(`id`)
     ON DELETE CASCADE
     ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--

-- Table for book authors
CREATE TABLE `book_authors` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` text DEFAULT NULL,

    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
--

-- Table for author writes book relationship
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


-- Preload book categories
INSERT INTO book_categories (name)
VALUES
("Sci-Fi"),
("Fantasy");
--