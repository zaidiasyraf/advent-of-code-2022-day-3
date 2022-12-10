import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ArrayUtils;

public class Main {

    public static void main(String... args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/input.txt"))) {
            String line = reader.readLine();
            String firstPart = "";
            String secondPart = "";

            Map<Character, Integer> characterValue = new HashMap<>();
            initializeMap(characterValue);

            Set<Character> firstSet = new HashSet<>();
            Set<Character> secondSet = new HashSet<>();
            Set<Character> firstFirstSet = new HashSet<>();
            Set<Character> secondSecondSet = new HashSet<>();

            int total1 = 0;
            int total2 = 0;

            int r = 0;

            while (line != null) {
                int half = line.length() / 2;
                firstPart = line.substring(0, half);
                secondPart = line.substring(half);
                firstSet.addAll(List.of(ArrayUtils.toObject(firstPart.toCharArray())));
                secondSet.addAll(List.of(ArrayUtils.toObject(secondPart.toCharArray())));
                firstSet.retainAll(secondSet);
                total1 = total1 + firstSet.stream().mapToInt(characterValue::get).sum();

                r = r + 1;
                if (r == 1) {
                    firstFirstSet.addAll(List.of(ArrayUtils.toObject(line.toCharArray())));
                } else {
                    secondSecondSet.addAll(List.of(ArrayUtils.toObject(line.toCharArray())));
                    firstFirstSet.retainAll(secondSecondSet);
                    secondSecondSet.clear();
                }
                if (r == 3) {
                    total2 = total2 + firstFirstSet.stream().mapToInt(characterValue::get).sum();
                    r = 0;
                    firstFirstSet.clear();
                }
                // read next line
                line = reader.readLine();
                firstSet.clear();
                secondSet.clear();
            }
            System.out.println("Total 1 is " + total1);
            System.out.println("Total 2 is " + total2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initializeMap(Map<Character, Integer> map) {
        char c;
        int i = 1;

        for (c = 'a'; c <= 'z'; ++c) {
            map.put(c, i);
            i++;
        }
        for(c = 'A'; c <= 'Z'; ++c) {
            map.put(c, i);
            i++;
        }
    }

}
