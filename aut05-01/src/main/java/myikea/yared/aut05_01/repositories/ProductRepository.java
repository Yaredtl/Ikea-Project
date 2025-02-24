package myikea.yared.aut05_01.repositories;

import myikea.yared.aut05_01.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
