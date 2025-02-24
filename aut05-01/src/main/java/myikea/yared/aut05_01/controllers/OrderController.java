package myikea.yared.aut05_01.controllers;

import ch.qos.logback.core.encoder.EchoEncoder;
import myikea.yared.aut05_01.models.Checkout;
import myikea.yared.aut05_01.models.Order;
import myikea.yared.aut05_01.models.Product;
import myikea.yared.aut05_01.models.User;
import myikea.yared.aut05_01.repositories.OrderRepository;
import myikea.yared.aut05_01.repositories.UserRepository;
import myikea.yared.aut05_01.services.CheckoutServices;
import myikea.yared.aut05_01.services.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class OrderController {
    @Autowired
    private OrderServices orderServices;
    @Autowired
    private CheckoutServices checkoutServices;

    @GetMapping("/Orders")
    public String ListOrdersUrl(Model model, @AuthenticationPrincipal UserDetails loggedUser) {
        model.addAttribute("Pedidos", orderServices.listOrder().stream().filter(c -> c.getUserEmail().equals(loggedUser.getUsername())).toList());
        return "Orders/list";
    }

    @PostMapping("/Orders")
    public String listOrders(@RequestParam("checkoutId") int checkoutId, @RequestParam("precioFinal") float precioFinal, Model model, @AuthenticationPrincipal UserDetails loggedUser) {
        //CONSEGUIMOS EL LOS CHECKOUTS CREADOS CON ESE ID
        Optional<Checkout> opcionalCheckout = checkoutServices.getCheckout(checkoutId);
        if (!opcionalCheckout.isPresent()) {
            return "redirect:/";
        }
        //GET PARA CONSEGUIR EL OBJETO YA QUE ES UN OPTIONAL
        Order newOrder = new Order();
        newOrder.setUserEmail(loggedUser.getUsername());
        newOrder.setDate(new java.sql.Date(System.currentTimeMillis()));
        newOrder.setPrice(precioFinal);
        newOrder.setProducts(new ArrayList<>(opcionalCheckout.get().getProducts()));
        orderServices.createOrder(newOrder);
        opcionalCheckout.get().setProducts(new ArrayList<>());
        checkoutServices.updateCheckout(opcionalCheckout.get());
        List<Order> listaOrders = orderServices.listOrder().stream().filter(o -> o.getUserEmail().equals(loggedUser.getUsername())).toList();
        model.addAttribute("pedidos", listaOrders);
        return "redirect:/Orders";
    }

    @GetMapping("/Orders/details/{id}")
    public String detailsOrders(@PathVariable int id, Model model) {
        Optional<Order> orderBuscado = orderServices.getOrder(id);
        if (!orderBuscado.isPresent()){
            return "redirect:/";
        }
        List<Product> products = orderBuscado.get().getProducts();
        model.addAttribute("productos",products);
        model.addAttribute("precioTotal", orderBuscado.get().getPrice());
        model.addAttribute("date", orderBuscado.get().getDate());
        return "Orders/details";
    }
}
