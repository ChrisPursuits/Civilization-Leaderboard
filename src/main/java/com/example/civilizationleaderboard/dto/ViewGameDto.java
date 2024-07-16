package com.example.civilizationleaderboard.dto;

import com.example.civilizationleaderboard.model.CivilizationStat;

import java.util.List;

public record ViewGameDto(
        int id,
        int leaderboardId,
        String name,
        List<CivilizationStat> civStatList
) {
}
