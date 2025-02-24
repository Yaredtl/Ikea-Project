package myikea.yared.aut05_01.repositories;

import myikea.yared.aut05_01.models.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {
    Checkout findByEmail(String email);
}
