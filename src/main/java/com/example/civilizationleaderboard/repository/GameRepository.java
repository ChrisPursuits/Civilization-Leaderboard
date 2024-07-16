package com.example.civilizationleaderboard.repository;

import com.example.civilizationleaderboard.model.Game;

public interface GameRepository {

    Game getGame(int gameId);

    Game createGame(Game game);

    Game editGame(Game game);

    boolean deleteGame(int gameId);
}
