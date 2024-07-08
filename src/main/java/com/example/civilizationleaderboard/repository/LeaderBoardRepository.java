package com.example.civilizationleaderboard.repository;

import com.example.civilizationleaderboard.model.Leaderboard;

public interface LeaderBoardRepository {

    Leaderboard getLeaderboard(long userId);
}
