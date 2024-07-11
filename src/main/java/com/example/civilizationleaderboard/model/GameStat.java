package com.example.civilizationleaderboard.model;

import victoryTypeEnum.VictoryType;

import java.util.Objects;

public class GameStat {

    private int id;
    private String accountUsername;
    private int leaderboardId;
    private String name;
    private boolean haveWon;
    private int victoryPoints;
    private VictoryType victoryType;
    private int science;
    private int culture;

    //Constructor used in impl.
    public GameStat(int id, String accountUsername, int leaderboardId, String name, boolean haveWon, int victoryPoints, VictoryType victoryType, int science, int culture) {
        this.id = id;
        this.accountUsername = accountUsername;
        this.leaderboardId = leaderboardId;
        this.name = name;
        this.haveWon = haveWon;
        this.victoryPoints = victoryPoints;
        this.victoryType = victoryType;
        this.science = science;
        this.culture = culture;
    }

    //Constructor used in DtoMapper
    public GameStat(String name, boolean haveWon, int victoryPoints, VictoryType victoryType, int science, int culture) {
        this.name = name;
        this.haveWon = haveWon;
        this.victoryPoints = victoryPoints;
        this.victoryType = victoryType;
        this.science = science;
        this.culture = culture;
    }

    //Constructor used in integration test
    public GameStat(String name, int victoryPoints, int science, int culture) {
        this.name = name;
        this.haveWon = false;
        this.victoryPoints = victoryPoints;
        this.victoryType = VictoryType.LOSE;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameStat gameStat)) return false;
        return haveWon == gameStat.haveWon && victoryPoints == gameStat.victoryPoints && science == gameStat.science && culture == gameStat.culture && Objects.equals(name, gameStat.name) && victoryType == gameStat.victoryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, haveWon, victoryPoints, victoryType, science, culture);
    }
}
