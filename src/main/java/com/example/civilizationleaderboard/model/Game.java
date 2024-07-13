package com.example.civilizationleaderboard.model;

import java.util.List;

public class Game {

    private int id;
    private String name;
    private List<User> players;
    private List<GameStat> gameStatList;

    public Game(int id, String name, List<User> players, List<GameStat> gameStatList) {
        this.id = id;
        this.name = name;
        this.players = players;
        this.gameStatList = gameStatList;
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

    public List<GameStat> getGameStatList() {
        return gameStatList;
    }

    public void setGameStatList(List<GameStat> gameStatList) {
        this.gameStatList = gameStatList;
    }
}
