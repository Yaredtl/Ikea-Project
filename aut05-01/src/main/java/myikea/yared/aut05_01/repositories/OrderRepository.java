package myikea.yared.aut05_01.repositories;

import myikea.yared.aut05_01.models.Checkout;
import myikea.yared.aut05_01.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer> {
  Order findByEmail(String email);
}
