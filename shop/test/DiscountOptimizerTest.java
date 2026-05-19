import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DiscountOptimizerTest {

    @Test
    void shouldFindBestDiscountOrder() {
        DiscountOptimizer optimizer = new DiscountOptimizer();
        List<Product> products = List.of(
                new Product("P1", "Przedmiot 1", 100.0),
                new Product("P2", "Przedmiot 2", 100.0),
                new Product("P3", "Przedmiot 3", 150.0)
        );

        DiscountStrategy thresholdDiscount = new ValueThresholdDiscount(300.0, 0.10);
        DiscountStrategy twoPlusOne = new BuyTwoGetOneFreeDiscount();

        List<DiscountStrategy> discounts = List.of(thresholdDiscount, twoPlusOne);

        List<DiscountStrategy> bestOrder = optimizer.findBestDiscountOrder(products, discounts);

        assertNotNull(bestOrder);
        assertEquals(2, bestOrder.size());

        assertEquals(thresholdDiscount, bestOrder.get(0));
        assertEquals(twoPlusOne, bestOrder.get(1));
    }
}