package com.example.civilizationleaderboard.controller;

import com.example.civilizationleaderboard.service.GameService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
}
