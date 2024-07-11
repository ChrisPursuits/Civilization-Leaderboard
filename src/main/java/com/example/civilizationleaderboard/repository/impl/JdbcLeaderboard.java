package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.Leaderboard;
import com.example.civilizationleaderboard.repository.LeaderboardRepository;
import org.springframework.stereotype.Repository;
import victoryTypeEnum.VictoryType;

import javax.sql.DataSource;
import java.sql.*;
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
    public Leaderboard getLeaderboard(int leaderboardId) {
        Leaderboard leaderboard = null;

        try (Connection connection = dataSource.getConnection()) {
            String getLeaderboard = """
                    SELECT * FROM leaderboard l
                    LEFT JOIN game_stat gs ON gs.leaderboard_id = l.id
                    WHERE l.id = ?;
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(getLeaderboard);
            preparedStatement.setLong(1, leaderboardId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                List<GameStat> gameStatList = new ArrayList<>();

                leaderboard = new Leaderboard(
                        resultSet.getString(2),
                        resultSet.getString(3)
                );

                if (resultSet.getString(8) != null) {
                    GameStat gameStat = new GameStat(
                            resultSet.getInt(5),
                            resultSet.getString(6),
                            resultSet.getInt(7),
                            resultSet.getString(8),
                            resultSet.getBoolean(9),
                            resultSet.getInt(11),
                            VictoryType.valueOf(resultSet.getString(10)),
                            resultSet.getInt(12),
                            resultSet.getInt(13)
                    );
                    gameStatList.add(gameStat);
                    gameStatList = getGameStatListFromLeaderboard(resultSet, gameStatList);

                    leaderboard.setGameStatList(gameStatList);
                    return leaderboard;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return leaderboard;
    }

    private List<GameStat> getGameStatListFromLeaderboard(ResultSet leaderboardRs, List<GameStat> gameStatList) {
        try {
            while (leaderboardRs.next()) {
                GameStat gameStat = new GameStat(
                        leaderboardRs.getInt(5),
                        leaderboardRs.getString(6),
                        leaderboardRs.getInt(7),
                        leaderboardRs.getString(8),
                        leaderboardRs.getBoolean(9),
                        leaderboardRs.getInt(11),
                        VictoryType.valueOf(leaderboardRs.getString(10)),
                        leaderboardRs.getInt(12),
                        leaderboardRs.getInt(13)
                );
                gameStatList.add(gameStat);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return gameStatList;
    }

    @Override
    public List<Leaderboard> getAllLeaderboards(String username) {
        return null;
    }

    @Override
    public boolean createLeaderboard(Leaderboard leaderboard) {
        boolean isCreated = false;

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String createLeaderboard = """
                        INSERT INTO leaderboard (name, description) VALUES(?, ?);
                        """;
                PreparedStatement preparedStatement = connection.prepareStatement(createLeaderboard);
                preparedStatement.setString(1, leaderboard.getName());
                preparedStatement.setString(2, leaderboard.getDescription());
                isCreated = 0 < preparedStatement.executeUpdate();

                connection.commit();
                connection.setAutoCommit(true);

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
    public boolean deleteLeaderboard(int leaderboardId) {
        return false;
    }

    @Override
    public boolean editLeaderboard(int leaderboardId) {
        return false;
    }

    //OTHER
    @Override
    public boolean addGameStat(GameStat gameStat, int leaderboardId) {
        return false;
    }

    @Override
    public boolean makePublic(int leaderboardId) {
        return false;
    }
}
