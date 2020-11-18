/* Creating the db */
DROP DATABASE IF EXISTS assignment2;
CREATE DATABASE assignment2;
USE assignment2;

/* Creating the tables */
CREATE TABLE IF NOT EXISTS member (
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
	fullname VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS messageauthor (
    messageid INT NOT NULL,
    username VARCHAR(50) NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS msg (
    messageid INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    created DATETIME NOT NULL,
    modified DATETIME NOT NULL,
    attachment LONGBLOB,
    PRIMARY KEY (messageid)
);

CREATE TABLE IF NOT EXISTS hashtag (
    hashtagid INT NOT NULL AUTO_INCREMENT,
    messageid INT NOT NULL,
    content VARCHAR(50) NOT NULL,
    PRIMARY KEY (hashtagid)
);

/* Inserting values */
INSERT INTO member (username, password, fullname, email)
    VALUES
        ('antoine', MD5('antoine'), 'antoine farley', 'antoine@example.com'),
        ('alex', MD5('alex'), 'alex cohen', 'alex@example.com'),
        ('zayn', MD5('zayn'), 'zayn muhammad', 'zayn@example.com'),
        ('wen', MD5('wen'), 'wen sun', 'wen@example.com');
