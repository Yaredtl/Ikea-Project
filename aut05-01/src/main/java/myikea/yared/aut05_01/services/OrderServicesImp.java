package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Checkout;
import myikea.yared.aut05_01.models.Order;
import myikea.yared.aut05_01.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class OrderServicesImp implements OrderServices{

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listOrder(){return orderRepository.findAll();}
    public Optional<Order> getOrder(int id) { return orderRepository.findById(id);}
    public  void createOrder(Order order){ orderRepository.save(order);}
    public void deleteOrder(int id){ orderRepository.deleteById(id);}
    public Order findByEmail (String email){return  orderRepository.findByEmail(email);}
}
