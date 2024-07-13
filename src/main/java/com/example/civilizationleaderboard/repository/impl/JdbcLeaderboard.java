package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.GameStat;
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
                leaderboard = new Leaderboard(
                        resultSet.getString(2),
                        resultSet.getString(3)
                );

                if (resultSet.getString(6) != null) {
                    List<GameStat> gameStatList = getGameStatListFromLeaderboard(resultSet);
                    List<User> players = getAllPlayersFromLeaderboard(leaderboardId);

                    leaderboard.setPlayers(players);
                    leaderboard.setGameStatList(gameStatList);

                    return leaderboard;
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return leaderboard;
    }

    private List<User> getAllPlayersFromLeaderboard(int leaderboardId) {
        List<User> players = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()){
            try {
                connection.setAutoCommit(false);

                String getAllPlayers = """
                        SELECT DISTINCT account_username FROM leaderboard l
                        LEFT JOIN game_stat gs ON gs.leaderboard_id = l.id
                        WHERE l.id = ?;
                        """;

                PreparedStatement preparedStatement = connection.prepareStatement(getAllPlayers);
                preparedStatement.setInt(1, leaderboardId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    User player = new User(resultSet.getString(1));
                    players.add(player);
                }

                connection.commit();
                connection.setAutoCommit(true);

            }catch (SQLException sqlException) {
                connection.rollback();
                connection.setAutoCommit(true);
                sqlException.printStackTrace();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return players;
    }

    private List<GameStat> getGameStatListFromLeaderboard(ResultSet leaderboardsRs) {
        List<GameStat> gameStatList = new ArrayList<>();
        try {
            //The courser has already been moved down the list once at the if check,
            //therefore we will have to instance GameStat, before running the while-loop
            GameStat gameStat = new GameStat(
                    leaderboardsRs.getInt(5),
                    leaderboardsRs.getString(6),
                    leaderboardsRs.getInt(7),
                    leaderboardsRs.getString(8),
                    leaderboardsRs.getBoolean(9),
                    leaderboardsRs.getInt(11),
                    VictoryType.valueOf(leaderboardsRs.getString(10)),
                    leaderboardsRs.getInt(12),
                    leaderboardsRs.getInt(13)
            );
            gameStatList.add(gameStat);

            while (leaderboardsRs.next()) {
                gameStat = new GameStat(
                        leaderboardsRs.getInt(5),
                        leaderboardsRs.getString(6),
                        leaderboardsRs.getInt(7),
                        leaderboardsRs.getString(8),
                        leaderboardsRs.getBoolean(9),
                        leaderboardsRs.getInt(11),
                        VictoryType.valueOf(leaderboardsRs.getString(10)),
                        leaderboardsRs.getInt(12),
                        leaderboardsRs.getInt(13)
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
    public boolean editLeaderboard(int leaderboardId) {
        return false;
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
    public boolean addGameStat(GameStat gameStat, int leaderboardId) {
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
//                preparedStatement.setInt(1, leaderboardId);
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
