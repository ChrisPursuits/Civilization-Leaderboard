package com.example.civilizationleaderboard.repository;

import com.example.civilizationleaderboard.model.CivilizationStat;

public interface CivilizationStatRepository {

    CivilizationStat getCivilizationStat(int civStatId);
    CivilizationStat createCivStat(CivilizationStat civilizationStatToCreate);
    boolean deleteCivStat(int gameStatId);
    CivilizationStat editCivStat(CivilizationStat civilizationStat);
}
