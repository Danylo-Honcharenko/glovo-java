package org.coursesjava.glovojava.repository;

import jakarta.transaction.Transactional;
import org.coursesjava.glovojava.model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @Modifying
    @Transactional
    // :#{#order.cost} - SpEL
    @Query("UPDATE Order o SET o.cost = :#{#order.cost}, o.date = :#{#order.date} WHERE o.id = :id")
    void updateById(@Param("id") Long id, @Param("order") Order order);
}
