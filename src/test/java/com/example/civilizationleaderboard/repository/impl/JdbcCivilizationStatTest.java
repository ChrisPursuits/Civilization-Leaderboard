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
    private JdbcCivilizationStat jdbcCivilizationStat;

    @Test
    void getCivilizationStatsOnId() {
        CivilizationStat expectedCivilizationStat = new CivilizationStat(2, "Chris", 1, "China", true, false, false, false, VictoryType.SCIENTIFIC, 777, 1, 1811, 21, 2, 3, 4);
        CivilizationStat actualCivilizationStat = jdbcCivilizationStat.getCivilizationStat(2);

        assertEquals(expectedCivilizationStat, actualCivilizationStat);
    }

    @Test
    void createGameStatAutoPlacement() {
        CivilizationStat expectedCivilizationStat = new CivilizationStat(5, "Chris", 1, "Maui", true, false, false, false, VictoryType.DOMINATION, 456, 1, 756, 642, 2, 3, 4);
        CivilizationStat civilizationStat = expectedCivilizationStat;

        jdbcCivilizationStat.createCivStatAutoPlacement(civilizationStat);
        CivilizationStat actualCivilizationStat = jdbcCivilizationStat.getCivilizationStat(5);

        assertEquals(expectedCivilizationStat, actualCivilizationStat);
    }

    @Test
    void createGameStatManuelPlacement() {
        CivilizationStat expectedCivilizationStat = new CivilizationStat(5, "Chris", 1, "Maui", false, true, false, false, VictoryType.DOMINATION, 456, 1, 756, 642, 2, 3, 4);
        CivilizationStat civilizationStat = expectedCivilizationStat;

        jdbcCivilizationStat.createCivStatManuelPlacement(civilizationStat);
        CivilizationStat actualCivilizationStat = jdbcCivilizationStat.getCivilizationStat(5);

        assertEquals(expectedCivilizationStat, actualCivilizationStat);
    }

    @Test
    void editGameStat() {
        CivilizationStat expectedCivilizationStats = new CivilizationStat(4, "EDITED Mikkel", 1, "Spain", false, true, false, false, VictoryType.DOMINATION, 417, 1, 425, 412, 2, 3, 4);
        CivilizationStat civilizationStatsToUpdate = expectedCivilizationStats;

        CivilizationStat actualCivilizationStats = jdbcCivilizationStat.editCivStat(civilizationStatsToUpdate);

        assertEquals(expectedCivilizationStats, actualCivilizationStats);
    }

    @Test
    void attemptEditGameStatsThatDoesNotExist() {
        CivilizationStat expectedCivilizationStats = new CivilizationStat(0, "Chris", 1, "12/07-2024", true, false, false, false, VictoryType.CULTURAL, 777, 1, 756, 642, 2, 3, 4);
        CivilizationStat civilizationStatsToUpdate = expectedCivilizationStats;

        CivilizationStat actualCivilizationStats = jdbcCivilizationStat.editCivStat(civilizationStatsToUpdate);

        assertNull(actualCivilizationStats);
    }

    @Test
    void deleteGameStatTestForReturnType() {
        boolean expectedResult = true;

        boolean actualResult = jdbcCivilizationStat.deleteCivStat(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void deleteGameStatTestForActualDeletion() {
        CivilizationStat expectedResult = null;

        jdbcCivilizationStat.deleteCivStat(1);
        CivilizationStat actualResult = jdbcCivilizationStat.getCivilizationStat(1);

        assertEquals(expectedResult, actualResult);
    }
}