import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Cart {
    private final List<Product> products = new ArrayList<>();
    private final List<DiscountStrategy> activeDiscounts = new ArrayList<>();

    public void addProduct(Product product) {
        if (product != null) products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public void addDiscount(DiscountStrategy discount) {
        if (discount != null) activeDiscounts.add(discount);
    }

    public void clearDiscounts() {
        activeDiscounts.clear();
    }

    public void sortProducts(Comparator<Product> comparator) {
        if (comparator != null) {
            products.sort(comparator);
        }
    }

    public double calculateTotalOriginalPrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public double calculateTotalDiscountedPrice() {
        return products.stream()
                .mapToDouble(Product::getDiscountPrice)
                .sum();
    }

    public void applyDiscounts() {
        products.forEach(Product::resetDiscount);
        products.removeIf(p -> p.getPrice() == 0.0 && p.getCode().startsWith("FREE_"));

        if (products.isEmpty()) return;

        for (DiscountStrategy strategy : activeDiscounts) {
            strategy.applyDiscount(products);
        }
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }
}