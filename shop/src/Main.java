public static void main(String[] args) {
    Cart cart = new Cart();
    cart.addProduct(new Product("01", "Klawiatura", 150.0));
    cart.addProduct(new Product("02", "Myszka", 80.0));
    cart.addProduct(new Product("03", "Monitor", 600.0));

    Comparator<Product> myComparator = Comparator
            .comparingDouble(Product::getPrice).reversed()
            .thenComparing(Product::getName);
    cart.sortProducts(myComparator);
}