package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.GameStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import victoryTypeEnum.VictoryType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_db.sql")
class JdbcGameStatTest {

    @Autowired
    private JdbcGameStat jdbcGameStat;

    @Test
    void getGameStat() {
        GameStat expectedGameStat = new GameStat(2, "john doe", 1, "game2", true, 777, VictoryType.SCIENTIFIC, 1811, 21);
        GameStat actualGameStat = jdbcGameStat.getGameStat(2);

        assertEquals(expectedGameStat, actualGameStat);
    }

    @Test
    void createGameStat() {
        GameStat expectedGameStat = new GameStat(4, "Chris", 1, "11/07-2024", true, 456, VictoryType.DOMINATION, 756, 642);
        GameStat gameStat = expectedGameStat;

        jdbcGameStat.createGameStat(gameStat);
        GameStat actualGameStat = jdbcGameStat.getGameStat(4);

        assertEquals(expectedGameStat, actualGameStat);
    }

    @Test
    void editGameStat() {
        GameStat expectedGameStats = new GameStat(3, "Chris", 1, "12/07-2024", true, 777, VictoryType.CULTURAL, 756, 642);
        GameStat gameStatsToUpdate = expectedGameStats;

        GameStat actualGameStats = jdbcGameStat.editGameStat(gameStatsToUpdate);

        assertEquals(expectedGameStats, actualGameStats);
    }

    @Test
    void attemptEditGameStatsThatDoesNotExist() {
        GameStat expectedGameStats = new GameStat(0, "Chris", 1, "12/07-2024", true, 777, VictoryType.CULTURAL, 756, 642);
        GameStat gameStatsToUpdate = expectedGameStats;

        GameStat actualGameStats = jdbcGameStat.editGameStat(gameStatsToUpdate);

        assertNull(actualGameStats);
    }

    @Test
    void deleteGameStatTestForReturnType() {
        boolean expectedResult = true;

        boolean actualResult = jdbcGameStat.deleteGameStat(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteGameStatTestForActualDeletion() {
        GameStat expectedResult = null;

        jdbcGameStat.deleteGameStat(1);
        GameStat actualResult = jdbcGameStat.getGameStat(1);

        assertEquals(expectedResult, actualResult);
    }
}