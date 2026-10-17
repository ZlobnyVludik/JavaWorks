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

    public long getSubtotal() {
        return product.getPriceInCents() * quantity;
    }

    public void increaseQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Должно быть положительным."); 
        }
        this.quantity += quantity;
    }

    public void decreaseQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Должно быть положительным.");
        }
        if (quantity >= this.quantity) {
            throw new IllegalArgumentException("Нельзя уменьшить. Сейчас: " + this.quantity);
        }
        this.quantity -= quantity;
    }

}
