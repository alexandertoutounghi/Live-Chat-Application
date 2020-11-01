/* Creating the db */
DROP DATABASE IF EXISTS assignment2;
CREATE DATABASE assignment2;
USE assignment2;

/* Creating the tables */
CREATE TABLE IF NOT EXISTS member (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS messageauthor (
    messageid INT NOT NULL,
    username VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS msg (
    messageid int NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created DATE NOT NULL,
    modified DATE NOT NULL,
    attachment LONGBLOB,
    PRIMARY KEY (messageid)
);

CREATE TABLE IF NOT EXISTS hashtags (
    messageid int NOT NULL,
    content VARCHAR(50) NOT NULL,
    PRIMARY KEY (messageid)
);

/* Inserting values */
INSERT INTO member (username, password)
    VALUES
        ('antoine', MD5('antoine')),
        ('alex', MD5('alex')),
        ('zayn', MD5('zayn')),
        ('wen', MD5('wen'));
