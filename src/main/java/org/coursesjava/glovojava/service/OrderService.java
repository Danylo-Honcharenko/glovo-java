package org.coursesjava.glovojava.service;

import org.coursesjava.glovojava.model.Order;
import org.coursesjava.glovojava.model.Product;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final Map<Integer, Order> orders = new HashMap<>();

    public Order save(Order order) {
        this.orders.put(order.getId(), order);
        return order;
    }

    public Order getById(int id) {
        return orders.get(id);
    }

    public Order updateOrder(int id, Order order) {
        orders.replace(id, order);
        return order;
    }

    public Order addProduct(int id, Product product) {
        Order order = orders.get(id);
        order.getProducts().add(product);
        return order;
    }

    public Order deleteProduct(int orderId, String productName) {
        Order order = orders.get(orderId);
        List<Product> products = order
                .getProducts()
                .stream()
                .filter(p -> !p.getName().equals(productName))
                .toList();
        order.setProducts(products);
        return order;
    }

    public void deleteOrder(int id) {
        orders.remove(id);
    }
}
