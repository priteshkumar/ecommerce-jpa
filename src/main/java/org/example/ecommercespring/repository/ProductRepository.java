package org.example.ecommercespring.repository;

import org.example.ecommercespring.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("Select p from Product p WHERE p.price > :minPrice AND p.brand = :brand")
  List<Product> findByBrandAndPrice(@Param("minPrice") int price, @Param("brand") String brandName);

  @Query(
      value = "Select * FROM product WHERE MATCH(title, description) " + "AGAINST (:keyword)",
      nativeQuery = true)
  List<Product> searchFullText(@Param("keyword") String keyword);
}
