package org.coursesjava.glovojava.service;

import lombok.AllArgsConstructor;
import org.coursesjava.glovojava.model.Order;
import org.coursesjava.glovojava.repository.OrderRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public ResponseEntity<Order> findOrder(Long id) {
        return orderRepository.findById(id)
                .map(o -> ResponseEntity.ok().body(o))
                .orElse(ResponseEntity.notFound().build());
    }
}
