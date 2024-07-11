package com.example.civilizationleaderboard.dto;

import victoryTypeEnum.VictoryType;

public record CreateGameStat(
        String name,
        boolean haveWon,
        int victoryPoints,
        VictoryType victoryType,
        int science,
        int culture
) {
}
