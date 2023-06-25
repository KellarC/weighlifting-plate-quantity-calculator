import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.IntStream;

public class ApplicationTest {
    @Test
    public void testPlateQuantitiesAndWeightSum() {
        IntStream.iterate(45, weight -> weight <= 1000000, weight -> weight + 5)
                .forEach(weight -> {
                    List<Integer> plateQuantities = Main.analyze(weight);
                    double sum = 45.00 + IntStream.range(0, plateQuantities.size())
                            .mapToDouble(i -> plateQuantities.get(i) * 2 * Main.PLATES.get(i).getWeight())
                            .sum();
                    Assertions.assertEquals(weight, sum, 0);
                });
    }
}






