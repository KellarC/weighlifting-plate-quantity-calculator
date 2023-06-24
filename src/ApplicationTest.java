import org.junit.jupiter.api.Test;
import java.util.List;
public class ApplicationTest {
    @Test
    // Need to refactor into functional style
    public void testPlateQuantitiesAndWeightSum() {
        for (int weight = 45; weight <= 1000000; weight += 5) {
            List<Integer> plateQuantities = Main.analyze(weight);
            double sum = 45.00; // Always start with the bar
            for (int i = 0; i < plateQuantities.size(); i++) {
                int quantity = plateQuantities.get(i) * 2; // Number of plates on both sides
                double plateWeight = Main.PLATES.get(i).getWeight();
                sum += quantity * plateWeight;
            }
            assert(sum == weight);
        }
    }
}






