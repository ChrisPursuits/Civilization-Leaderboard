package com.example.civilizationleaderboard.dto;

import victoryTypeEnum.VictoryType;

public record CivStatDto(
        int id,
        String accountUsername,
        int gameId,
        String name,
        boolean isFirstPlace,
        boolean isSecondPlace,
        boolean isThirdPlace,
        boolean isOtherPlace,
        int victoryPoints,
        VictoryType victoryType,
        int science,
        int culture
) {
}
