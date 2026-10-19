class OnlinePayment extends PaymentMethod {
    @Override
    public boolean pay(Order order) {
        System.out.println("Платёж выполнен.");
        return true;
    }
}
