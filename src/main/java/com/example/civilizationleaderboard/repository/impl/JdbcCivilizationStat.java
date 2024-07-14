package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.repository.CivilizationStatRepository;
import org.springframework.stereotype.Repository;
import victoryTypeEnum.VictoryType;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class JdbcCivilizationStat implements CivilizationStatRepository {

    private DataSource dataSource;

    public JdbcCivilizationStat(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CivilizationStat createCivStat(CivilizationStat civilizationStatToCreate) {
        CivilizationStat createdCivStat = null;

        try (Connection connection = dataSource.getConnection()){
            try {
                connection.setAutoCommit(false);

                String createCivStat = """
                        INSERT INTO civilization_stat (account_username, game_id, name, haveWon, victory_type, victory_points, science, culture)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?);
                        """;
                PreparedStatement preparedStatement = connection.prepareStatement(createCivStat, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, civilizationStatToCreate.getAccountUsername());
                preparedStatement.setInt(2, civilizationStatToCreate.getGameId());
                preparedStatement.setString(3, civilizationStatToCreate.getName());
                preparedStatement.setBoolean(4, civilizationStatToCreate.isHaveWon());
                preparedStatement.setString(5, civilizationStatToCreate.getVictoryType().toString());
                preparedStatement.setInt(6, civilizationStatToCreate.getVictoryPoints());
                preparedStatement.setInt(7, civilizationStatToCreate.getScience());
                preparedStatement.setInt(8, civilizationStatToCreate.getCulture());
                preparedStatement.executeUpdate();

                ResultSet genereatedKeys = preparedStatement.getGeneratedKeys();
                if (genereatedKeys.next()) {
                    int civStatId = genereatedKeys.getInt(1);

                    createdCivStat = getCivilizationStat(civStatId);
                }

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

        return createdCivStat;
    }

    @Override
    public CivilizationStat getCivilizationStat(int civStatId) {
        CivilizationStat civilizationStat = null;

        try (Connection connection = dataSource.getConnection()){
            try {
                connection.setAutoCommit(false);

                String getGameStat = "SELECT * FROM civilization_stat WHERE id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(getGameStat);
                preparedStatement.setInt(1, civStatId);
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
    public CivilizationStat editCivStat(CivilizationStat civilizationStat) {

        try(Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String editGameStats = """
                        UPDATE civilization_stat
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
    public boolean deleteCivStat(int gameStatId) {
        boolean isDeleted = false;

        try(Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String deleteGameStat = """
                        DELETE FROM civilization_stat
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
