import java.util.ArrayList;

class OrderService {
    private final ArrayList<Product> catalog;
    private int nextOrderId = 1;

    public OrderService(ArrayList<Product> catalog) {
        if (catalog == null) {
            throw new IllegalArgumentException("Каталог не может быть пустым");
        }
        this.catalog = catalog;
    }

    private Product findProduct(int productId) {
        for (Product product : catalog) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }

    public Order createOrder(Customer customer, ShoppingCart cart) {
        if (customer == null) {
            throw new IllegalArgumentException("Покупатель не может быть пустым");
        }
        if (cart == null) {
            throw new IllegalArgumentException("Корзина не может быть null");
        }
        if (cart.getItems().isEmpty()) {
            throw new IllegalStateException("Корзина пуста");
        }

        for (CartItem item : cart.getItems()) {
            if (!item.getProduct().isAvailable(item.getQuantity())) {
                throw new IllegalStateException("Недостаточно товара.");
            }
        }

        Order order = new Order(nextOrderId++, customer);

        for (CartItem item : cart.getItems()) {
            order.addItem(new OrderItem(item.getProduct(), item.getQuantity()));
        }

        for (CartItem item : cart.getItems()) {
            item.getProduct().decreaseStock(item.getQuantity());
        }

        cart.clear();
        return order;
    }

    public boolean processPayment(Order order, PaymentMethod paymentMethod) {
        if (order == null) {
            throw new IllegalArgumentException("Заказ не может быть пустым");
        }
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Способ оплаты не может быть никаким");
        }
        if (order.getStatus() != OrderStatus.NEW) {
            throw new IllegalStateException("Нельзя оплатить заказ в статусе " + order.getStatus());
        }

        boolean success = paymentMethod.pay(order);

        if (success) {
            order.markAsPaid();
        }
        return success;
    }

    public void cancelOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Заказ не может быть пустым");
        }
        if (order.getStatus() != OrderStatus.NEW) {
            throw new IllegalStateException("Нельзя отменить заказ в статусе " + order.getStatus());
        }

        for (OrderItem item : order.getItems()) {
            Product product = findProduct(item.getProductId());
            if (product == null) {
                throw new IllegalStateException("Товар с id" + item.getProductId() + " не найден в каталоге");
            }
            product.increaseStock(item.getQuantity());
        }

        order.cancel();
    }
}
