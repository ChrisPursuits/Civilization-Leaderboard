package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.Leaderboard;
import com.example.civilizationleaderboard.repository.LeaderboardRepository;
import com.example.civilizationleaderboard.service.LeaderboardService;
import org.springframework.stereotype.Repository;
import victoryTypeEnum.VictoryType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JdbcLeaderboard implements LeaderboardRepository {

    private final DataSource dataSource;

    public JdbcLeaderboard(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //CRUD Operations
    @Override
    public Leaderboard getLeaderboard(String leaderboardName) {
        Leaderboard leaderboard = null;

        try (Connection connection = dataSource.getConnection()) {
            String getLeaderboard = """
                    SELECT * FROM leaderboard
                    WHERE name = ?;
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(getLeaderboard);
            preparedStatement.setString(1, leaderboardName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() && resultSet.getString("name").equalsIgnoreCase(leaderboardName)) {
                List<GameStat> gameStatList = new ArrayList<>();

                leaderboard = new Leaderboard(
                        resultSet.getString(3),
                        resultSet.getString(4)
                );

                GameStat gameStat = new GameStat(
                        resultSet.getString("name"),
                        resultSet.getBoolean("haveWon"),
                        resultSet.getInt("victory_points"),
                        VictoryType.valueOf(resultSet.getString("victory_type")),
                        resultSet.getInt("science"),
                        resultSet.getInt("culture")
                );
                gameStatList.add(gameStat);
                gameStatList = getGameStatListFromLeaderboard(resultSet, leaderboardName, gameStatList);

                leaderboard.setGameStatList(gameStatList);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return leaderboard;
    }

    private List<GameStat> getGameStatListFromLeaderboard(ResultSet leaderboardRs, String leaderboardName, List<GameStat> gameStatList) {
        try {
            while (leaderboardRs.next() && leaderboardRs.getString("name").equalsIgnoreCase(leaderboardName)) {
                GameStat gameStat = new GameStat(
                        leaderboardRs.getString("name"),
                        leaderboardRs.getBoolean("haveWon"),
                        leaderboardRs.getInt("victory_points"),
                        VictoryType.valueOf(leaderboardRs.getString("victory_type")),
                        leaderboardRs.getInt("science"),
                        leaderboardRs.getInt("culture")
                );
                gameStatList.add(gameStat);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return gameStatList;
    }

    @Override
    public List<Leaderboard> getAllLeaderboards(long userId) {
        return null;
    }

    @Override
    public boolean createLeaderboard(Leaderboard leaderboard) {
        boolean isCreated = false;

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String createLeaderboard = """
                        INSERT INTO leaderboard (name, description) VALUES(?,?);
                        """;
                PreparedStatement preparedStatement = connection.prepareStatement(createLeaderboard);
                preparedStatement.setString(1, leaderboard.getName());
                preparedStatement.setString(2, leaderboard.getDescription());
                isCreated = 0 < preparedStatement.executeUpdate();

                connection.setAutoCommit(true);
                connection.commit();

            } catch (SQLException sqlException) {
                connection.rollback();
                connection.setAutoCommit(true);
                sqlException.printStackTrace();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return isCreated;
    }

    @Override
    public boolean deleteLeaderboard(long leaderboardId) {
        return false;
    }

    @Override
    public boolean editLeaderboard(long leaderboardId) {
        return false;
    }

    //OTHER
    @Override
    public boolean addGameStat(GameStat gameStat, long userId) {
        return false;
    }

    @Override
    public boolean makePublic(long leaderboardId) {
        return false;
    }
}
