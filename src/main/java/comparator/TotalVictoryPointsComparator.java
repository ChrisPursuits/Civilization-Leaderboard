package comparator;

import com.example.civilizationleaderboard.model.User;

import java.util.Comparator;

public class TotalVictoryPointsComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return Integer.compare(u1.getTotalVictoryPoints(), u2.getTotalVictoryPoints());
    }
}
