package shop

class Product {
    private final int id;
    private String name;
    private long priceInCents;
    private int stock;

    public Product(int id, String name, long priceInCents, int stock) {
        if (name == null) {
            throw new IllegalArgumentException("Название не может быть пустым.");
        }

        if (priceInCents < 0) {
            throw new IllegalArgumentException("Цена не может быть отрицательной.");
        }

        if (stock < 0) {
            throw new IllegalArgumentException("Остаток не может быть отрицательным.");
        }

        this.id = id;
        this.name = name;
        this.priceInCents = priceInCents;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPriceInCents() {
        return priceInCents;
    }

    public int getStock() {
        return stock;
    }

    public void increaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кол-во для увелечния должно быть положительным.");
        }
        this.stock += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Кол-во для уменьшения должно быть положительным.");
        }

        if (quantity > stock) {
            throw new IllegalArgumentException("Недостаточно товара на складе. Доступно: " + stock);
        }
        stock -= quantity;
    }

    public boolean isAvailable(int quantity) {
        return quantity > 0 && quantity <= stock;
    }

    public String getFormattedPrice() {
        return (double) priceInCents / 100 + " руб.";
    }
}
