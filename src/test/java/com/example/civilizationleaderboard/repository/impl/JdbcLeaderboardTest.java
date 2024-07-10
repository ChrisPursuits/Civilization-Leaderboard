package com.example.civilizationleaderboard.repository.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_db.sql")
class JdbcLeaderboardTest {

    @Autowired
    private JdbcLeaderboard jdbcLeaderboard;

    @Test
    void getLeaderboard() {
        String expectedName = "leaderboardOne";
        String actualName = jdbcLeaderboard.getLeaderboard(1).getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    void getAllLeaderboards() {
    }

    @Test
    void createLeaderboard() {
    }

    @Test
    void deleteLeaderboard() {
    }

    @Test
    void editLeaderboard() {
    }

    @Test
    void addGameStat() {
    }

    @Test
    void makePublic() {
    }
}