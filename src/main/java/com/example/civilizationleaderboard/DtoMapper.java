package com.example.civilizationleaderboard;

import com.example.civilizationleaderboard.dto.*;
import com.example.civilizationleaderboard.model.GameStat;
import com.example.civilizationleaderboard.model.Leaderboard;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    public Leaderboard toLeaderboard(CreateLeaderboardDto dto) {
        return new Leaderboard(dto.name(), dto.description());
    }

    public ViewLeaderboardDto toViewLeaderboardDto(Leaderboard leaderboard) {
        return new ViewLeaderboardDto(
                leaderboard.getName(),
                leaderboard.getDescription(),
                leaderboard.getGameStatList()
        );
    }

    public GameStat toGameStat(CreateGameStatDto gameStat) {
        return new GameStat(
                gameStat.accountUsername(),
                gameStat.leaderboardId(),
                gameStat.name(),
                gameStat.haveWon(),
                gameStat.victoryPoints(),
                gameStat.victoryType(),
                gameStat.science(),
                gameStat.culture()
        );
    }

    public GameStat toGameStat(GameStatDto gameStat) {
        return new GameStat(
                gameStat.id(),
                gameStat.accountUsername(),
                gameStat.leaderboardId(),
                gameStat.name(),
                gameStat.haveWon(),
                gameStat.victoryPoints(),
                gameStat.victoryType(),
                gameStat.science(),
                gameStat.culture()
        );
    }

    public ViewGameStatDto toGameStatDto(GameStat gameStat) {
        return new ViewGameStatDto(
                gameStat.getName(),
                gameStat.isHaveWon(),
                gameStat.getVictoryPoints(),
                gameStat.getVictoryType(),
                gameStat.getScience(),
                gameStat.getCulture()
        );
    }
}
