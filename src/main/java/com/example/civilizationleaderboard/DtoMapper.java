package com.example.civilizationleaderboard;

import com.example.civilizationleaderboard.dto.LeaderboardDto;
import com.example.civilizationleaderboard.model.Leaderboard;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {

    public Leaderboard toLeaderboard(LeaderboardDto dto) {
        return new Leaderboard(dto.name(), dto.description());
    }
}
