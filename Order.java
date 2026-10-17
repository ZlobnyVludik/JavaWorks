import java.util.ArrayList;

class Order {
    private final int id;
    private final Customer customer;
    private final ArrayList<OrderItem> items = new ArrayList<>();
    private long totalInCents;
    private OrderStatus status;

    public Order(int id, Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Покупатель не может быть null");
        }
        this.id = id;
        this.customer = customer;
        this.status = OrderStatus.NEW;
    }

    // Добавление позиции — только пока заказ новый
    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Позиция не может быть null");
        }
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Нельзя изменить заказ в статусе " + status);
        }
        items.add(item);
        totalInCents += item.getSubtotal();
    }

    // Геттеры
    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public ArrayList<OrderItem> getItems() { return items; }
    public long getTotal() { return totalInCents; }
    public OrderStatus getStatus() { return status; }

    public void markAsPaid() {
        if (status != OrderStatus.NEW) {
            throw new IllegalStateException("Нельзя оплатить заказ в статусе " + status);
        }
        status = OrderStatus.PAID;
    }

    public void cancel() {
        if (status == OrderStatus.PAID) {
            throw new IllegalStateException("Нельзя отменить оплаченный заказ");
        }
        if (status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Заказ уже отменён");
        }
        status = OrderStatus.CANCELLED;
    }

    public void printOrder() {
        System.out.println("=== Заказ #" + id + " ===");
        System.out.println("Покупатель: " + customer.getName()
                + " (" + customer.getEmail() + ")");
        System.out.println("Статус: " + status);
        for (OrderItem item : items) {
            System.out.println("  " + item.getProductName()
                    + " x" + item.getQuantity()
                    + " = " + (double) item.getSubtotal() / 100 + " руб.");
        }
        System.out.println("Итого: " + (double) totalInCents / 100 + " руб.");
    }
}
