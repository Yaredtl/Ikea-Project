package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Checkout;

import java.util.List;
import java.util.Optional;

public interface CheckoutServices {
    List<Checkout> listCheckout();
    Optional<Checkout> getCheckout(int id);
    void createCheckout (Checkout checkout);
    void updateCheckout(Checkout checkout);
    Checkout findByEmail (String email);
    void deleteCheckout(int id);
}
