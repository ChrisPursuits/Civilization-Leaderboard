package com.example.civilizationleaderboard.dto;

import victoryTypeEnum.VictoryType;

public record CreateCivStatDto(
        String accountUsername,
        int gameId,
        String name,
        boolean haveWon,
        int victoryPoints,
        VictoryType victoryType,
        int science,
        int culture
) {
}
