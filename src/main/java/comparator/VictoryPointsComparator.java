package comparator;

import com.example.civilizationleaderboard.model.CivilizationStat;
import com.example.civilizationleaderboard.model.User;

import java.util.Comparator;

public class VictoryPointsComparator implements Comparator<CivilizationStat> {
    @Override
    public int compare(CivilizationStat cs1, CivilizationStat cs2) {
        return Integer.compare(cs1.getVictoryPoints(), cs2.getVictoryPoints());
    }
}
