package com.example.civilizationleaderboard.service;

import com.example.civilizationleaderboard.DtoMapper;
import com.example.civilizationleaderboard.dto.CreateLeaderboardDto;
import com.example.civilizationleaderboard.dto.EditLeaderboardDto;
import com.example.civilizationleaderboard.dto.CivStatDto;
import com.example.civilizationleaderboard.dto.ViewLeaderboardDto;
import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.model.Game;
import com.example.civilizationleaderboard.model.Leaderboard;
import com.example.civilizationleaderboard.model.User;
import com.example.civilizationleaderboard.repository.LeaderboardRepository;
import comparator.FirstPlaceCounterComparator;
import comparator.SecondPlaceCounterComparator;
import comparator.ThirdPlaceCounterComparator;
import comparator.VictoryPointsComparator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;
    private final DtoMapper dtoMapper;

    public LeaderboardService(LeaderboardRepository leaderboardRepository, DtoMapper dtoMapper) {
        this.leaderboardRepository = leaderboardRepository;
        this.dtoMapper = dtoMapper;
    }

    public void createLeaderboard(CreateLeaderboardDto dto) {
        Leaderboard leaderboard = dtoMapper.toLeaderboard(dto);
        leaderboardRepository.createLeaderboard(leaderboard);
    }

    public ViewLeaderboardDto getLeaderboardSortedByVictoryPoints(int leaderboardId) {
        Leaderboard leaderboard = leaderboardRepository.getLeaderboard(leaderboardId);
        Leaderboard sortedLeaderboard = sortLeaderboardByVictoryPoints(leaderboard);

        return dtoMapper.toViewLeaderboardDto(sortedLeaderboard);
    }

    public ViewLeaderboardDto getLeaderboardSortedByPlacements(int leaderboardId) {
        Leaderboard leaderboard = leaderboardRepository.getLeaderboard(leaderboardId);
        Leaderboard sortedLeaderboard = sortLeaderboardByPlacements(leaderboard);

        return dtoMapper.toViewLeaderboardDto(sortedLeaderboard);
    }

    private Leaderboard sortLeaderboardByVictoryPoints(Leaderboard leaderboard) {

        //Loop through list of players in leaderboard
        List<User> playerList = leaderboard.getPlayers();
        for (User player : playerList) {
            String playerName = player.getUsername();

            int gamesPlayed = 0;
            int firstPlaceCount = 0;
            int secondPlaceCount = 0;
            int thirdPlaceCount = 0;
            int otherPlacementCount = 0;

            //Loop through all the games in the leaderboard.
            List<Game> gameList = leaderboard.getGameList();
            for (Game game : gameList) {

                //sort every civStats in every game by victory points.
                //if 2 players have the same amount of victory points, the first player, will receive 1 placement higher than the player after.
                //it simply gets decided by alphabetic order, when the list a players gets queried in LeaderboardRepository
                List<CivilizationStat> civStatList = game.getCivilizationStatList();
                Collections.sort(civStatList, new VictoryPointsComparator().reversed());

                //track player/leaderboard stats.
                ++gamesPlayed;

                for (int i = 0; i < civStatList.size(); i++) {
                    String accountUsername = civStatList.get(i).getAccountUsername();

                    if (playerName.equalsIgnoreCase(accountUsername)) {
                        int placementCounter = switch (i) {
                            case 0 -> ++firstPlaceCount;
                            case 1 -> ++secondPlaceCount;
                            case 2 -> ++thirdPlaceCount;
                            default -> ++otherPlacementCount;
                        };
                    }
                    
                }
            }

            player.setGamesPlayed(gamesPlayed);
            player.setFirstPlaceCount(firstPlaceCount);
            player.setSecondPlaceCount(secondPlaceCount);
            player.setThirdPlaceCount(thirdPlaceCount);
            player.setOtherPlacementCount(otherPlacementCount);
        }

        //Sort the player list.
        Collections.sort(playerList,
                new FirstPlaceCounterComparator()
                        .thenComparing(new SecondPlaceCounterComparator()
                                .thenComparing(new ThirdPlaceCounterComparator())).reversed());

        return leaderboard;
    }

    private Leaderboard sortLeaderboardByPlacements(Leaderboard leaderboard) {

        List<User> players = leaderboard.getPlayers();
        List<Game> games = leaderboard.getGameList();

        for (User player:players) {
        String playerName = player.getUsername();

            int gamesPlayed = 0;
            int firstPlaceCount = 0;
            int secondPlaceCount = 0;
            int thirdPlaceCount = 0;
            int otherPlacementCount = 0;

            for (Game game:games) {
                List<CivilizationStat> civilizationStats = game.getCivilizationStatList();

                ++gamesPlayed;

                for (CivilizationStat civStat:civilizationStats) {
                    String civStatName = civStat.getAccountUsername();

                    if (civStatName.equalsIgnoreCase(playerName)) {
                        if (civStat.isFirstPlace()) {
                            ++firstPlaceCount;
                            break;
                        }
                        if (civStat.isSecondPlace()) {
                            ++secondPlaceCount;
                            break;
                        }
                        if (civStat.isThirdPlace()) {
                            ++thirdPlaceCount;
                            break;
                        }
                        if (civStat.isOtherPlace()) {
                            ++otherPlacementCount;
                            break;
                        }
                    }
                }
            }

            player.setFirstPlaceCount(firstPlaceCount);
            player.setSecondPlaceCount(secondPlaceCount);
            player.setThirdPlaceCount(thirdPlaceCount);
            player.setOtherPlacementCount(otherPlacementCount);
            player.setGamesPlayed(gamesPlayed);
        }

        //Sort the player list.
        Collections.sort(players,
                new FirstPlaceCounterComparator()
                        .thenComparing(new SecondPlaceCounterComparator()
                                .thenComparing(new ThirdPlaceCounterComparator())).reversed());

        return leaderboard;
    }

    public ViewLeaderboardDto editLeaderboard(EditLeaderboardDto editLeaderboardDto) {
        Leaderboard leaderboardToEdit = dtoMapper.toLeaderboard(editLeaderboardDto);
        Leaderboard editedLeaderboard = leaderboardRepository.editLeaderboard(leaderboardToEdit);
        return dtoMapper.toViewLeaderboardDto(editedLeaderboard);
    }

    public boolean deleteLeaderboard(int leaderboardId) {
        return leaderboardRepository.deleteLeaderboard(leaderboardId);
    }

    //FUTURE FEATURE
    public void addGameStatToLeaderboard(CivStatDto civStatDto) {
        CivilizationStat civilizationStat = dtoMapper.toCivStat(civStatDto);
        int leaderboardId = civilizationStat.getGameId();
        leaderboardRepository.addGameStat(civilizationStat, leaderboardId);
    }
}
