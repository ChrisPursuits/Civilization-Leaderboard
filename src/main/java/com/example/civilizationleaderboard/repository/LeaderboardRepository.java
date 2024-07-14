package com.example.civilizationleaderboard.repository;

import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.model.Leaderboard;

import java.util.List;

public interface LeaderboardRepository {

    //CRUD-operations
    Leaderboard getLeaderboard(int leaderboardId);
    List<Leaderboard> getAllLeaderboards(String username);
    Leaderboard createLeaderboard(Leaderboard leaderboard);
    boolean deleteLeaderboard(int leaderboardId);
    Leaderboard editLeaderboard(Leaderboard leaderboardToEdit);

    //Other
    boolean addGameStat(CivilizationStat civilizationStat, int leaderboardId);
    boolean makePublic(int leaderboardId);
}
