package com.example.civilizationleaderboard.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Leaderboard {

    private int id;
    private String name;
    private String description;
    private boolean isPublic;
    private List<User> players;
    private List<Game> gameList;

    public Leaderboard(int id, String name, String description, List<User> players, List<Game> gameList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublic = true;
        this.players = players;
        this.gameList = gameList;
    }

    public Leaderboard(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublic = true;
        this.players = new ArrayList<>();
        this.gameList = new ArrayList<>();
    }

    //USED WHEN CREATING NEW LEADERBOARD IN MAPPING CLASS
    public Leaderboard(String name, String description) {
        this.name = name;
        this.description = description;
        this.isPublic = true;
        this.players = new ArrayList<>();
        this.gameList = new ArrayList<>();
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

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(List<Game> gameList) {
        this.gameList = gameList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Leaderboard that)) return false;
        return id == that.id && isPublic == that.isPublic && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(players, that.players) && Objects.equals(gameList, that.gameList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, isPublic, players, gameList);
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", isPublic=" + isPublic +
               ", players=" + players +
               ", gameList=" + gameList +
               '}';
    }
}