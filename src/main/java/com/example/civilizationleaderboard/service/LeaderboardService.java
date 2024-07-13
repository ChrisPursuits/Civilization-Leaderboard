package com.example.civilizationleaderboard.service;

import com.example.civilizationleaderboard.DtoMapper;
import com.example.civilizationleaderboard.dto.CreateLeaderboardDto;
import com.example.civilizationleaderboard.dto.GameStatDto;
import com.example.civilizationleaderboard.dto.ViewLeaderboardDto;
import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.Leaderboard;
import com.example.civilizationleaderboard.model.User;
import com.example.civilizationleaderboard.repository.LeaderboardRepository;
import comparator.TotalVictoryPointsComparator;
import comparator.TotalWinsComparator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final DtoMapper dtoMapper;

    public LeaderboardService(LeaderboardRepository leaderboardRepository, DtoMapper dtoMapper) {
        this.leaderboardRepository = leaderboardRepository;
        this.dtoMapper = dtoMapper;
    }

    public void createLeaderboard(CreateLeaderboardDto dto) {
        Leaderboard leaderboard = dtoMapper.toLeaderboard(dto);
        leaderboardRepository.createLeaderboard(leaderboard);
    }

    public ViewLeaderboardDto getLeaderboard(int leaderboardId) {
        Leaderboard leaderboard = leaderboardRepository.getLeaderboard(leaderboardId);
        Leaderboard sortedLeaderboard = sortLeaderboard(leaderboard);

        return dtoMapper.toViewLeaderboardDto(sortedLeaderboard);
    }

    private Leaderboard sortLeaderboard(Leaderboard leaderboard) {
        List<User> players = summarizePlayerStats(leaderboard);

        Collections.sort(players, new TotalWinsComparator().thenComparing(new TotalVictoryPointsComparator()));
        leaderboard.setPlayers(players);

        return leaderboard;
    }

    private List<User> summarizePlayerStats(Leaderboard leaderboard) {
        List<User> players = new ArrayList<>();

        for (User player : leaderboard.getPlayers()) {
            int totalWins = 0;
            int totalVictoryPoints = 0;
            int totalScience = 0;
            int totalCulture = 0;

            for (GameStat game : leaderboard.getGameStatList()) {
                if (player.equals(game.getAccountUsername()) && game.isHaveWon()) {
                    ++totalWins;
                }

                if (player.equals(game.getAccountUsername())) {
                    totalVictoryPoints += game.getVictoryPoints();
                    totalScience += game.getScience();
                    totalCulture += game.getCulture();
                }
            }

            player.setTotalWins(totalWins);
            player.setTotalVictoryPoints(totalVictoryPoints);
            player.setTotalScience(totalScience);
            player.setTotalCulture(totalCulture);
            players.add(player);
        }

        return players;
    }

    //FUTURE FEATURE
    public void addGameStatToLeaderboard(GameStatDto gameStatDto) {
        GameStat gameStat = dtoMapper.toGameStat(gameStatDto);
        int leaderboardId = gameStat.getLeaderboardId();
        leaderboardRepository.addGameStat(gameStat, leaderboardId);
    }
}
