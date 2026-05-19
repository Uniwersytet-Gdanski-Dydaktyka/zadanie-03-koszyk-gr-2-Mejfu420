import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DiscountStrategyTest {
    private List<Product> products;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>(List.of(
                new Product("P1", "Przedmiot 1", 100.0),
                new Product("P2", "Przedmiot 2", 150.0),
                new Product("P3", "Przedmiot 3", 80.0)
        ));
    }

    @Test
    void shouldApplyValueThresholdDiscount() {
        DiscountStrategy strategy = new ValueThresholdDiscount(300.0, 0.10);
        strategy.applyDiscount(products);

        assertEquals(90.0, products.get(0).getDiscountPrice());
        assertEquals(135.0, products.get(1).getDiscountPrice());
        assertEquals(72.0, products.get(2).getDiscountPrice());
    }

    @Test
    void shouldNotApplyValueThresholdDiscountIfBelowThreshold() {
        DiscountStrategy strategy = new ValueThresholdDiscount(500.0, 0.10);
        strategy.applyDiscount(products);

        assertEquals(100.0, products.get(0).getDiscountPrice());
    }

    @Test
    void shouldApplyBuyTwoGetOneFreeDiscount() {
        DiscountStrategy strategy = new BuyTwoGetOneFreeDiscount();
        strategy.applyDiscount(products);

        assertEquals(100.0, products.get(0).getDiscountPrice());
        assertEquals(150.0, products.get(1).getDiscountPrice());
        assertEquals(0.0, products.get(2).getDiscountPrice());
    }

    @Test
    void shouldAddFreeGiftIfAboveThreshold() {
        DiscountStrategy strategy = new FreeGiftDiscount(200.0, "Firmowy Kubek");
        strategy.applyDiscount(products);

        assertEquals(4, products.size());
        Product gift = products.get(3);
        assertTrue(gift.getCode().startsWith("FREE_"));
        assertEquals("Firmowy Kubek", gift.getName());
        assertEquals(0.0, gift.getPrice());
    }
}