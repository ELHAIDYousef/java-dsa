package com.shoplite;
import java.util.UUID;
public class OrderService {
    private final PaymentGateway paymentGateway;
    private final OrderRepository orderRepository;
    private final PriceCalculator priceCalculator;
    private final NotificationService notifier;
    public OrderService(PaymentGateway paymentGateway,
                        OrderRepository orderRepository,
                        PriceCalculator priceCalculator,
                        NotificationService notifier) {
        this.paymentGateway = paymentGateway;
        this.orderRepository = orderRepository;
        this.priceCalculator = priceCalculator;
        this.notifier = notifier;
    }
    public Order placeOrder(Cart cart, String customerEmail) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cannot order an empty cart");
        }
        double total = priceCalculator.finalPrice(cart.total());
        if (!paymentGateway.charge(total)) {
            throw new IllegalStateException("Payment declined");
        }
        Order order = new Order(UUID.randomUUID().toString(), cart.items(), total);
        orderRepository.save(order);
        try {
            notifier.sendConfirmation(customerEmail,
                    "Order " + order.id() + " confirmed. Total: " + total + " MAD");
        } catch (RuntimeException e) {
// an email failure must NOT cancel a paid order
        }
        return order;
    }
}