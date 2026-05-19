import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiscountOptimizer {

    public List<DiscountStrategy> findBestDiscountOrder(List<Product> products, List<DiscountStrategy> discounts) {
        if (discounts == null || discounts.isEmpty()) return discounts;

        List<List<DiscountStrategy>> allPermutations = new ArrayList<>();
        generatePermutations(new ArrayList<>(discounts), 0, allPermutations);

        List<DiscountStrategy> bestOrder = null;
        double lowestTotal = Double.MAX_VALUE;

        for (List<DiscountStrategy> order : allPermutations) {
            double currentTotal = evaluateDiscountOrder(products, order);
            if (currentTotal < lowestTotal) {
                lowestTotal = currentTotal;
                bestOrder = order;
            }
        }
        return bestOrder;
    }

    private double evaluateDiscountOrder(List<Product> products, List<DiscountStrategy> order) {
        List<Product> clonedProducts = new ArrayList<>();
        for (Product p : products) {
            clonedProducts.add(p.cloneProduct());
        }

        for (DiscountStrategy strategy : order) {
            strategy.applyDiscount(clonedProducts);
        }

        return clonedProducts.stream().mapToDouble(Product::getDiscountPrice).sum();
    }

    private void generatePermutations(List<DiscountStrategy> arr, int k, List<List<DiscountStrategy>> result) {
        if (k == arr.size()) {
            result.add(new ArrayList<>(arr));
            return;
        }
        for (int i = k; i < arr.size(); i++) {
            Collections.swap(arr, i, k);
            generatePermutations(arr, k + 1, result);
            Collections.swap(arr, k, i);
        }
    }
}