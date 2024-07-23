package com.example.civilizationleaderboard.model;

import victoryTypeEnum.VictoryType;

import java.util.Objects;

public class CivilizationStat {

    private int id;
    private String accountUsername;
    private int gameId;
    private String name;
    private boolean isFirstPlace;
    private boolean isSecondPlace;
    private boolean isThirdPlace;
    private boolean isOtherPlace;
    private VictoryType victoryType;
    private int victoryPoints;
    private int militaryStrength;
    private int science;
    private int culture;
    private int gold;
    private int religiousPoints;
    private int diplomaticFavors;

    //Constructor used in impl, DtoMapper and test.
    public CivilizationStat(
            int id,
            String accountUsername,
            int gameId,
            String name,
            boolean isFirstPlace,
            boolean isSecondPlace,
            boolean isThirdPlace,
            boolean isOtherPlace,
            VictoryType victoryType,
            int victoryPoints,
            int militaryStrength,
            int science,
            int culture,
            int gold,
            int religiousPoints,
            int diplomaticFavors) {

        this.id = id;
        this.accountUsername = accountUsername;
        this.gameId = gameId;
        this.name = name;
        this.isFirstPlace = isFirstPlace;
        this.isSecondPlace = isSecondPlace;
        this.isThirdPlace = isThirdPlace;
        this.isOtherPlace = isOtherPlace;
        this.victoryType = victoryType;
        this.victoryPoints = victoryPoints;
        this.militaryStrength = militaryStrength;
        this.science = science;
        this.culture = culture;
        this.gold = gold;
        this.religiousPoints = religiousPoints;
        this.diplomaticFavors = diplomaticFavors;
    }

    //TODO version PRIVATE LOG
    //Used for creating GameStat that is not part of any leaderboard. Meant to be used in future version where you can have a private log of all your games.
    //Precisely used in mapping of CreatePrivateGameStatDto
    public CivilizationStat(String accountUsername, String name, boolean isFirstPlace, VictoryType victoryType, int victoryPoints, int science, int culture) {
        this.accountUsername = accountUsername;
        this.name = name;
        this.isFirstPlace = isFirstPlace;
        this.victoryPoints = victoryPoints;
        this.victoryType = victoryType;
        this.science = science;
        this.culture = culture;
    }

    //TODO version PRIVATE LOG
    //Used for creating GameStat that is not part of any leaderboard. Meant to be used in future version where you can have a private log of all your games.
    //Precisely used in mapping of PrivateGameStatDto (in get method?)
    public CivilizationStat(int id, String accountUsername, String name, boolean isFirstPlace, VictoryType victoryType, int victoryPoints, int science, int culture) {
        this.id = id;
        this.accountUsername = accountUsername;
        this.name = name;
        this.isFirstPlace = isFirstPlace;
        this.victoryPoints = victoryPoints;
        this.victoryType = victoryType;
        this.science = science;
        this.culture = culture;
    }

    //Constructor used only in DtoMapper
    public CivilizationStat(String accountUsername, int gameId, String name, boolean isFirstPlace, boolean isSecondPlace, boolean isThirdPlace, boolean isOtherPlace, VictoryType victoryType, int victoryPoints, int militaryStrength, int science, int culture, int gold, int religiousPoints, int diplomaticFavors) {
        this.accountUsername = accountUsername;
        this.gameId = gameId;
        this.name = name;
        this.isFirstPlace = isFirstPlace;
        this.isSecondPlace = isSecondPlace;
        this.isThirdPlace = isThirdPlace;
        this.isOtherPlace = isOtherPlace;
        this.victoryType = victoryType;
        this.victoryPoints = victoryPoints;
        this.militaryStrength = militaryStrength;
        this.science = science;
        this.culture = culture;
        this.gold = gold;
        this.religiousPoints = religiousPoints;
        this.diplomaticFavors = diplomaticFavors;
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFirstPlace() {
        return isFirstPlace;
    }

    public void setFirstPlace(boolean firstPlace) {
        this.isFirstPlace = firstPlace;
    }

    public boolean isSecondPlace() {
        return isSecondPlace;
    }

    public void setSecondPlace(boolean secondPlace) {
        isSecondPlace = secondPlace;
    }

    public boolean isThirdPlace() {
        return isThirdPlace;
    }

    public void setThirdPlace(boolean thirdPlace) {
        isThirdPlace = thirdPlace;
    }

    public boolean isOtherPlace() {
        return isOtherPlace;
    }

    public void setOtherPlace(boolean otherPlace) {
        isOtherPlace = otherPlace;
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

    public int getMilitaryStrength() {
        return militaryStrength;
    }

    public void setMilitaryStrength(int militaryStrength) {
        this.militaryStrength = militaryStrength;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getReligiousPoints() {
        return religiousPoints;
    }

    public void setReligiousPoints(int religiousPoints) {
        this.religiousPoints = religiousPoints;
    }

    public int getDiplomaticFavors() {
        return diplomaticFavors;
    }

    public void setDiplomaticFavors(int diplomaticFavors) {
        this.diplomaticFavors = diplomaticFavors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CivilizationStat that)) return false;
        return id == that.id && gameId == that.gameId && isFirstPlace == that.isFirstPlace && isSecondPlace == that.isSecondPlace && isThirdPlace == that.isThirdPlace && isOtherPlace == that.isOtherPlace && victoryPoints == that.victoryPoints && militaryStrength == that.militaryStrength && science == that.science && culture == that.culture && gold == that.gold && religiousPoints == that.religiousPoints && diplomaticFavors == that.diplomaticFavors && Objects.equals(accountUsername, that.accountUsername) && Objects.equals(name, that.name) && victoryType == that.victoryType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountUsername, gameId, name, isFirstPlace, isSecondPlace, isThirdPlace, isOtherPlace, victoryType, victoryPoints, militaryStrength, science, culture, gold, religiousPoints, diplomaticFavors);
    }

    @Override
    public String toString() {
        return "CivilizationStat{" +
               "id=" + id +
               ", accountUsername='" + accountUsername + '\'' +
               ", gameId=" + gameId +
               ", name='" + name + '\'' +
               ", isFirstPlace=" + isFirstPlace +
               ", isSecondPlace=" + isSecondPlace +
               ", isThirdPlace=" + isThirdPlace +
               ", isOtherPlace=" + isOtherPlace +
               ", victoryType=" + victoryType +
               ", victoryPoints=" + victoryPoints +
               ", militaryStrength=" + militaryStrength +
               ", science=" + science +
               ", culture=" + culture +
               ", gold=" + gold +
               ", religiousPoints=" + religiousPoints +
               ", diplomaticFavors=" + diplomaticFavors +
               '}';
    }
}
