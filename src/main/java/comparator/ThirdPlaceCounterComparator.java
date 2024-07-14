package comparator;

import com.example.civilizationleaderboard.model.User;

import java.util.Comparator;

public class ThirdPlaceCounterComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return Integer.compare(u1.getThirdPlaceCount(), u2.getThirdPlaceCount());
    }
}
