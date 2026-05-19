import java.util.Objects;

public class Product {
    private final String code;
    private final String name;
    private final double price;
    private double discountPrice;

    public Product(String code, String name, double price) {
        if (price < 0) throw new IllegalArgumentException("Cena nie może być ujemna");
        this.code = Objects.requireNonNull(code);
        this.name = Objects.requireNonNull(name);
        this.price = price;
        this.discountPrice = price;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getDiscountPrice() { return discountPrice; }

    public void setDiscountPrice(double discountPrice) {
        if (discountPrice < 0) throw new IllegalArgumentException("Cena po zniżce nie może być ujemna");
        this.discountPrice = discountPrice;
    }

    public void resetDiscount() {
        this.discountPrice = this.price;
    }

    public Product cloneProduct() {
        Product clone = new Product(this.code, this.name, this.price);
        clone.setDiscountPrice(this.discountPrice);
        return clone;
    }

    @Override
    public String toString() {
        return name + " (" + code + ") - Cena: " + price + " (Po zniżce: " + discountPrice + ")";
    }
}