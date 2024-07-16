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
        CivilizationStat expectedCivilizationStat = new CivilizationStat(2, "Chris", 1, "China", true, 777, VictoryType.SCIENTIFIC, 1811, 21);
        CivilizationStat actualCivilizationStat = jdbcCivilizationStat.getCivilizationStat(2);

        assertEquals(expectedCivilizationStat, actualCivilizationStat);
    }

    @Test
    void createGameStat() {
        CivilizationStat expectedCivilizationStat = new CivilizationStat(5, "Chris", 1, "Maui", true, 456, VictoryType.DOMINATION, 756, 642);
        CivilizationStat civilizationStat = expectedCivilizationStat;

        jdbcCivilizationStat.createCivStat(civilizationStat);
        CivilizationStat actualCivilizationStat = jdbcCivilizationStat.getCivilizationStat(5);

        assertEquals(expectedCivilizationStat, actualCivilizationStat);
    }

    @Test
    void editGameStat() {
        CivilizationStat expectedCivilizationStats = new CivilizationStat(4, "EDITED Mikkel", 1, "Spain", true, 417, VictoryType.DOMINATION, 425, 412);
        CivilizationStat civilizationStatsToUpdate = expectedCivilizationStats;

        CivilizationStat actualCivilizationStats = jdbcCivilizationStat.editCivStat(civilizationStatsToUpdate);

        assertEquals(expectedCivilizationStats, actualCivilizationStats);
    }

    @Test
    void attemptEditGameStatsThatDoesNotExist() {
        CivilizationStat expectedCivilizationStats = new CivilizationStat(0, "Chris", 1, "12/07-2024", true, 777, VictoryType.CULTURAL, 756, 642);
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