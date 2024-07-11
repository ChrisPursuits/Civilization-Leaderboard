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
    }

    @Test
    void deleteGameStat() {
    }

    @Test
    void editGameStat() {
    }
}