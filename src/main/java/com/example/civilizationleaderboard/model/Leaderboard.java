package com.example.civilizationleaderboard.model;

import java.util.List;

public class Leaderboard {

    private String name;
    private String description;
    private boolean isPublic;
    private List<GameStat> gameStatList;

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

    public List<GameStat> getGameStatList() {
        return gameStatList;
    }

    public void setGameStatList(List<GameStat> gameStatList) {
        this.gameStatList = gameStatList;
    }
}
