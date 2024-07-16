package com.example.civilizationleaderboard.service;

import com.example.civilizationleaderboard.DtoMapper;
import com.example.civilizationleaderboard.dto.CreateGameDto;
import com.example.civilizationleaderboard.dto.ViewGameDto;
import com.example.civilizationleaderboard.model.Game;
import com.example.civilizationleaderboard.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final DtoMapper dtoMapper;

    public GameService(GameRepository gameRepository, DtoMapper dtoMapper) {
        this.gameRepository = gameRepository;
        this.dtoMapper = dtoMapper;
    }

    public void createGame(CreateGameDto gameDto) {
        Game game = dtoMapper.toGame(gameDto);
        gameRepository.createGame(game);
    }
}
