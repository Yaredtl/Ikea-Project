package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Checkout;
import myikea.yared.aut05_01.repositories.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class CheckoutServicesImp implements CheckoutServices {

    @Autowired
    private CheckoutRepository checkoutRepository;

    public List<Checkout> listCheckout(){return checkoutRepository.findAll();}
    public Optional<Checkout> getCheckout(int id) { return checkoutRepository.findById(id);}
    public void updateCheckout(Checkout checkout){checkoutRepository.save(checkout);}
    public  void createCheckout(Checkout checkout){ checkoutRepository.save(checkout);}
    public void deleteCheckout(int id){ checkoutRepository.deleteById(id);}
    public Checkout findByEmail (String email){return  checkoutRepository.findByEmail(email);}
}
