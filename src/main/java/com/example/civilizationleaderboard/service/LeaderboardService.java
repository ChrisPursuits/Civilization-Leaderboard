package com.example.civilizationleaderboard.service;

import com.example.civilizationleaderboard.DtoMapper;
import com.example.civilizationleaderboard.dto.CreateLeaderboardDto;
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
}
