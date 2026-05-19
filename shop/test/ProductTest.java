import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateProductWithValidData() {
        Product product = new Product("P01", "Myszka", 100.0);
        assertEquals("P01", product.getCode());
        assertEquals("Myszka", product.getName());
        assertEquals(100.0, product.getPrice());
        assertEquals(100.0, product.getDiscountPrice());
    }

    @Test
    void shouldThrowExceptionWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Product("P02", "Klawiatura", -50.0));
    }

    @Test
    void shouldResetDiscountToOriginalPrice() {
        Product product = new Product("P03", "Monitor", 500.0);
        product.setDiscountPrice(400.0);
        assertEquals(400.0, product.getDiscountPrice());

        product.resetDiscount();
        assertEquals(500.0, product.getDiscountPrice());
    }

    @Test
    void shouldCloneProductCorrectly() {
        Product original = new Product("P04", "Kabel", 20.0);
        original.setDiscountPrice(15.0);

        Product clone = original.cloneProduct();
        assertEquals(original.getCode(), clone.getCode());
        assertEquals(original.getPrice(), clone.getPrice());
        assertEquals(original.getDiscountPrice(), clone.getDiscountPrice());
        assertNotSame(original, clone);
    }
}