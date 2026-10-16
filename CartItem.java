package shop

class CartItem {
    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Продукт не может быть пустым.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Кол-во должно быть положительным.");
        }

        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

}