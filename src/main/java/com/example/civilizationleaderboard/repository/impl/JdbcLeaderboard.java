package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.model.Game;
import com.example.civilizationleaderboard.model.Leaderboard;
import com.example.civilizationleaderboard.model.User;
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
        List<Game> gameList;

        try (Connection connection = dataSource.getConnection()) {
            String getLeaderboard = """
                    SELECT * FROM leaderboard l
                    WHERE l.id = ?;
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(getLeaderboard);
            preparedStatement.setInt(1, leaderboardId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                leaderboard = new Leaderboard(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)
                );

                gameList = getAllGamesInLeaderboard(leaderboardId);
                leaderboard.setGameList(gameList);
                leaderboard.setPlayers(getAllPlayersInLeaderboard(leaderboardId));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return leaderboard;
    }

    private List<Game> getAllGamesInLeaderboard(int leaderboardId) {
        Game game = null;
        List<Game> gameList = new ArrayList<>();
        List<CivilizationStat> civilizationStatList = null;

        try (Connection connection = dataSource.getConnection()) {
            String getAllGames = """
                    SELECT * FROM game g
                    LEFT JOIN civilization_stat cs ON g.id = cs.game_id
                    WHERE g.leaderboard_id = ?
                    ORDER BY cs.id ASC;
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(getAllGames);
            preparedStatement.setInt(1, leaderboardId);
            ResultSet resultSet = preparedStatement.executeQuery();

            int gameId = 0;
            while (resultSet.next()) {
                if (gameId != resultSet.getInt(1)) {
                    civilizationStatList = new ArrayList<>();
                    game = new Game(
                            resultSet.getInt(1),
                            leaderboardId,
                            resultSet.getString(2)
                    );
                    gameList.add(game);
                }

                CivilizationStat civilizationStat = new CivilizationStat(
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getInt(6),
                        resultSet.getString(7),
                        resultSet.getBoolean(8),
                        resultSet.getInt(10),
                        VictoryType.valueOf(resultSet.getString(9)),
                        resultSet.getInt(11),
                        resultSet.getInt(12)
                );

                civilizationStatList.add(civilizationStat);
                game.setCivilizationStatList(civilizationStatList);

                gameId = resultSet.getInt(1);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return gameList;
    }

    public List<User> getAllPlayersFromGame(int gameId) {
        List<User> players = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String getAllPlayers = """
                    SELECT DISTINCT account_username
                    FROM civilization_stat cs
                    LEFT JOIN game g ON g.id = cs.game_id
                    WHERE g.id = ?;
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(getAllPlayers);
            preparedStatement.setInt(1, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User player = new User(resultSet.getString(1));
                players.add(player);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return players;
    }

    private List<User> getAllPlayersInLeaderboard(int leaderboardId) {
        List<User> playerList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String getAllPlayersInLeaderboard = """
                    SELECT DISTINCT account_username
                    FROM leaderboard l
                    LEFT JOIN game g ON g.leaderboard_id = l.id
                    LEFT JOIN civilization_stat cs ON g.id = cs.game_id
                    WHERE l.id = ?
                    ORDER BY account_username;
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(getAllPlayersInLeaderboard);
            preparedStatement.setInt(1, leaderboardId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User player = new User(resultSet.getString(1));
                playerList.add(player);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return playerList;
    }

    @Override
    public List<Leaderboard> getAllLeaderboards(String username) {
        return null;
    }

    @Override
    public Leaderboard createLeaderboard(Leaderboard leaderboard) {
        Leaderboard createdLeaderboard = null;

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String createLeaderboard = """
                        INSERT INTO leaderboard (name, description) VALUES(?, ?);
                        """;
                PreparedStatement preparedStatement = connection.prepareStatement(createLeaderboard, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, leaderboard.getName());
                preparedStatement.setString(2, leaderboard.getDescription());
                preparedStatement.executeUpdate();

                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int leaderboardId = generatedKeys.getInt(1);

                    createdLeaderboard = getCreatedLeaderboard(connection, leaderboardId);
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

        return createdLeaderboard;
    }

    private Leaderboard getCreatedLeaderboard(Connection connection, int leaderboardId) {
        Leaderboard createdLeaderboard = null;

        try {
            String getCreatedLeaderboard = """
                    SELECT * FROM leaderboard WHERE id = ?
                    """;

            PreparedStatement ps = connection.prepareStatement(getCreatedLeaderboard);
            ps.setInt(1, leaderboardId);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            createdLeaderboard = new Leaderboard(
                    leaderboardId,
                    resultSet.getString(2),
                    resultSet.getString(3)
            );

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return createdLeaderboard;
    }

    @Override
    public Leaderboard editLeaderboard(Leaderboard leaderboardToEdit) {
        Leaderboard editedLeaderboard = null;

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String editLeaderboard = """
                        UPDATE leaderboard
                        SET name = ?, description = ?
                        WHERE id = ?;
                        """;

                PreparedStatement preparedStatement = connection.prepareStatement(editLeaderboard);
                preparedStatement.setString(1, leaderboardToEdit.getName());
                preparedStatement.setString(2, leaderboardToEdit.getDescription());
                preparedStatement.setInt(3, leaderboardToEdit.getId());
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

        editedLeaderboard = getLeaderboard(leaderboardToEdit.getId());
        return editedLeaderboard;
    }

    @Override
    public boolean deleteLeaderboard(int leaderboardId) {
        boolean isDeleted = false;

        try (Connection connection = dataSource.getConnection()) {
            try {
                connection.setAutoCommit(false);

                String deleteLeaderboard = """
                        DELETE FROM leaderboard
                        WHERE id = ?;
                        """;
                PreparedStatement preparedStatement = connection.prepareStatement(deleteLeaderboard);
                preparedStatement.setInt(1, leaderboardId);
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

    //OTHER
    @Override
    public boolean addGameStat(CivilizationStat civilizationStat, int leaderboardId) {
        //Currently this method is redundant since the gamestat automaticly gets added to the leaderboard when its created.
        //In this version you cant have a personal log that tracks all your games.
        //For that version we would want this method.

//        boolean isAdded = false;
//        int gameStatId = gameStat.getId();
//
//        try (Connection connection = dataSource.getConnection()){
//            try{
//                connection.setAutoCommit(false);
//
//                String addGameStatToLeaderboard = """
//                        UPDATE game_stat
//                        SET leaderboard_id = ?
//                        WHERE id = ?;
//                        """;
//
//                PreparedStatement preparedStatement = connection.prepareStatement(addGameStatToLeaderboard);
//                preparedStatement.setInt(1, gameId);
//                preparedStatement.setInt(2, gameStatId);
//                int affectedRows = preparedStatement.executeUpdate();
//                isAdded = affectedRows > 0;
//
//                connection.commit();
//                connection.setAutoCommit(true);
//
//            }catch (SQLException sqlException) {
//                connection.rollback();
//                connection.setAutoCommit(true);
//                sqlException.printStackTrace();
//            }
//
//        }catch (SQLException sqlException) {
//            sqlException.printStackTrace();
//        }
//
//        return isAdded;
        return false;
    }

    @Override
    public boolean makePublic(int leaderboardId) {
        return false;
    }
}
