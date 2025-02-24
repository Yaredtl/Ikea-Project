package myikea.yared.aut05_01.controllers;

import myikea.yared.aut05_01.models.Checkout;
import myikea.yared.aut05_01.models.Product;
import myikea.yared.aut05_01.services.CheckoutServices;
import myikea.yared.aut05_01.services.ProductServices;
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

import java.util.ArrayList;
import java.util.Optional;

@Controller
@PreAuthorize("hasRole('ADMIN')")
public class CheckoutController {
    @Autowired
    private CheckoutServices checkoutServices;

    @Autowired
    private ProductServices productServices;


    @GetMapping("/Checkout")
    public String listCheckoutUrl(Model model, @AuthenticationPrincipal UserDetails loggedUser){
        Checkout checkoutBuscado = checkoutServices.findByEmail(loggedUser.getUsername());
        Float precioFinal = sumProducts(checkoutBuscado);
        model.addAttribute("checkout", (checkoutBuscado != null) ? checkoutBuscado : new Checkout());
        model.addAttribute("precioFinal", precioFinal);
        return "Checkout/list";
    }


    @PostMapping("/Checkout")
    public String listCheckout(@RequestParam("productId") int productId, Model model, @AuthenticationPrincipal UserDetails loggedUser) {
        Optional<Product> optionalProducto = productServices.getProduct(productId);
        if (!optionalProducto.isPresent()) {
            return "redirect:/";
        }

        Product productoElegido = optionalProducto.get();
        Checkout checkoutBuscado = checkoutServices.findByEmail(loggedUser.getUsername());

        if (checkoutBuscado == null) {
            checkoutBuscado = new Checkout();
            checkoutBuscado.setUserEmail(loggedUser.getUsername());
        }

        if (checkoutBuscado.getProducts() == null) {
            checkoutBuscado.setProducts(new ArrayList<>());
        }
        checkoutBuscado.addProduct(productoElegido);
        checkoutServices.updateCheckout(checkoutBuscado);
        Float precioFinal = sumProducts(checkoutBuscado);
        model.addAttribute("checkout", checkoutBuscado);
        model.addAttribute("precioFinal", precioFinal);
        return "Checkout/list";
    }

    @PostMapping("/Checkout/delete/{id}")
    public String deleteProductCheckout(@PathVariable("id") int id, Model model, @AuthenticationPrincipal UserDetails loggedUser){
        Checkout checkoutBuscado = checkoutServices.findByEmail(loggedUser.getUsername());
        Product product = checkoutBuscado.getProducts().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (product != null){
            checkoutBuscado.getProducts().remove(product);
            checkoutServices.updateCheckout(checkoutBuscado);
        }
        return "redirect:/Checkout";
    }

    public Float sumProducts(Checkout checkout){
        Float precio = 0.0F;
        if (checkout == null){
            return 0.0F;
        }else{
            for (int i = 0; i < checkout.getProducts().size(); i++){
                precio += checkout.getProducts().get(i).getPrice();
            }
            return precio;
        }
    }
}


