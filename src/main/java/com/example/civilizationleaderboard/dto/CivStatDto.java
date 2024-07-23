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
        VictoryType victoryType,
        int victoryPoints,
        int militaryStrength,
        int science,
        int culture,
        int gold,
        int religiousPoints,
        int diplomaticFavors
) {
}
