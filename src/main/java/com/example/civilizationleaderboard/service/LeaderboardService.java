package com.example.civilizationleaderboard.service;

import com.example.civilizationleaderboard.DtoMapper;
import com.example.civilizationleaderboard.dto.CreateLeaderboardDto;
import com.example.civilizationleaderboard.dto.GameStatDto;
import com.example.civilizationleaderboard.dto.ViewLeaderboardDto;
import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.Leaderboard;
import com.example.civilizationleaderboard.repository.LeaderboardRepository;
import org.springframework.stereotype.Service;

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
        return dtoMapper.toViewLeaderboardDto(leaderboard);
    }

    public void addGameStatToLeaderboard(GameStatDto gameStatDto) {
        GameStat gameStat = dtoMapper.toGameStat(gameStatDto);
        int leaderboardId = gameStat.getLeaderboardId();

        leaderboardRepository.addGameStat(gameStat, leaderboardId);
    }
}
