package myikea.yared.aut05_01.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name="productoffer")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int id;

    @NotNull
    @Column(name="product_name")
    private String name;

    @NotNull
    @Column(name="product_price")
    private float price;

    @Column(name="product_picture")
    private String image;

    @NotNull
    @Column(name="id_municipio")
    private int idMunicipio;

    @NotNull
    @Column(name="product_stock")
    private int stock;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Order> orders;


    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<Checkout> checkouts;

    public void addCheckout(Checkout checkout) {
        this.checkouts.add(checkout);
        checkout.getProducts().add(this); // Ensuring bidirectional consistency
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Checkout> getCheckouts() {
        return checkouts;
    }

    public void setCheckouts(List<Checkout> checkouts) {
        this.checkouts = checkouts;
    }
    public Product(){}
    public Product(int id, int stock, int idMunicipio, String image, float price, String name) {
        this.id = id;
        this.stock = stock;
        this.idMunicipio = idMunicipio;
        this.image = image;
        this.price = price;
        this.name = name;
    }
}

