package com.example.civilizationleaderboard.repository;

import com.example.civilizationleaderboard.model.GameStat;

public interface GameStatRepository {

    GameStat getGameStat(int gameStatId);
    boolean createGameStat(GameStat gameStatToCreate);
    boolean deleteGameStat(int gameStatId);
    GameStat editGameStat(GameStat gameStat);
}
