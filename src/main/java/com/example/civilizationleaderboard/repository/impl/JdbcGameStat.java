package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.repository.GameStatRepository;
import org.springframework.stereotype.Repository;
import victoryTypeEnum.VictoryType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JdbcGameStat implements GameStatRepository {

    private DataSource dataSource;

    public JdbcGameStat(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public GameStat getGameStat(int gameStatId) {
        GameStat gameStat = null;

        try (Connection connection = dataSource.getConnection()){
            try {
                connection.setAutoCommit(false);

                String getGameStat = "SELECT * FROM game_stat WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(getGameStat);
                preparedStatement.setInt(1, gameStatId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    gameStat = new GameStat(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getInt(3),
                            resultSet.getString(4),
                            resultSet.getBoolean(5),
                            resultSet.getInt(7),
                            VictoryType.valueOf(resultSet.getString(6)),
                            resultSet.getInt(8),
                            resultSet.getInt(9)
                    );
                }

                connection.commit();
                connection.setAutoCommit(true);

            } catch (SQLException sqlException) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return gameStat;
    }

    @Override
    public boolean createGameStat(GameStat gameStatToCreate) {
        boolean isCreated = false;

        try (Connection connection = dataSource.getConnection()){
            try {
                connection.setAutoCommit(false);

                String createGameStat = """
                        INSERT INTO game_stat (account_username, leaderboard_id, name, haveWon, victory_type, victory_points, science, culture)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                        """;
                PreparedStatement preparedStatement = connection.prepareStatement(createGameStat);
                preparedStatement.setString(1, gameStatToCreate.getAccountUsername());
                preparedStatement.setInt(2, gameStatToCreate.getLeaderboardId());
                preparedStatement.setString(3, gameStatToCreate.getName());
                preparedStatement.setBoolean(4, gameStatToCreate.isHaveWon());
                preparedStatement.setString(5, gameStatToCreate.getVictoryType().toString());
                preparedStatement.setInt(6, gameStatToCreate.getVictoryPoints());
                preparedStatement.setInt(7, gameStatToCreate.getScience());
                preparedStatement.setInt(8, gameStatToCreate.getCulture());
                int affectedRows = preparedStatement.executeUpdate();
                isCreated = affectedRows > 0;

                connection.commit();
                connection.setAutoCommit(true);

            }catch (SQLException sqlException) {
                connection.rollback();
                connection.setAutoCommit(true);
                sqlException.printStackTrace();
            }

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return isCreated;
    }

    @Override
    public boolean deleteGameStat(int gameStatId) {
        return false;
    }

    @Override
    public boolean editGameStat(int gameStatId, GameStat gameStat) {
        return false;
    }
}
