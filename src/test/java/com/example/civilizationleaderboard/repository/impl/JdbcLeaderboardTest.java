package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.model.Game;
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
    private JdbcCivilizationStat jdbcCivilizationStat;

    //CRUD OPERATIONS
    @Test
    void getLeaderboard() {
        List<User> players = new ArrayList<>(List.of(
                new User("Chris"),
                new User("Engjëll"),
                new User("Markus"),
                new User("Mikkel"))
        );
        List<CivilizationStat> civilizationStatsList = new ArrayList<>(List.of(
                new CivilizationStat(1, "Engjëll", 1, "Portugal", false, 521, VictoryType.LOSE, 111, 121),
                new CivilizationStat(2, "Chris", 1, "China", true, 777, VictoryType.SCIENTIFIC, 1811, 21),
                new CivilizationStat(3, "Mikkel", 1, "Japan", true, 777, VictoryType.CULTURAL, 231, 321),
                new CivilizationStat(4, "Markus", 1, "Spain", true, 417, VictoryType.DOMINATION, 425, 412))
        );
        Game game = new Game(1, 1, "Game: 1", civilizationStatsList);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);
        Leaderboard expectedLeaderboard = new Leaderboard(1, "leaderboardOne", "descriptionOne", players, gameList);

        Leaderboard actualLeaderboard = jdbcLeaderboard.getLeaderboard(1);

        assertEquals(expectedLeaderboard, actualLeaderboard);
    }

    @Test
    void getAllLeaderboards() {
    }

    @Test
    void createLeaderboard() {
        Leaderboard expectedLeaderboard = new Leaderboard("newLeaderBoard", "newDescription");
        expectedLeaderboard.setId(2);

        Leaderboard actualLeaderboard = jdbcLeaderboard.createLeaderboard(expectedLeaderboard);

        assertEquals(expectedLeaderboard, actualLeaderboard);
    }

    @Test
    void editLeaderboard() {
        List<User> players = new ArrayList<>(List.of(
                new User("Chris"),
                new User("Engjëll"),
                new User("Markus"),
                new User("Mikkel"))
        );
        List<CivilizationStat> civilizationStatsList = new ArrayList<>(List.of(
                new CivilizationStat(1, "Engjëll", 1, "Portugal", false, 521, VictoryType.LOSE, 111, 121),
                new CivilizationStat(2, "Chris", 1, "China", true, 777, VictoryType.SCIENTIFIC, 1811, 21),
                new CivilizationStat(3, "Mikkel", 1, "Japan", true, 777, VictoryType.CULTURAL, 231, 321),
                new CivilizationStat(4, "Markus", 1, "Spain", true, 417, VictoryType.DOMINATION, 425, 412))
        );
        Game game = new Game(1, 1, "Game: 1", civilizationStatsList);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);
        Leaderboard expectedLeaderboard = new Leaderboard(1, "EDITEDleaderboardOne", "EDITEDdescriptionOne", players, gameList);

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
//
//    //OTHER FEATURES
//    @Test
//    void addGameStat() {
//        CivilizationStat expectedCivilizationStat = new CivilizationStat(5, "Chris", "11/07-2024", true, 521, VictoryType.CULTURAL, 111, 1452);
//        CivilizationStat privateCivilizationStat = expectedCivilizationStat;
//        int gameId = 1;
//
//        jdbcLeaderboard.addGameStat(privateCivilizationStat, gameId);
//        List<CivilizationStat> civilizationStatList = jdbcLeaderboard.getLeaderboard(gameId).getGameStatList();
//        CivilizationStat actualCivilizationStat = civilizationStatList.get(4);
//
//        assertEquals(expectedCivilizationStat, actualCivilizationStat);
//    }

    @Test
    void makePublic() {
    }
}