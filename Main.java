package shop;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> catalog = new ArrayList<>();

        Product laptop = new Product(1, "Ноутбук", 7_500_00, 5);
        Product phone = new Product(2, "Смартфон", 3_500_00, 10);
        Product headphones = new Product(3, "Наушники", 500_00, 20);
        Product mouse = new Product(4, "Мышь", 150_00, 30);
        Product keyboard = new Product(5, "Клавиатура", 250_00, 15);

        catalog.add(laptop);
        catalog.add(phone);
        catalog.add(headphones);
        catalog.add(mouse);
        catalog.add(keyboard);

        Customer ivan = new Customer(1, "Иван", "ivan@example.com");
        Customer anna = new Customer(2, "Анна", "anna@example.com");

        ShoppingCart cart1 = new ShoppingCart();
        cart1.addProduct(laptop, 1);
        cart1.addProduct(phone, 2);
        cart1.addProduct(mouse, 3);
        System.out.println("Корзина Ивана:");
        cart1.printCart();

        ShoppingCart cart2 = new ShoppingCart();
        cart2.addProduct(headphones, 2);
        cart2.addProduct(keyboard, 1);
        cart2.addProduct(phone, 1);
        System.out.println("\nКорзина Анны:");
        cart2.printCart();

        System.out.println("\nИзменяем корзину Ивана:");
        cart1.changeQuantity(2, 1);
        cart1.removeProduct(4);
        cart1.printCart();

        OrderService service = new OrderService(catalog);

        Order order1 = service.createOrder(ivan, cart1);
        Order order2 = service.createOrder(anna, cart2);

        System.out.println("\nОформленные заказы:");
        order1.printOrder();
        System.out.println();
        order2.printOrder();

        System.out.println("\nОплата:");
        PaymentMethod card = new CardPayment();
        PaymentMethod online = new OnlinePayment();

        service.processPayment(order1, card);
        service.processPayment(order2, online);

        System.out.println("\nСтатусы заказов:");
        System.out.println("Заказ " + order1.getId() + ": " + order1.getStatus());
        System.out.println("Заказ " + order2.getId() + ": " + order2.getStatus());

        System.out.println("\nОтмена неоплаченного заказа:");
        ShoppingCart cart3 = new ShoppingCart();
        cart3.addProduct(mouse, 2);
        System.out.println("Остаток мышей до оформления: " + mouse.getStock());
        Order order3 = service.createOrder(ivan, cart3);
        System.out.println("Остаток мышей после оформления: " + mouse.getStock());
        service.cancelOrder(order3);
        System.out.println("Остаток мышей после отмены: " + mouse.getStock());
        System.out.println("Статус заказа " + order3.getId() + ": " + order3.getStatus());

        System.out.println("\nПолиморфизм через коллекцию:");
        ArrayList<PaymentMethod> methods = new ArrayList<>();
        methods.add(new CardPayment());
        methods.add(new CashPayment());
        methods.add(new OnlinePayment());

        for (PaymentMethod method : methods) {
            ShoppingCart tempCart = new ShoppingCart();
            tempCart.addProduct(headphones, 1);
            Order tempOrder = service.createOrder(anna, tempCart);
            System.out.println();
            method.printPaymentInfo(tempOrder);
            service.processPayment(tempOrder, method);
        }

        System.out.println("\nИзменение цены после оформления:");
        System.out.println("Цена клавиатуры до: " + keyboard.getPriceInCents() + " коп.");
        keyboard.changePrice(400_00);
        System.out.println("Цена клавиатуры после: " + keyboard.getPriceInCents() + " коп.");
        System.out.println("Но в заказе " + order2.getId() + " осталась старая цена:");
        for (OrderItem item : order2.getItems()) {
            if (item.getProductId() == 5) {
                System.out.println("  " + item.getProductName()
                        + " по цене " + item.getPriceInCents() + " коп.");
            }
        }

        try {
            ShoppingCart empty = new ShoppingCart();
            service.createOrder(ivan, empty);
        } catch (IllegalStateException e) {
            System.out.println("Пустая корзина: " + e.getMessage());
        }

        try {
            service.processPayment(order1, card);
        } catch (IllegalStateException e) {
            System.out.println("Повторная оплата: " + e.getMessage());
        }

        try {
            service.cancelOrder(order2);
        } catch (IllegalStateException e) {
            System.out.println("Отмена оплаченного: " + e.getMessage());
        }

        try {
            service.cancelOrder(order3);
        } catch (IllegalStateException e) {
            System.out.println("Повторная отмена: " + e.getMessage());
        }

        try {
            ShoppingCart badCart = new ShoppingCart();
            badCart.addProduct(phone, -1);
        } catch (IllegalArgumentException e) {
            System.out.println("Отрицательное количество: " + e.getMessage());
        }

        try {
            ShoppingCart overCart = new ShoppingCart();
            overCart.addProduct(laptop, 100);
        } catch (IllegalStateException e) {
            System.out.println("Нехватка товара: " + e.getMessage());
        }

        System.out.println("Итоговые остатки на складе:");
        for (Product p : catalog) {
            System.out.println("  " + p.getName() + ": " + p.getStock());
        }
    }
}
