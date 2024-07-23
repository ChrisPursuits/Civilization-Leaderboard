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

CREATE TABLE IF NOT EXISTS game
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(50) NOT NULL,
    leaderboard_id INT,
    FOREIGN KEY (leaderboard_id) REFERENCES leaderboard (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS civilization_stat
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    account_username  VARCHAR(50) NOT NULL,
    game_id           INT,
    name              VARCHAR(50) NOT NULL,
    is_first_place    boolean              DEFAULT FALSE,
    is_second_place   boolean              DEFAULT FALSE,
    is_third_place    boolean              DEFAULT FALSE,
    is_other_place    boolean              DEFAULT FALSE,
    victory_type      VARCHAR(20) NOT NULL DEFAULT 'LOSE',
    victory_points    INT        NOT NULL DEFAULT 0,
    military_strength INT        NOT NULL DEFAULT 0,
    science           INT        NOT NULL DEFAULT 0,
    culture           INT        NOT NULL DEFAULT 0,
    gold              INT        NOT NULL DEFAULT 0,
    religious_points  INT        NOT NULL DEFAULT 0,
    diplomatic_favors INT        NOT NULL DEFAULT 0,


    FOREIGN KEY (account_username) REFERENCES users (username) ON DELETE CASCADE,
    FOREIGN KEY (game_id) REFERENCES game (id) ON DELETE CASCADE
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

INSERT INTO users (username, password) value ('Engjëll', 123);
INSERT INTO users (username, password) value ('Chris', 123);
INSERT INTO users (username, password) value ('Mikkel', 123);
INSERT INTO users (username, password) value ('Markus', 123);

INSERT INTO leaderboard (name, description)
VALUES ('leaderboardOne', 'descriptionOne');

INSERT INTO game (name, leaderboard_id)
    VALUE ('Game: 1', 1);

INSERT INTO civilization_stat (account_username, game_id, name, is_other_place, victory_points,
                               military_strength, science, culture, gold, religious_points, diplomatic_favors)
VALUES ('Engjëll', 1, 'Portugal', true, 521, 1, 111, 121, 2, 3, 4);

INSERT INTO civilization_stat (account_username, game_id, name, is_first_place, victory_type, victory_points,
                               military_strength, science, culture, gold, religious_points, diplomatic_favors)
VALUES ('Chris', 1, 'China', true, 'SCIENTIFIC', 777, 1, 1811, 21, 2, 3, 4);

INSERT INTO civilization_stat (account_username, game_id, name, is_second_place, victory_type, victory_points,
                               military_strength, science, culture, gold, religious_points, diplomatic_favors)
VALUES ('Mikkel', 1, 'Japan', true, 'SECOND', 777, 1, 231, 321, 2, 3, 4);

INSERT INTO civilization_stat (account_username, game_id, name, is_third_place, victory_type, victory_points,
                               military_strength, science, culture, gold, religious_points, diplomatic_favors)
VALUES ('Markus', 1, 'Spain', true, 'THIRD', 417, 1, 425, 412, 2, 3, 4);
