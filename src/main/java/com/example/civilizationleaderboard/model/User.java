package com.example.civilizationleaderboard.model;

import java.util.Objects;

public class User {

    private String username;
    private String password;
    private int gamesPlayed;
    private int firstPlaceCount;
    private int secondPlaceCount;
    private int thirdPlaceCount;
    private int otherPlacementCount;

    public User(String username) {
        this.username = username;
    }

    //USED IN LeaderboardServiceTest
    public User(String username, int gamesPlayed, int firstPlaceCount, int secondPlaceCount, int thirdPlaceCount, int otherPlacementCount) {
        this.username = username;
        this.gamesPlayed = gamesPlayed;
        this.firstPlaceCount = firstPlaceCount;
        this.secondPlaceCount = secondPlaceCount;
        this.thirdPlaceCount = thirdPlaceCount;
        this.otherPlacementCount = otherPlacementCount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getFirstPlaceCount() {
        return firstPlaceCount;
    }

    public void setFirstPlaceCount(int firstPlaceCount) {
        this.firstPlaceCount = firstPlaceCount;
    }

    public int getSecondPlaceCount() {
        return secondPlaceCount;
    }

    public void setSecondPlaceCount(int secondPlaceCount) {
        this.secondPlaceCount = secondPlaceCount;
    }

    public int getThirdPlaceCount() {
        return thirdPlaceCount;
    }

    public void setThirdPlaceCount(int thirdPlaceCount) {
        this.thirdPlaceCount = thirdPlaceCount;
    }

    public int getOtherPlacementCount() {
        return otherPlacementCount;
    }

    public void setOtherPlacementCount(int otherPlacementCount) {
        this.otherPlacementCount = otherPlacementCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return gamesPlayed == user.gamesPlayed && firstPlaceCount == user.firstPlaceCount && secondPlaceCount == user.secondPlaceCount && thirdPlaceCount == user.thirdPlaceCount && otherPlacementCount == user.otherPlacementCount && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, gamesPlayed, firstPlaceCount, secondPlaceCount, thirdPlaceCount, otherPlacementCount);
    }

    @Override
    public String toString() {
        return "User{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", gamesPlayed=" + gamesPlayed +
               ", firstPlaceCount=" + firstPlaceCount +
               ", secondPlaceCount=" + secondPlaceCount +
               ", thirdPlaceCount=" + thirdPlaceCount +
               ", otherPlacementCount=" + otherPlacementCount +
               '}';
    }
}
