CREATE DATABASE IF NOT EXISTS cl_test_db;
USE cl_test_db;

-- DATABASE STRUCTURE SETUP

CREATE TABLE IF NOT EXISTS users
(
    username VARCHAR(50)  NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled  BOOLEAN
);


CREATE TABLE IF NOT EXISTS authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    PRIMARY KEY (username, authority),
    FOREIGN KEY (username) REFERENCES users (username)
);


CREATE TABLE IF NOT EXISTS leaderboard
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(100),
    isPublic    BOOLEAN DEFAULT TRUE
);


CREATE TABLE IF NOT EXISTS game_stat
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    account_username VARCHAR(50) NOT NULL,
    leaderboard_id   INT         NOT NULL,
    name             VARCHAR(50) NOT NULL,
    haveWon          boolean DEFAULT FALSE,
    victory_type     VARCHAR(20),
    victory_points   LONG        NOT NULL,
    science          LONG        NOT NULL,
    culture          LONG        NOT NULL,

    FOREIGN KEY (account_username) REFERENCES users (username),
    FOREIGN KEY (leaderboard_id) REFERENCES leaderboard (id)
);


CREATE TABLE IF NOT EXISTS invitations
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    sender_id      VARCHAR(50) NOT NULL,
    reciver_id     VARCHAR(50) NOT NULL,
    leaderboard_id INT         NOT NULL,

    FOREIGN KEY (sender_id) REFERENCES users (username),
    FOREIGN KEY (reciver_id) REFERENCES users (username),
    FOREIGN KEY (leaderboard_id) REFERENCES leaderboard (id)
);



-- DATA INSERTION

INSERT INTO leaderboard (name, description) VALUES ('leaderboardOne', 'descriptionOne');