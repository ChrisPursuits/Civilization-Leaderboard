package com.example.civilizationleaderboard.service;

import com.example.civilizationleaderboard.DtoMapper;
import com.example.civilizationleaderboard.dto.CreateCivStatDto;
import com.example.civilizationleaderboard.dto.CivStatDto;
import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.repository.CivilizationStatRepository;
import org.springframework.stereotype.Service;

@Service
public class CivilizationStatService {

    private CivilizationStatRepository civilizationStatRepository;
    private DtoMapper dtoMapper;

    public CivilizationStatService(CivilizationStatRepository civilizationStatRepository, DtoMapper dtoMapper) {
        this.civilizationStatRepository = civilizationStatRepository;
        this.dtoMapper = dtoMapper;
    }

    public void createGameStat(CreateCivStatDto gameStat) {
        CivilizationStat civilizationStatToCreate = dtoMapper.toCivStat(gameStat);
        civilizationStatRepository.createCivStat(civilizationStatToCreate);
    }

    public CivStatDto getGameStat(int gameStatId) {
        CivilizationStat civilizationStat = civilizationStatRepository.getCivilizationStat(gameStatId);
        return dtoMapper.toCivStatDto(civilizationStat);
    }

    public CivStatDto editGameStat(CivStatDto civStatDto) {
        CivilizationStat civilizationStat = dtoMapper.toCivStat(civStatDto);
        CivilizationStat updatedCivilizationStat = civilizationStatRepository.editCivStat(civilizationStat);
        return dtoMapper.toCivStatDto(updatedCivilizationStat);
    }

    public boolean deleteGameStat(int gameStatId) {
        return civilizationStatRepository.deleteCivStat(gameStatId);
    }
}
