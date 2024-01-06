package org.coursesjava.glovojava.controller;

import lombok.AllArgsConstructor;
import org.coursesjava.glovojava.model.OrderEntity;
import org.coursesjava.glovojava.model.Product;
import org.coursesjava.glovojava.repository.OrderRepository;
import org.coursesjava.glovojava.repository.ProductRepository;
import org.coursesjava.glovojava.service.OrderService;
import org.coursesjava.glovojava.utilit.ResponseHandler;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity order) {
        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ResponseEntity<OrderEntity> response = orderService.findOrderById(id);
        if (response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) return ResponseHandler.response("Order not found!", HttpStatus.NOT_FOUND);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderEntity> updateOrder(@PathVariable Long id, @RequestBody OrderEntity order) {
        orderRepository.updateById(id, order);
        return orderService.findOrderById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> addProductToOrder(@PathVariable Long id, @RequestBody Product product) {
        ResponseEntity<OrderEntity> response = orderService.findOrderById(id);
        if (response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) return ResponseHandler.response("Order not found!", HttpStatus.NOT_FOUND);
        product.setOrder(response.getBody());
        productRepository.save(product);
        return response;
    }

    @DeleteMapping("/{id}/product/{name}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id, @PathVariable String name) {
        ResponseEntity<OrderEntity> response = orderService.findOrderById(id);
        if (response.getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND)) return ResponseHandler.response("Order not found!", HttpStatus.NOT_FOUND);
        productRepository.deleteProductByIdAndName(id, name);
        return response;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
    }
}
