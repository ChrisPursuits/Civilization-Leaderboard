package com.example.civilizationleaderboard.service;

import com.example.civilizationleaderboard.DtoMapper;
import com.example.civilizationleaderboard.dto.CreateGameStatDto;
import com.example.civilizationleaderboard.dto.GameStatDto;
import com.example.civilizationleaderboard.model.CivilizationStat;
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

    public void createGameStat(CreateGameStatDto gameStat) {
        CivilizationStat civilizationStatToCreate = dtoMapper.toGameStat(gameStat);
        gameStatRepository.createGameStat(civilizationStatToCreate);
    }

    public GameStatDto getGameStat(int gameStatId) {
        CivilizationStat civilizationStat = gameStatRepository.getGameStat(gameStatId);
        return dtoMapper.toGameStatDto(civilizationStat);
    }

    public GameStatDto editGameStat(GameStatDto gameStatDto) {
        CivilizationStat civilizationStat = dtoMapper.toGameStat(gameStatDto);
        CivilizationStat updatedCivilizationStat = gameStatRepository.editGameStat(civilizationStat);
        return dtoMapper.toGameStatDto(updatedCivilizationStat);
    }

    public boolean deleteGameStat(int gameStatId) {
        return gameStatRepository.deleteGameStat(gameStatId);
    }
}
