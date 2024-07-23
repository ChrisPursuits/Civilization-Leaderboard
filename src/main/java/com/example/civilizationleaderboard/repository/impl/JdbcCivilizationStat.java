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
    public CivilizationStat createCivStatAutoPlacement(CivilizationStat civilizationStatToCreate) {
        CivilizationStat createdCivStat = null;

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String createCivStat = """
                        INSERT INTO civilization_stat (account_username, game_id, name, is_first_place, victory_type, victory_points, science, culture, is_other_place, gold, military_strength, religious_points, diplomatic_favors)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                        """;


                PreparedStatement preparedStatement = connection.prepareStatement(createCivStat, Statement.RETURN_GENERATED_KEYS);

                if (civilizationStatToCreate.getVictoryType() != VictoryType.LOSE) {
                    preparedStatement.setBoolean(4, true);
                    preparedStatement.setBoolean(9, false);
                }
                preparedStatement.setString(1, civilizationStatToCreate.getAccountUsername());
                preparedStatement.setInt(2, civilizationStatToCreate.getGameId());
                preparedStatement.setString(3, civilizationStatToCreate.getName());
                preparedStatement.setString(5, civilizationStatToCreate.getVictoryType().toString());
                preparedStatement.setInt(6, civilizationStatToCreate.getVictoryPoints());
                preparedStatement.setInt(7, civilizationStatToCreate.getScience());
                preparedStatement.setInt(8, civilizationStatToCreate.getCulture());
                preparedStatement.setInt(10, civilizationStatToCreate.getGold());
                preparedStatement.setInt(11, civilizationStatToCreate.getMilitaryStrength());
                preparedStatement.setInt(12, civilizationStatToCreate.getReligiousPoints());
                preparedStatement.setInt(13, civilizationStatToCreate.getDiplomaticFavors());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int civStatId = generatedKeys.getInt(1);

                    createdCivStat = getCivilizationStat(civStatId);
                }

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

        return createdCivStat;
    }

    @Override
    public CivilizationStat createCivStatManuelPlacement(CivilizationStat civilizationStatToCreate) {
        CivilizationStat createdCivStat = null;

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String createCivStat = """
                        INSERT INTO civilization_stat (account_username, game_id, name, is_first_place, is_second_place, is_third_place, is_other_place, victory_type, victory_points, military_strength, science, culture, gold, religious_points, diplomatic_favors)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                        """;


                PreparedStatement preparedStatement = connection.prepareStatement(createCivStat, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setString(1, civilizationStatToCreate.getAccountUsername());
                preparedStatement.setInt(2, civilizationStatToCreate.getGameId());
                preparedStatement.setString(3, civilizationStatToCreate.getName());
                preparedStatement.setBoolean(4, civilizationStatToCreate.isFirstPlace());
                preparedStatement.setBoolean(5, civilizationStatToCreate.isSecondPlace());
                preparedStatement.setBoolean(6, civilizationStatToCreate.isThirdPlace());
                preparedStatement.setBoolean(7, civilizationStatToCreate.isOtherPlace());
                preparedStatement.setString(8, civilizationStatToCreate.getVictoryType().toString());
                preparedStatement.setInt(9, civilizationStatToCreate.getVictoryPoints());
                preparedStatement.setInt(10, civilizationStatToCreate.getMilitaryStrength());
                preparedStatement.setInt(11, civilizationStatToCreate.getScience());
                preparedStatement.setInt(12, civilizationStatToCreate.getCulture());
                preparedStatement.setInt(13, civilizationStatToCreate.getGold());
                preparedStatement.setInt(14, civilizationStatToCreate.getReligiousPoints());
                preparedStatement.setInt(15, civilizationStatToCreate.getDiplomaticFavors());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int civStatId = generatedKeys.getInt(1);

                    createdCivStat = getCivilizationStat(civStatId);
                }

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

        return createdCivStat;
    }

    @Override
    public CivilizationStat getCivilizationStat(int civStatId) {
        CivilizationStat civilizationStat = null;

        try (Connection connection = dataSource.getConnection()) {
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
                            resultSet.getBoolean(6),
                            resultSet.getBoolean(7),
                            resultSet.getBoolean(8),
                            VictoryType.valueOf(resultSet.getString(9)),
                            resultSet.getInt(10),
                            resultSet.getInt(11),
                            resultSet.getInt(12),
                            resultSet.getInt(13),
                            resultSet.getInt(14),
                            resultSet.getInt(15),
                            resultSet.getInt(16)
                    );
                }

                connection.commit();
                connection.setAutoCommit(true);

            } catch (SQLException sqlException) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return civilizationStat;
    }

    @Override
    public CivilizationStat editCivStat(CivilizationStat civilizationStat) {

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String editGameStats = """
                        UPDATE civilization_stat
                        SET name = ?, is_first_place = ?, is_second_place = ?, is_third_place = ?, is_other_place = ?, victory_type = ?, victory_points = ?, science = ?, culture = ?
                        WHERE id = ?;
                        """;

                PreparedStatement preparedStatement = connection.prepareStatement(editGameStats);
                preparedStatement.setString(1, civilizationStat.getName());
                preparedStatement.setBoolean(2, civilizationStat.isFirstPlace());
                preparedStatement.setBoolean(3, civilizationStat.isSecondPlace());
                preparedStatement.setBoolean(4, civilizationStat.isThirdPlace());
                preparedStatement.setBoolean(5, civilizationStat.isOtherPlace());
                preparedStatement.setString(6, civilizationStat.getVictoryType().toString());
                preparedStatement.setInt(7, civilizationStat.getVictoryPoints());
                preparedStatement.setInt(8, civilizationStat.getScience());
                preparedStatement.setInt(9, civilizationStat.getCulture());
                preparedStatement.setInt(10, civilizationStat.getId());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    connection.commit();
                    connection.setAutoCommit(true);
                    return civilizationStat;
                }

                connection.rollback();
                connection.setAutoCommit(true);

            } catch (SQLException sqlException) {
                connection.rollback();
                connection.setAutoCommit(true);
                sqlException.printStackTrace();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean deleteCivStat(int gameStatId) {
        boolean isDeleted = false;

        try (Connection connection = dataSource.getConnection()) {
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

            } catch (SQLException sqlException) {
                connection.rollback();
                connection.setAutoCommit(true);
                sqlException.printStackTrace();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return isDeleted;
    }
}
