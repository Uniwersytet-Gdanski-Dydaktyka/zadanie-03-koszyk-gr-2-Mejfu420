import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {

    public Product findCheapest(List<Product> products) {
        if (products == null || products.isEmpty()) return null;
        return products.stream()
                .min(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
    }

    public Product findMostExpensive(List<Product> products) {
        if (products == null || products.isEmpty()) return null;
        return products.stream()
                .max(Comparator.comparingDouble(Product::getPrice))
                .orElse(null);
    }

    public List<Product> findNCheapest(List<Product> products, int n) {
        if (products == null || products.isEmpty() || n <= 0) return List.of();
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Product> findNMostExpensive(List<Product> products, int n) {
        if (products == null || products.isEmpty() || n <= 0) return List.of();
        return products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }
}