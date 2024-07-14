package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.CivilizationStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import victoryTypeEnum.VictoryType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_db.sql")
class JdbcCivilizationStatTest {

    @Autowired
    private JdbcGameStat jdbcGameStat;

    @Test
    void getGameStat() {
        CivilizationStat expectedCivilizationStat = new CivilizationStat(2, "john doe", 1, "game2", true, 777, VictoryType.SCIENTIFIC, 1811, 21);
        CivilizationStat actualCivilizationStat = jdbcGameStat.getGameStat(2);

        assertEquals(expectedCivilizationStat, actualCivilizationStat);
    }

    @Test
    void createGameStat() {
        CivilizationStat expectedCivilizationStat = new CivilizationStat(4, "Chris", 1, "11/07-2024", true, 456, VictoryType.DOMINATION, 756, 642);
        CivilizationStat civilizationStat = expectedCivilizationStat;

        jdbcGameStat.createGameStat(civilizationStat);
        CivilizationStat actualCivilizationStat = jdbcGameStat.getGameStat(4);

        assertEquals(expectedCivilizationStat, actualCivilizationStat);
    }

    @Test
    void editGameStat() {
        CivilizationStat expectedCivilizationStats = new CivilizationStat(3, "Chris", 1, "12/07-2024", true, 777, VictoryType.CULTURAL, 756, 642);
        CivilizationStat civilizationStatsToUpdate = expectedCivilizationStats;

        CivilizationStat actualCivilizationStats = jdbcGameStat.editGameStat(civilizationStatsToUpdate);

        assertEquals(expectedCivilizationStats, actualCivilizationStats);
    }

    @Test
    void attemptEditGameStatsThatDoesNotExist() {
        CivilizationStat expectedCivilizationStats = new CivilizationStat(0, "Chris", 1, "12/07-2024", true, 777, VictoryType.CULTURAL, 756, 642);
        CivilizationStat civilizationStatsToUpdate = expectedCivilizationStats;

        CivilizationStat actualCivilizationStats = jdbcGameStat.editGameStat(civilizationStatsToUpdate);

        assertNull(actualCivilizationStats);
    }

    @Test
    void deleteGameStatTestForReturnType() {
        boolean expectedResult = true;

        boolean actualResult = jdbcGameStat.deleteGameStat(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteGameStatTestForActualDeletion() {
        CivilizationStat expectedResult = null;

        jdbcGameStat.deleteGameStat(1);
        CivilizationStat actualResult = jdbcGameStat.getGameStat(1);

        assertEquals(expectedResult, actualResult);
    }
}