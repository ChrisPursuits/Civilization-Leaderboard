package com.example.civilizationleaderboard.dto;

import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.User;

import java.util.List;

public record ViewLeaderboardDto(
        String name,
        String description,
        List<GameStat> gameStatList,
        List<User> players) {
}
