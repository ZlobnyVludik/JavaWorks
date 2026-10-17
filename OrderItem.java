class OrderItem {
    private finale int productId;
    private finale String productName;
    private finale long priceInCents;
    private finale int quantity
    
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

    public int get(){
        return productId;
    }

    public String get(){
        return productName;
    }

    public long get(){
        return priceInCents;
    }

    public int get(){
        return quantity;
    }

    public long getSubtotal() {
        return priceInCents * quantity;
    }
}
