class OrderItem {
    private final int productId;
    private final String productName;
    private final long priceInCents;
    private final int quantity;
    
    public OrderItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Не может быть пустым.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Должно быть положительным.");
        }

        this.productId = product.getId();
        this.productName = product.getName();
        this.priceInCents = product.getPriceInCents();
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public long getPriceInCents() {
        return priceInCents;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getSubtotal() {
        return priceInCents * quantity;
    }
}
