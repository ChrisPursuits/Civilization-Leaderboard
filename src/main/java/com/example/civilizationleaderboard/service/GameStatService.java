package com.example.civilizationleaderboard.service;

import com.example.civilizationleaderboard.DtoMapper;
import com.example.civilizationleaderboard.dto.CreateGameStatDto;
import com.example.civilizationleaderboard.dto.GameStatDto;
import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.repository.GameStatRepository;
import org.springframework.stereotype.Service;

@Service
public class GameStatService {

    private GameStatRepository gameStatRepository;
    private DtoMapper dtoMapper;

    public GameStatService(GameStatRepository gameStatRepository, DtoMapper dtoMapper) {
        this.gameStatRepository = gameStatRepository;
        this.dtoMapper = dtoMapper;
    }

    public GameStatDto getGameStat(int gameStatId) {
        GameStat gameStat = gameStatRepository.getGameStat(gameStatId);
        return dtoMapper.toGameStatDto(gameStat);
    }

    public void createGameStat(CreateGameStatDto gameStat) {
        GameStat gameStatToCreate = dtoMapper.toGameStat(gameStat);
        gameStatRepository.createGameStat(gameStatToCreate);
    }
}
