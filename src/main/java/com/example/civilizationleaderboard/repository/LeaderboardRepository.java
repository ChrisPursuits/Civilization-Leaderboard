package com.example.civilizationleaderboard.repository;

import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.Leaderboard;

import java.util.List;

public interface LeaderboardRepository {

    //CRUD-operations
    Leaderboard getLeaderboard(long userId);
    List<Leaderboard> getAllLeaderboards(long userId);
    boolean createLeaderboard(Leaderboard leaderboard);
    boolean deleteLeaderboard(long leaderboardId);
    boolean editLeaderboard(long leaderboardId);

    //Other
    boolean addGameStat(GameStat gameStat, long userId);
}
