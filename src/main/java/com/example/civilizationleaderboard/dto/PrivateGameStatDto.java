package com.example.civilizationleaderboard.dto;

import victoryTypeEnum.VictoryType;

//TODO version PRIVATE LOG
public record PrivateGameStatDto(
        int id,
        String accountUsername,
        String name,
        boolean haveWon,
        int victoryPoints,
        VictoryType victoryType,
        int science,
        int culture
) {
}
