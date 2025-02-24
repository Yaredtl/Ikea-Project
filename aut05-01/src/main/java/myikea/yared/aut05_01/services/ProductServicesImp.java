package myikea.yared.aut05_01.services;

import myikea.yared.aut05_01.models.Product;
import myikea.yared.aut05_01.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Primary
@Service
public class ProductServicesImp implements ProductServices{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> listProduct(){return productRepository.findAll();}
    public Optional<Product> getProduct(int id) { return productRepository.findById(id);}
    public  void createProduct(Product product){ productRepository.save(product);}
    public void deleteProduct(int id){ productRepository.deleteById(id);}
}
