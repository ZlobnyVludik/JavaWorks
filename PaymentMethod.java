abstract class PaymentMethod {
    public abstract boolean pay(Order order);

    public void printPaymentInfo(Order order) {
        System.out.println("Оплата заказа " + order.getId()
                + " на сумму " + (double) order.getTotal() / 100 + " руб.");
    }
}
