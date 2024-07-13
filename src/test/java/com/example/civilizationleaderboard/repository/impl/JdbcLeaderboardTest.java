package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.Leaderboard;
import com.example.civilizationleaderboard.model.User;
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

    @Autowired
    private JdbcGameStat jdbcGameStat;

    //CRUD OPERATIONS
    @Test
    void getLeaderboard() {
        List<User> players = new ArrayList<>(List.of(
                new User("john doe"),
                new User("Mikkel"))
        );
        List<GameStat> gameStatList = new ArrayList<>(List.of(
                new GameStat(1, "john doe", 1, "game1", false, 521, VictoryType.LOSE, 111, 121),
                new GameStat(2, "john doe", 1, "game2", true, 777, VictoryType.SCIENTIFIC, 1811, 21),
                new GameStat(3, "Mikkel", 1, "game1", true, 777, VictoryType.CULTURAL, 231, 321),
                new GameStat(4, "Mikkel", 1, "game2", true, 417,VictoryType.DOMINATION, 425, 412))
        );
       Leaderboard expectedLeaderboard = new Leaderboard(1, "leaderboardOne", "descriptionOne", players, gameStatList);

       Leaderboard actualLeaderboard = jdbcLeaderboard.getLeaderboard(1);

       assertEquals(expectedLeaderboard, actualLeaderboard);
    }

    @Test
    void getGameStatsOnLeaderboard() {
        List<GameStat> expectedGameStatList = new ArrayList<>(List.of(
                new GameStat(1, "john doe", 1, "game1", false, 521, VictoryType.LOSE, 111, 121),
                new GameStat(2, "john doe", 1, "game2", true, 777, VictoryType.SCIENTIFIC, 1811, 21),
                new GameStat(3, "Mikkel", 1, "game1", true, 777, VictoryType.CULTURAL, 231, 321),
                new GameStat(4, "Mikkel", 1, "game2", true, 417,VictoryType.DOMINATION, 425, 412))
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
    void editLeaderboard() {
        List<User> players = new ArrayList<>(List.of(
                new User("john doe"),
                new User("Mikkel"))
        );
        List<GameStat> gameStatList = new ArrayList<>(List.of(
                new GameStat(1, "john doe", 1, "game1", false, 521, VictoryType.LOSE, 111, 121),
                new GameStat(2, "john doe", 1, "game2", true, 777, VictoryType.SCIENTIFIC, 1811, 21),
                new GameStat(3, "Mikkel", 1, "game1", true, 777, VictoryType.CULTURAL, 231, 321),
                new GameStat(4, "Mikkel", 1, "game2", true, 417,VictoryType.DOMINATION, 425, 412))
        );
        Leaderboard expectedLeaderboard = new Leaderboard(1, "EDITEDleaderboardOne", "EDITEDdescriptionOne", players, gameStatList);

        Leaderboard actualLeaderboard = jdbcLeaderboard.editLeaderboard(expectedLeaderboard);

        assertEquals(expectedLeaderboard, actualLeaderboard);
    }

    @Test
    void deleteLeaderboardReturnType() {
        boolean expectedResult = true;

        boolean actualResult = jdbcLeaderboard.deleteLeaderboard(1);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void checkDeletedLeaderboardGoneFromDatabase() {
        Leaderboard expectedResult = null;

        jdbcLeaderboard.deleteLeaderboard(1);
        Leaderboard actualResult = jdbcLeaderboard.getLeaderboard(1);

        assertEquals(expectedResult, actualResult);
    }

    //OTHER FEATURES
    @Test
    void addGameStat() {
        GameStat expectedGameStat = new GameStat(5, "Chris", "11/07-2024", true, 521, VictoryType.CULTURAL, 111, 1452);
        GameStat privateGameStat = expectedGameStat;
        int leaderboardId = 1;

        jdbcLeaderboard.addGameStat(privateGameStat, leaderboardId);
        List<GameStat> gameStatList = jdbcLeaderboard.getLeaderboard(leaderboardId).getGameStatList();
        GameStat actualGameStat = gameStatList.get(4);

        assertEquals(expectedGameStat, actualGameStat);
    }

    @Test
    void makePublic() {
    }
}