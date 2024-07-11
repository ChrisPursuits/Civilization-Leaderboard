package com.example.civilizationleaderboard.dto;

import victoryTypeEnum.VictoryType;

public record GameStatDto(
        int id,
        String accountUsername,
        int leaderboardId,
        String name,
        boolean haveWon,
        int victoryPoints,
        VictoryType victoryType,
        int science,
        int culture
) {
}
