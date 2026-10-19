class CashPayment extends PaymentMethod {
    @Override
    public boolean pay(Order order) {
        System.out.println("Наличные приняты.");
        return true;
    }
}
