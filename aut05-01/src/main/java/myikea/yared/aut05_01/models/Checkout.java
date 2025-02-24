package myikea.yared.aut05_01.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


import java.util.List;

@Entity
public class Checkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY PARA QUE LAS ID SE CREEN BIEN NO ALEATORIAS
    private int id;
    @NotNull
    private String email;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name = "checkout_products",
            joinColumns = @JoinColumn(name = "checkout_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public void addProduct(Product product) {
        this.products.add(product);
        product.getCheckouts().add(this); // Ensuring bidirectional consistency
    }

    public Checkout(){}

    public Checkout(int id, List<Product> products, String email) {
        this.id = id;
        this.products = products;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return email;
    }

    public void setUserEmail(String email) {
        this.email = email;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
