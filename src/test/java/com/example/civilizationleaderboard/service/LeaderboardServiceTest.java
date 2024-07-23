package com.example.civilizationleaderboard.service;

import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.model.Game;
import com.example.civilizationleaderboard.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import victoryTypeEnum.VictoryType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeaderboardServiceTest {

    @Autowired
    private LeaderboardService leaderboardService;

    @Test
    void createLeaderboard() {
    }

    @Test
    void getLeaderboardSortedByVictoryPoints() {
    }

    @Test
    void getLeaderboardSortedByPlacements() {
        List<User> expectedPlayerList = new ArrayList<>(List.of(
                new User("Chris", 1, 1, 0, 0, 0),
                new User("Mikkel", 1, 0, 1, 0, 0),
                new User("Markus", 1, 0, 0,1,0),
                new User("Engjëll", 1, 0, 0, 0, 1))
        );

        List<User> actualPlayerList = leaderboardService.getLeaderboardSortedByPlacements(1).players();

        assertEquals(expectedPlayerList, actualPlayerList);
    }

    @Test
    void editLeaderboard() {
    }

    @Test
    void deleteLeaderboard() {
    }

    @Test
    void addGameStatToLeaderboard() {
    }
}