package com.example.civilizationleaderboard.repository;

import com.example.civilizationleaderboard.model.CivilizationStat;

public interface GameStatRepository {

    CivilizationStat getGameStat(int gameStatId);
    boolean createGameStat(CivilizationStat civilizationStatToCreate);
    boolean deleteGameStat(int gameStatId);
    CivilizationStat editGameStat(CivilizationStat civilizationStat);
}
