package com.example.civilizationleaderboard;

import com.example.civilizationleaderboard.dto.CreateLeaderboardDto;
import com.example.civilizationleaderboard.dto.ViewLeaderboardDto;
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
}
