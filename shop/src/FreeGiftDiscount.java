import java.util.List;

public class FreeGiftDiscount implements DiscountStrategy {
    private final double threshold;
    private final String giftName;

    public FreeGiftDiscount(double threshold, String giftName) {
        this.threshold = threshold;
        this.giftName = giftName;
    }

    @Override
    public void applyDiscount(List<Product> products) {
        double currentTotal = products.stream().mapToDouble(Product::getDiscountPrice).sum();
        if (currentTotal > threshold) {
            products.add(new Product("FREE_GIFT", giftName, 0.0));
        }
    }
}