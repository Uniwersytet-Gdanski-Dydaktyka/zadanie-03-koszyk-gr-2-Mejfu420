import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BuyTwoGetOneFreeDiscount implements DiscountStrategy {
    @Override
    public void applyDiscount(List<Product> products) {
        List<Product> sortedProducts = products.stream()
                .filter(p -> p.getDiscountPrice() > 0)
                .sorted(Comparator.comparingDouble(Product::getDiscountPrice))
                .collect(Collectors.toList());

        int freeItemsCount = sortedProducts.size() / 3;
        for (int i = 0; i < freeItemsCount; i++) {
            sortedProducts.get(i).setDiscountPrice(0.0);
        }
    }
}