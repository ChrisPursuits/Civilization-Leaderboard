package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.model.Game;
import com.example.civilizationleaderboard.model.User;
import com.example.civilizationleaderboard.repository.GameRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

@Repository
public class JdbcGame implements GameRepository {

    private final DataSource dataSource;

    public JdbcGame(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Game getGame(int gameId) {
        Game selectedGame = null;

        try (Connection connection = dataSource.getConnection()) {
            String getGameOnId = """
                    SELECT * FROM game
                    WHERE id = ?;
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(getGameOnId);
            preparedStatement.setInt(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                selectedGame = new Game(
                        resultSet.getInt(1),
                        resultSet.getInt(3),
                        resultSet.getString(2)
                );
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return selectedGame;
    }

    @Override
    public Game createGame(Game game) {
        Game createdGame;
        int gameId = 0;

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String createGame = """
                        INSERT INTO game (name, leaderboard_id)
                        VALUE (?, ?)
                        """;

                PreparedStatement preparedStatement = connection.prepareStatement(createGame, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, game.getName());
                preparedStatement.setInt(2, game.getLeaderboardId());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                generatedKeys.next();
                gameId = generatedKeys.getInt(1);


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

        createdGame = getGame(gameId);
        return createdGame;
    }

    @Override
    public Game editGame(Game game) {
        Game editedGame;
        int gameId = game.getId();

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String editGame = """
                        UPDATE game
                        SET name = ?
                        WHERE id = ?;
                        """;

                PreparedStatement preparedStatement = connection.prepareStatement(editGame);
                preparedStatement.setString(1, game.getName());
                preparedStatement.setInt(2, gameId);
                preparedStatement.executeUpdate();

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

        editedGame = getGame(gameId);
        return editedGame;
    }

    @Override
    public boolean deleteGame(int gameId) {
        boolean isDeleted = false;

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String deleteGame = """
                        DELETE FROM game
                        WHERE id = ?;
                        """;

                PreparedStatement preparedStatement = connection.prepareStatement(deleteGame);
                preparedStatement.setInt(1, gameId);
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
