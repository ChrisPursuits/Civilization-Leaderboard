package com.example.civilizationleaderboard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Leaderboard {

    private int leaderboardId;
    private String name;
    private String description;
    private boolean isPublic;
    private List<User> players;
    private List<GameStat> gameStatList;

    public Leaderboard(String name, String description, List<User> players, List<GameStat> gameStatList) {
        this.name = name;
        this.description = description;
        this.isPublic = true;
        this.players = players;
        this.gameStatList = gameStatList;
    }

    public Leaderboard(String name, String description) {
        this.name = name;
        this.description = description;
        this.isPublic = true;
        this.players = new ArrayList<>();
        this.gameStatList = new ArrayList<>();
    }

    public int getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(int leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
    }

    public List<GameStat> getGameStatList() {
        return gameStatList;
    }

    public void setGameStatList(List<GameStat> gameStatList) {
        this.gameStatList = gameStatList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Leaderboard that)) return false;
        return leaderboardId == that.leaderboardId && isPublic == that.isPublic && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(players, that.players) && Objects.equals(gameStatList, that.gameStatList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leaderboardId, name, description, isPublic, players, gameStatList);
    }
}