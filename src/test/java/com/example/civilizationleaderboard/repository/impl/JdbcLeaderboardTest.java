package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.GameStat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:init_db.sql")
class JdbcLeaderboardTest {

    @Autowired
    private JdbcLeaderboard jdbcLeaderboard;

    @Test
    void getLeaderboard() {
        String expectedName = "leaderboardOne";
        String expectedDescription = "descriptionOne";

        String actualName = jdbcLeaderboard.getLeaderboard(1).getName();
        String actualDescription = jdbcLeaderboard.getLeaderboard(1).getDescription();

        assertEquals(expectedName, actualName);
        assertEquals(expectedDescription, actualDescription);
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