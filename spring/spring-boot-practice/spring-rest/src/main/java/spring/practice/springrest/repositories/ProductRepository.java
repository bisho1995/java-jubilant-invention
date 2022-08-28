package spring.practice.springrest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.practice.springrest.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
