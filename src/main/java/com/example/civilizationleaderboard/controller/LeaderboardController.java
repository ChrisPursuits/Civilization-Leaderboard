package com.example.civilizationleaderboard.controller;

import com.example.civilizationleaderboard.dto.LeaderboardDto;
import com.example.civilizationleaderboard.service.LeaderboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @PostMapping("/create")
    public void createLeaderboard(@RequestBody LeaderboardDto leaderboardDto) {
        leaderboardService.createLeaderboard(leaderboardDto);
    }
}