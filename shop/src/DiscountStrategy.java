import java.util.List;

public interface DiscountStrategy {
    void applyDiscount(List<Product> products);
}