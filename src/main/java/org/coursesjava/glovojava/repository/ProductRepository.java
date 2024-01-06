package org.coursesjava.glovojava.repository;

import jakarta.transaction.Transactional;
import org.coursesjava.glovojava.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.order.id = :id AND p.name = :name")
    void deleteProductByIdAndName(Long id, String name);
}
