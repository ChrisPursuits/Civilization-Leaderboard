package com.example.civilizationleaderboard.dto;

import com.example.civilizationleaderboard.model.GameStat;

import java.util.List;

public record ViewLeaderboardDto(
        String name,
        String description,
        List<GameStat> gameStatList) {
}
