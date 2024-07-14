package com.example.civilizationleaderboard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {

    private int id;
    private String name;
    private List<User> players;
    private List<CivilizationStat> civilizationStatList;

    public Game(int id, String name, List<User> players, List<CivilizationStat> civilizationStatList) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.civilizationStatList = civilizationStatList;
    }

    public Game(int id, String name, List<User> players) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.civilizationStatList = new ArrayList<>(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getPlayers() {
        return players;
    }

    public void setPlayers(List<User> players) {
        this.players = players;
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
        return id == game.id && Objects.equals(name, game.name) && Objects.equals(players, game.players) && Objects.equals(civilizationStatList, game.civilizationStatList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, players, civilizationStatList);
    }

    @Override
    public String toString() {
        return "Game{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", players=" + players +
               ", civilizationStatList=" + civilizationStatList +
               '}';
    }
}
