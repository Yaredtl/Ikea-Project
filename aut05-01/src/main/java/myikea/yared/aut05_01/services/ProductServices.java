package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductServices {
    List<Product> listProduct ();
    Optional<Product> getProduct(int id);
    void createProduct (Product product);
    void deleteProduct(int id);
}
