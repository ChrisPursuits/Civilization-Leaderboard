package com.example.civilizationleaderboard.dto;

import victoryTypeEnum.VictoryType;

public record CreateGameStatDto(
        String name,
        boolean haveWon,
        int victoryPoints,
        VictoryType victoryType,
        int science,
        int culture
) {
}
