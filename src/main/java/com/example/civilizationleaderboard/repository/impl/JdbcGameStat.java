package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.CivilizationStat;
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
    public boolean createGameStat(CivilizationStat civilizationStatToCreate) {
        boolean isCreated = false;

        try (Connection connection = dataSource.getConnection()){
            try {
                connection.setAutoCommit(false);

                String createGameStat = """
                        INSERT INTO game_stat (account_username, leaderboard_id, name, haveWon, victory_type, victory_points, science, culture)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                        """;
                PreparedStatement preparedStatement = connection.prepareStatement(createGameStat);
                preparedStatement.setString(1, civilizationStatToCreate.getAccountUsername());
                preparedStatement.setInt(2, civilizationStatToCreate.getGameId());
                preparedStatement.setString(3, civilizationStatToCreate.getName());
                preparedStatement.setBoolean(4, civilizationStatToCreate.isHaveWon());
                preparedStatement.setString(5, civilizationStatToCreate.getVictoryType().toString());
                preparedStatement.setInt(6, civilizationStatToCreate.getVictoryPoints());
                preparedStatement.setInt(7, civilizationStatToCreate.getScience());
                preparedStatement.setInt(8, civilizationStatToCreate.getCulture());
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
    public CivilizationStat getGameStat(int gameStatId) {
        CivilizationStat civilizationStat = null;

        try (Connection connection = dataSource.getConnection()){
            try {
                connection.setAutoCommit(false);

                String getGameStat = "SELECT * FROM game_stat WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(getGameStat);
                preparedStatement.setInt(1, gameStatId);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    civilizationStat = new CivilizationStat(
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

        return civilizationStat;
    }

    @Override
    public CivilizationStat editGameStat(CivilizationStat civilizationStat) {

        try(Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String editGameStats = """
                        UPDATE game_stat
                        SET name = ?, haveWon = ?, victory_type = ?, victory_points = ?, science = ?, culture = ?
                        WHERE id = ?;
                        """;

                PreparedStatement preparedStatement = connection.prepareStatement(editGameStats);
                preparedStatement.setString(1, civilizationStat.getName());
                preparedStatement.setBoolean(2, civilizationStat.isHaveWon());
                preparedStatement.setString(3, civilizationStat.getVictoryType().toString());
                preparedStatement.setInt(4, civilizationStat.getVictoryPoints());
                preparedStatement.setInt(5, civilizationStat.getScience());
                preparedStatement.setInt(6, civilizationStat.getCulture());
                preparedStatement.setInt(7, civilizationStat.getId());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    connection.commit();
                    connection.setAutoCommit(true);
                    return civilizationStat;
                }

                connection.rollback();
                connection.setAutoCommit(true);

            }catch (SQLException sqlException) {
                connection.rollback();
                connection.setAutoCommit(true);
                sqlException.printStackTrace();
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteGameStat(int gameStatId) {
        boolean isDeleted = false;

        try(Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String deleteGameStat = """
                        DELETE FROM game_stat
                        WHERE id = ?;
                        """;

                PreparedStatement preparedStatement = connection.prepareStatement(deleteGameStat);
                preparedStatement.setInt(1, gameStatId);
                int affectedRows = preparedStatement.executeUpdate();
                isDeleted = affectedRows > 0;

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

        return isDeleted;
    }
}
