package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_db.sql")
class JdbcGameTest {

    @Autowired
    private JdbcGame jdbcGame;

    @Test
    void getGame() {
        int gameId = 1;
        Game expectedGame = new Game(gameId, 1, "Game: 1");

        Game actualGame = jdbcGame.getGame(gameId);

        assertEquals(expectedGame, actualGame);
    }

    @Test
    void createGame() {
        int gameId = 2;
        Game expextedGame = new Game(gameId, 1, "Game: 2");

        Game actualGame = jdbcGame.createGame(expextedGame);

        assertEquals(expextedGame, actualGame);
    }

    @Test
    void editGame() {
        int gameId = 1;
        Game expectedGame = new Game(gameId, 1, "Edited GameName");

        Game actualGame = jdbcGame.editGame(expectedGame);

        assertEquals(expectedGame, actualGame);
    }

    @Test
    void deleteGameCheckReturnType() {
        int gameId = 1;
        boolean expectedResult = true;

        boolean actualResult = jdbcGame.deleteGame(gameId);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteGameCheckReturnTypeNegative() {
        int gameId = 0;
        boolean expectedResult = false;

        boolean actualResult = jdbcGame.deleteGame(gameId);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteGameCheckActualDeletedInDatabase() {
        int gameId = 1;
        Game expectedResult = null;

        jdbcGame.deleteGame(gameId);
        Game actualResult = jdbcGame.getGame(gameId);

        assertEquals(expectedResult, actualResult);
    }
}