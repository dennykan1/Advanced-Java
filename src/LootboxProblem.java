import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class LootboxProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<Integer> firstQueueBox = new ArrayDeque<>();
        ArrayDeque<Integer> secondStackBox = new ArrayDeque<>();

        Arrays.stream(read.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(firstQueueBox::offer);

        Arrays.stream(read.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(secondStackBox::push);

        int sum = 0;
        while (!firstQueueBox.isEmpty() && !secondStackBox.isEmpty()) {
            int firstItemPeek = firstQueueBox.peek();
            int secondItemPeek = secondStackBox.peek();

            if ((firstItemPeek + secondItemPeek) % 2 == 0) {

                sum += firstItemPeek + secondItemPeek;
                firstQueueBox.poll();
                secondStackBox.pop();
            } else {
                firstQueueBox.offer(secondStackBox.pop());
            }

        }
        if (firstQueueBox.isEmpty()) {
            System.out.println("First lootbox is empty");
        }
        if (secondStackBox.isEmpty()) {
            System.out.println("Second lootbox is empty");
        }

        if (sum < 100) {
            System.out.printf("Your loot was poor... Value: %d", sum);
        } else {
            System.out.printf("Your loot was epic! Value: %d", sum);
        }
    }
}
