DROP DATABASE cl_test_db;
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
    name        VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    isPublic    BOOLEAN DEFAULT TRUE
);


CREATE TABLE IF NOT EXISTS game_stat
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    account_username VARCHAR(50) NOT NULL,
    leaderboard_id   INT,
    name             VARCHAR(50) NOT NULL,
    haveWon          boolean              DEFAULT FALSE,
    victory_type     VARCHAR(20) NOT NULL DEFAULT 'LOSE',
    victory_points   LONG        NOT NULL,
    science          LONG        NOT NULL,
    culture          LONG        NOT NULL,

    FOREIGN KEY (account_username) REFERENCES users (username) ON DELETE CASCADE,
    FOREIGN KEY (leaderboard_id) REFERENCES leaderboard (id) ON DELETE CASCADE
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

INSERT INTO users (username, password) value ('john doe', 123);
INSERT INTO users (username, password) value ('Chris', 123);
INSERT INTO users (username, password) value ('Mikkel', 123);

INSERT INTO leaderboard (name, description)
VALUES ('leaderboardOne', 'descriptionOne');

INSERT INTO game_stat (account_username, leaderboard_id, name, victory_points, science, culture)
values ('john doe', 1, 'game1', 521, 111, 121);

INSERT INTO game_stat (account_username, leaderboard_id, name, haveWon, victory_type, victory_points, science, culture)
values ('john doe', 1, 'game2', true, 'SCIENTIFIC', 777, 1811, 21);

INSERT INTO game_stat (account_username, leaderboard_id, name, haveWon, victory_type, victory_points, science, culture)
values ('Mikkel', 1, 'game1', true, 'CULTURAL', 777, 231, 321);

INSERT INTO game_stat (account_username, leaderboard_id, name, haveWon, victory_type, victory_points, science, culture)
values ('Mikkel', 1, 'game2', true, 'DOMINATION', 417, 425, 412);

-- PRIVATE GAMESTAT
INSERT INTO game_stat (account_username, name, haveWon, victory_type, victory_points, science, culture)
VALUES ('Chris', '11/07-2024', true, 'CULTURAL', 521, 111, 1452)