package com.example.civilizationleaderboard.repository;

import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.Leaderboard;

import java.util.List;

public interface LeaderboardRepository {

    //CRUD-operations
    Leaderboard getLeaderboard(int leaderboardId);
    List<Leaderboard> getAllLeaderboards(String username);
    boolean createLeaderboard(Leaderboard leaderboard);
    boolean deleteLeaderboard(int leaderboardId);
    boolean editLeaderboard(int leaderboardId);

    //Other
    boolean addGameStat(GameStat gameStat, int leaderboardId);
    boolean makePublic(int leaderboardId);
}
