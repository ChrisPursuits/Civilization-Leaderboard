package comparator;

import com.example.civilizationleaderboard.model.User;

import java.util.Comparator;

public class FirstPlaceCounterComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        return Integer.compare(u1.getFirstPlaceCount(), u2.getFirstPlaceCount());
    }
}
