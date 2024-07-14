//package com.example.civilizationleaderboard.service;
//
//import com.example.civilizationleaderboard.dto.ViewLeaderboardDto;
//import com.example.civilizationleaderboard.model.CivilizationStat;
//import com.example.civilizationleaderboard.model.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import victoryTypeEnum.VictoryType;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class LeaderboardServiceTest {
//
//    @Autowired
//    private LeaderboardService leaderboardService;
//
//    @Test
//    void createLeaderboard() {
//    }
//
//    @Test
//    void getLeaderboard() {
//        List<User> players = new ArrayList<>(List.of(
//                new User("Mikkel", 2, 1194, 656, 733),
//                new User("john doe", 1, 1298, 1922, 142)
//        ));
//
//        List<CivilizationStat> civilizationStatList = new ArrayList<>(List.of(
//                new CivilizationStat(1, "john doe", 1, "game1", false, 521, VictoryType.LOSE, 111, 121),
//                new CivilizationStat(2, "john doe", 1, "game2", true, 777, VictoryType.SCIENTIFIC, 1811, 21),
//                new CivilizationStat(3, "Mikkel", 1, "game1", true, 777, VictoryType.CULTURAL, 231, 321),
//                new CivilizationStat(4, "Mikkel", 1, "game2", true, 417, VictoryType.DOMINATION, 425, 412)));
//
//        ViewLeaderboardDto expectedLeaderboard = new ViewLeaderboardDto(
//                "leaderboardOne",
//                "descriptionOne",
//                players,
//                civilizationStatList
//        );
//
//        ViewLeaderboardDto actualLeaderboard = leaderboardService.getLeaderboard(1);
//
//        assertEquals(expectedLeaderboard, actualLeaderboard);
//    }
//
//    @Test
//    void addGameStatToLeaderboard() {
//    }
//}