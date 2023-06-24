import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.text.DecimalFormat;

public class Main {
    static final int BAR = 45;
    static final Plate FORTY_FIVE = new Plate(45);
    static final Plate TWENTY_FIVE = new Plate(25);
    static final Plate TEN = new Plate(10);
    static final Plate FIVE = new Plate(5);
    static final Plate TWO_AND_HALF = new Plate(2.5);
    static List<Plate> PLATES = Arrays.asList(FORTY_FIVE, TWENTY_FIVE, TEN, FIVE, TWO_AND_HALF);
    public static int getWeight() {
        Scanner x = new Scanner(System.in);  // Create a Scanner object
        while (true) {
            try {
                System.out.println("Enter desired weight in lbs (ex. '335'): ");
                int input = x.nextInt();  // Read user input
                if (!(input % 5 == 0)) {
                    throw new IllegalArgumentException("Weight must be divisible by 5.");
                } else if (!(input >= 45)) {
                    throw new IllegalArgumentException("Weight must be greater than or equal to 45lbs");
                }
                return input;
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
                x.nextLine(); // Continue
            }
        }
    }
    public static List<Integer> analyze(int weight) {
        final int remainingWeight = weight - BAR;
        final int[] weightHolder = { remainingWeight };
        return PLATES.stream()
                .map(plate -> { // For each element in PLATES
                    int quantity = (int) (weightHolder[0] / (2 * plate.getWeight())); // Calculate how many plates go on each side
                    weightHolder[0] -= quantity * (2 * plate.getWeight()); // Subtract however many plates times their weight from the overall weight
                    return quantity; // Add how many plates go on each side to return list
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        try {
            int weight = getWeight();
            List<Integer> plateQuantities = analyze(weight);
            DecimalFormat decimalFormat = new DecimalFormat("0.0");
            IntStream.range(0, plateQuantities.size())
                    .forEach(i -> {
                        Plate plate = PLATES.get(i);
                        String weightText;
                        if (!(plate.equals(TWO_AND_HALF))) {
                            weightText = String.valueOf((int) plate.getWeight());
                        } else {
                            weightText = decimalFormat.format(plate.getWeight());
                        }
                        System.out.println(weightText + "lb per side: " + plateQuantities.get(i));
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}