package com.example.civilizationleaderboard;

import com.example.civilizationleaderboard.dto.*;
import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.model.Leaderboard;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    public Leaderboard toLeaderboard(CreateLeaderboardDto dto) {
        return new Leaderboard(dto.name(), dto.description());
    }

    public Leaderboard toLeaderboard(EditLeaderboardDto dto) {
        return new Leaderboard(dto.name(), dto.description());
    }

    public ViewLeaderboardDto toViewLeaderboardDto(Leaderboard leaderboard) {
        return new ViewLeaderboardDto(
                leaderboard.getName(),
                leaderboard.getDescription(),
                leaderboard.getPlayers(),
                leaderboard.getGameList()
                );
    }

    public CivilizationStat toGameStat(CreateGameStatDto gameStat) {
        return new CivilizationStat(
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

    public CivilizationStat toPrivateGameStat(CreatePrivateGameStatDto gameStat) {
        return new CivilizationStat(
                gameStat.accountUsername(),
                gameStat.name(),
                gameStat.haveWon(),
                gameStat.victoryPoints(),
                gameStat.victoryType(),
                gameStat.science(),
                gameStat.culture()
        );
    }

    public CivilizationStat toGameStat(GameStatDto gameStat) {
        return new CivilizationStat(
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

    public GameStatDto toGameStatDto(CivilizationStat civilizationStat) {
        return new GameStatDto(
                civilizationStat.getId(),
                civilizationStat.getAccountUsername(),
                civilizationStat.getGameId(),
                civilizationStat.getName(),
                civilizationStat.isHaveWon(),
                civilizationStat.getVictoryPoints(),
                civilizationStat.getVictoryType(),
                civilizationStat.getScience(),
                civilizationStat.getCulture()
        );
    }

    public PrivateGameStatDto toPrivateGameStatDto(CivilizationStat civilizationStat) {
        return new PrivateGameStatDto(
                civilizationStat.getId(),
                civilizationStat.getAccountUsername(),
                civilizationStat.getName(),
                civilizationStat.isHaveWon(),
                civilizationStat.getVictoryPoints(),
                civilizationStat.getVictoryType(),
                civilizationStat.getScience(),
                civilizationStat.getCulture()
        );
    }
}
