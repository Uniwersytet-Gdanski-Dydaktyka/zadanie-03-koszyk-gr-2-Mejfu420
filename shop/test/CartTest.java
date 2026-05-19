import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    private Cart cart;
    private Product p1;
    private Product p2;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        p1 = new Product("P01", "A-Myszka", 150.0);
        p2 = new Product("P02", "B-Klawiatura", 250.0);
    }

    @Test
    void shouldAddAndRemoveProducts() {
        cart.addProduct(p1);
        cart.addProduct(p2);
        assertEquals(2, cart.getProducts().size());

        cart.removeProduct(p1);
        assertEquals(1, cart.getProducts().size());
        assertEquals("P02", cart.getProducts().get(0).getCode());
    }

    @Test
    void shouldCalculateTotalOriginalPrice() {
        cart.addProduct(p1);
        cart.addProduct(p2);
        assertEquals(400.0, cart.calculateTotalOriginalPrice());
    }

    @Test
    void shouldSortProductsByPriceDescending() {
        cart.addProduct(p1);
        cart.addProduct(p2);

        Comparator<Product> descendingPrice = Comparator.comparingDouble(Product::getPrice).reversed();
        cart.sortProducts(descendingPrice);

        List<Product> products = cart.getProducts();
        assertEquals("P02", products.get(0).getCode());
        assertEquals("P01", products.get(1).getCode());
    }
}