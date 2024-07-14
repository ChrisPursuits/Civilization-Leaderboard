package com.example.civilizationleaderboard.dto;

import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.model.Game;
import com.example.civilizationleaderboard.model.User;

import java.util.List;
import java.util.Objects;

public record ViewLeaderboardDto(
        String name,
        String description,
        List<User> players,
        List<Game> gameList) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViewLeaderboardDto that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(players, that.players) && Objects.equals(gameList, that.gameList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, players, gameList);
    }
}
