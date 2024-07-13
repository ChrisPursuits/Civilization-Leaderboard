package com.example.civilizationleaderboard.model;

import java.util.Objects;

public class User {

    private String username;
    private String password;
    private int totalWins;
    private int totalVictoryPoints;
    private int totalScience;
    private int totalCulture;

    public User(String username, int totalWins, int totalVictoryPoints, int totalScience, int totalCulture) {
        this.username = username;
        this.totalWins = totalWins;
        this.totalVictoryPoints = totalVictoryPoints;
        this.totalScience = totalScience;
        this.totalCulture = totalCulture;
    }

    public User(String username) {
        this.username = username;
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

    public int getTotalWins() {
        return totalWins;
    }

    public void setTotalWins(int totalWins) {
        this.totalWins = totalWins;
    }

    public int getTotalVictoryPoints() {
        return totalVictoryPoints;
    }

    public void setTotalVictoryPoints(int totalVictoryPoints) {
        this.totalVictoryPoints = totalVictoryPoints;
    }

    public int getTotalScience() {
        return totalScience;
    }

    public void setTotalScience(int totalScience) {
        this.totalScience = totalScience;
    }

    public int getTotalCulture() {
        return totalCulture;
    }

    public void setTotalCulture(int totalCulture) {
        this.totalCulture = totalCulture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return totalWins == user.totalWins && totalVictoryPoints == user.totalVictoryPoints && totalScience == user.totalScience && totalCulture == user.totalCulture && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, totalWins, totalVictoryPoints, totalScience, totalCulture);
    }
}
