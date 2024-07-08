package com.example.civilizationleaderboard.repository.impl;

import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.Leaderboard;
import com.example.civilizationleaderboard.repository.LeaderboardRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcLeaderboard implements LeaderboardRepository {

    private DataSource dataSource;

    public JdbcLeaderboard(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //CRUD Operations
    @Override
    public Leaderboard getLeaderboard(long userId) {
        return null;
    }

    @Override
    public List<Leaderboard> getAllLeaderboards(long userId) {
        return null;
    }

    @Override
    public boolean createLeaderboard(Leaderboard leaderboard) {
        boolean isCreated = false;

        try(Connection connection = dataSource.getConnection()){
            try{
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

            }catch (SQLException sqlException){
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
