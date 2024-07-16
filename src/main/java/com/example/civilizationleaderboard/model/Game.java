package com.example.civilizationleaderboard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {

    private int id;
    private int leaderboardId;
    private String name;
    private List<CivilizationStat> civilizationStatList;

    public Game(int id, int leaderboardId, String name, List<CivilizationStat> civilizationStatList) {
        this.id = id;
        this.leaderboardId = leaderboardId;
        this.name = name;
        this.civilizationStatList = civilizationStatList;
    }

    public Game(int id, int leaderboardId, String name) {
        this.id = id;
        this.leaderboardId = leaderboardId;
        this.name = name;
        this.civilizationStatList = new ArrayList<>(0);
    }

    public Game(String name, int leaderboardId) {
        this.name = name;
        this.leaderboardId = leaderboardId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<CivilizationStat> getCivilizationStatList() {
        return civilizationStatList;
    }

    public void setCivilizationStatList(List<CivilizationStat> civilizationStatList) {
        this.civilizationStatList = civilizationStatList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game game)) return false;
        return id == game.id && leaderboardId == game.leaderboardId && Objects.equals(name, game.name) && Objects.equals(civilizationStatList, game.civilizationStatList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, leaderboardId, name, civilizationStatList);
    }

    @Override
    public String toString() {
        return "Game{" +
               "id=" + id +
               ", leaderboardId=" + leaderboardId +
               ", name='" + name + '\'' +
               ", civilizationStatList=" + civilizationStatList +
               '}';
    }
}
