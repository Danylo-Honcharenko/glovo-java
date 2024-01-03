package org.coursesjava.glovojava.controller;

import lombok.AllArgsConstructor;
import org.coursesjava.glovojava.model.Order;
import org.coursesjava.glovojava.model.Product;
import org.coursesjava.glovojava.repository.OrderRepository;
import org.coursesjava.glovojava.repository.ProductRepository;
import org.coursesjava.glovojava.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class GlovoController {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private OrderService orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        orderRepository.updateById(id, order);
        return orderService.findOrderById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> addProductToOrder(@PathVariable Long id, @RequestBody Product product) {
        ResponseEntity<Order> response = orderService.findOrderById(id);
        if (response.getStatusCode().isError()) return response;
        product.setOrder(response.getBody());
        productRepository.save(product);
        return response;
    }

    @DeleteMapping("/{id}/product/{name}")
    public ResponseEntity<Order> deleteProduct(@PathVariable Long id, @PathVariable String name) {
        ResponseEntity<Order> response = orderService.findOrderById(id);
        if (response.getStatusCode().isError()) return response;
        productRepository.deleteProductByIdAndName(id, name);
        return response;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
