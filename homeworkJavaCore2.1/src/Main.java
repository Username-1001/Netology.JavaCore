import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 5, 16, -1, -2, 0, 32, 3, 5, 8, 23, 4);

        List<Integer> result = new ArrayList<>();

        for (Integer comp : intList) {
            if (comp > 0 && comp % 2 == 0) {
                result.add(comp);
            }
        }

        Collections.sort(result, Comparator.naturalOrder());

        System.out.println(result);
    }
}
