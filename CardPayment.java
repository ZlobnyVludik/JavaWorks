class CardPayment extends PaymentMethod {
    @Override
    public boolean pay(Order order) {
        System.out.println("Оплата прошла успешно.);
        return true;
    }
}
