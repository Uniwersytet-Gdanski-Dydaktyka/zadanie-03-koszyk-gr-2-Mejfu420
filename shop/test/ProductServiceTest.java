import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    private ProductService service;
    private List<Product> products;

    @BeforeEach
    void setUp() {
        service = new ProductService();
        products = List.of(
                new Product("P1", "Tani", 10.0),
                new Product("P2", "Sredni", 50.0),
                new Product("P3", "Drogi", 100.0)
        );
    }

    @Test
    void shouldFindCheapestProduct() {
        Product cheapest = service.findCheapest(products);
        assertNotNull(cheapest);
        assertEquals("P1", cheapest.getCode());
    }

    @Test
    void shouldReturnNullWhenListIsEmpty() {
        assertNull(service.findCheapest(new ArrayList<>()));
        assertNull(service.findMostExpensive(null));
    }

    @Test
    void shouldFindNMostExpensiveProducts() {
        List<Product> expensive = service.findNMostExpensive(products, 2);
        assertEquals(2, expensive.size());
        assertEquals("P3", expensive.get(0).getCode());
        assertEquals("P2", expensive.get(1).getCode());
    }
}