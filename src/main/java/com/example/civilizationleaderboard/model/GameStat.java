package com.example.civilizationleaderboard.model;

import victoryTypeEnum.VictoryType;

public class GameStat {

    private String name;
    private boolean haveWon;
    private int victoryPoints;
    private VictoryType victoryType;
    private int science;
    private int culture;

    public GameStat(String name, boolean haveWon, int victoryPoints, VictoryType victoryType, int science, int culture) {
        this.name = name;
        this.haveWon = haveWon;
        this.victoryPoints = victoryPoints;
        this.victoryType = victoryType;
        this.science = science;
        this.culture = culture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHaveWon() {
        return haveWon;
    }

    public void setHaveWon(boolean haveWon) {
        this.haveWon = haveWon;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    public VictoryType getVictoryType() {
        return victoryType;
    }

    public void setVictoryType(VictoryType victoryType) {
        this.victoryType = victoryType;
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public int getCulture() {
        return culture;
    }

    public void setCulture(int culture) {
        this.culture = culture;
    }
}
