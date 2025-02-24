package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Checkout;
import myikea.yared.aut05_01.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderServices {
    List<Order> listOrder ();
    Optional<Order> getOrder(int id);
    void createOrder (Order order);
    void deleteOrder(int id);
    Order findByEmail (String email);
}
