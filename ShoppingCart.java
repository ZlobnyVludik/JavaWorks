package shop
import java.util.ArrayList;

class ShoppingCart {
    private final ArrayList<CartItem> items = new ArrayList<>();

    public void addProduct(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Не может быть пустой товар.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Положительным должно быть.");
        }

        for (CartItem item : items) {
            (item.getProduct().getId() == product.getId()) {
                int newQuantity = item.getQuantity() + quantity;
                if (!product.isAvailable(newQuantity)) {
                    throw new IllegalArgumentException("Недостаточно на складе.");
                }
                item.increaseQuantity(quantity);
                System.out.println("Товар " + product.getName() + " добавлен.")
                return;
            }
        }

        if (!product.isAvailable(quantity)) {
            throw new IllegalArgumentException("Недостаточно на складе.");
        }
        items.add(new CartItem(product, quantity));
        System.out.println("Товар " + product.getName() + " добавлен в корзину.");
    }

    public void removeProduct(int productId) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getProduct().getId() == productId) {
                System.out.println("Товар удалён.");
                items.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Товар не найден.");
    }

    public void changeQuantity(int productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Положительным должно быть.");
        }
    }

    public long getTotal() {
        long total = 0;
        for (CartItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public void printCart() {
        System.out.println("Корзина: ");
        for ()
    }
}
