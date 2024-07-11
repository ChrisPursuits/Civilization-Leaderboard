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

    //Constructor used in impl, DtoMapper and test.
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

    //TODO version PRIVATE LOG
    //Used for creating GameStat that is not part of any leaderboard. Meant to be used in future version where you can have a private log of all your games.
    //Precisely used in mapping of CreatePrivateGameStatDto
    public GameStat(String accountUsername, String name, boolean haveWon, int victoryPoints, VictoryType victoryType, int science, int culture) {
        this.accountUsername = accountUsername;
        this.name = name;
        this.haveWon = haveWon;
        this.victoryPoints = victoryPoints;
        this.victoryType = victoryType;
        this.science = science;
        this.culture = culture;
    }

    //TODO version PRIVATE LOG
    //Used for creating GameStat that is not part of any leaderboard. Meant to be used in future version where you can have a private log of all your games.
    //Precisely used in mapping of PrivateGameStatDto (in get method?)
    public GameStat(int id, String accountUsername, String name, boolean haveWon, int victoryPoints, VictoryType victoryType, int science, int culture) {
        this.id = id;
        this.accountUsername = accountUsername;
        this.name = name;
        this.haveWon = haveWon;
        this.victoryPoints = victoryPoints;
        this.victoryType = victoryType;
        this.science = science;
        this.culture = culture;
    }

    //Constructor used only in DtoMapper
    public GameStat(String accountUsername, int leaderboardId, String name, boolean haveWon, int victoryPoints, VictoryType victoryType, int science, int culture) {
        this.accountUsername = accountUsername;
        this.leaderboardId = leaderboardId;
        this.name = name;
        this.haveWon = haveWon;
        this.victoryPoints = victoryPoints;
        this.victoryType = victoryType;
        this.science = science;
        this.culture = culture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountUsername() {
        return accountUsername;
    }

    public void setAccountUsername(String accountUsername) {
        this.accountUsername = accountUsername;
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
