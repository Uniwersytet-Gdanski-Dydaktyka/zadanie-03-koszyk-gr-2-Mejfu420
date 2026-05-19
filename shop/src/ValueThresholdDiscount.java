import java.util.List;

public class ValueThresholdDiscount implements DiscountStrategy {
    private final double threshold;
    private final double discountPercentage;

    public ValueThresholdDiscount(double threshold, double discountPercentage) {
        this.threshold = threshold;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public void applyDiscount(List<Product> products) {
        double currentTotal = products.stream().mapToDouble(Product::getDiscountPrice).sum();
        if (currentTotal > threshold) {
            for (Product p : products) {
                p.setDiscountPrice(p.getDiscountPrice() * (1.0 - discountPercentage));
            }
        }
    }
}