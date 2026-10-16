
import java.util.*;

public class Leaderboard {
    public static void main(String[] args) {
        Map<String, Integer> scores = new HashMap<>();

        scores.put("Riya", 90);
        scores.put("Amit", 95);
        scores.put("Neha", 90);
        scores.put("Raj", 85);
        scores.put("Karan", 95);

        List<Map.Entry<String, Integer>> list =
                new ArrayList<>(scores.entrySet());

        list.sort((a, b) -> {
            int result = b.getValue().compareTo(a.getValue());
            if (result == 0)
                return a.getKey().compareTo(b.getKey());
            return result;
        });

        System.out.println("Top 3 Players:");
        for (int i = 0; i < 3 && i < list.size(); i++) {
            System.out.println(list.get(i).getKey() + " : "
                    + list.get(i).getValue());
        }
    }
}
