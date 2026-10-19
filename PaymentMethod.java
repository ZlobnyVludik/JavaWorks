abstract class PaymentMethod {
    public abstract boolean pay(Order order);

    public void printPaymentInfo(Order order) {
        System.out.println("Оплата заказа " + order.getId()
                + " на сумму " + (double) order.getTotal() / 100 + " руб.");
    }
}

class OnlinePayment extends PaymentMethod {
    @Override
    public boolean pay(Order order) {
        System.out.println("Платёж выполнен.");
        return true;
    }
}
