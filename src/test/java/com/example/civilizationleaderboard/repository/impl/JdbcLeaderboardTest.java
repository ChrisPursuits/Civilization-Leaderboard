package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.Leaderboard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import victoryTypeEnum.VictoryType;

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
    void getGameStatsOnLeaderboard() {
        List<GameStat> expectedGameStatList = new ArrayList<>(List.of(
                new GameStat(1, "john doe", 1, "game1", false, 521, VictoryType.LOSE, 111, 121),
                new GameStat(2, "john doe", 1, "game2", true, 777, VictoryType.SCIENTIFIC, 1811, 21))
        );

        List<GameStat> actualGameStatList = jdbcLeaderboard.getLeaderboard(1).getGameStatList();

        assertEquals(expectedGameStatList, actualGameStatList);
    }


    @Test
    void getAllLeaderboards() {
    }

    @Test
    void createLeaderboard() {
        Leaderboard leaderboard = new Leaderboard("newLeaderBoard", "newDescription");

        jdbcLeaderboard.createLeaderboard(leaderboard);

        Leaderboard expectedLeaderboard = leaderboard;
        Leaderboard actualLeaderboard = jdbcLeaderboard.getLeaderboard(2);

        assertEquals(expectedLeaderboard, actualLeaderboard);
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