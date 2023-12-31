package org.coursesjava.glovojava.controller;

import lombok.AllArgsConstructor;
import org.coursesjava.glovojava.model.Order;
import org.coursesjava.glovojava.model.Product;
import org.coursesjava.glovojava.service.OrderService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class GlovoController {
    private OrderService orderService;

    @PostMapping("/create")
    public Order createOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable int id, @RequestBody Order order) {
        return orderService.updateOrder(id, order);
    }

    @PatchMapping("/{id}")
    public Order addProduct(@PathVariable int id, @RequestBody Product product) {
        return orderService.addProduct(id, product);
    }

    @DeleteMapping("/{id}/products/{name}")
    public Order deleteProduct(@PathVariable int id, @PathVariable String name) {
        return orderService.deleteProduct(id, name);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
    }
}
