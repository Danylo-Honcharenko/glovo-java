package org.coursesjava.glovojava.repository;

import jakarta.transaction.Transactional;
import org.coursesjava.glovojava.model.OrderEntity;
import static org.junit.jupiter.api.Assertions.*;

import org.coursesjava.glovojava.model.ProductEntity;
import org.coursesjava.glovojava.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.yaml")
@ActiveProfiles("test")
@Transactional
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderService orderService;

    @Test
    public void save() {
        OrderEntity order = new OrderEntity();
        order.setId(4);
        order.setCost(0);
        order.setDate(LocalDate.now());
        order.setProducts(null);

        assertEquals(order, orderRepository.save(order));
    }

    @Test
    public void saveNull() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> orderRepository.save(null));
    }

    @Test
    public void findOrderById() {
        assertTrue(orderService.findOrderById(1L).getStatusCode().isSameCodeAs(HttpStatus.OK));
    }

    @Test
    public void notFoundOrderById() {
        assertTrue(orderService.findOrderById(10L).getStatusCode().isSameCodeAs(HttpStatus.NOT_FOUND));
    }

    @Test
    public void findById() {
        assertTrue(orderRepository.findById(1L).isPresent());
    }

    @Test
    public void updateById() {
        List<ProductEntity> products = new ArrayList<>();
        Long id = 4L;
        OrderEntity order = new OrderEntity();
        order.setId(4);
        order.setCost(200);
        order.setDate(LocalDate.now());
        order.setProducts(products);

        orderRepository.updateById(id, order);

        Optional<OrderEntity> foundOrder = orderRepository.findById(id);

        assertTrue(foundOrder.isPresent());
        assertEquals(order, foundOrder.get());
    }

    @Test
    public void addProductToOrder() {
        Optional<OrderEntity> order = orderRepository.findById(1L);
        assertTrue(order.isPresent());

        ProductEntity product = new ProductEntity();
        product.setId(5);
        product.setName("Apple");
        product.setOrder(order.get());

        assertEquals(product, productRepository.save(product));
    }

    @Test
    public void deleteProductFromOrder() {
        List<ProductEntity> products = new ArrayList<>();
        OrderEntity order = new OrderEntity();
        order.setId(2);
        order.setDate(LocalDate.of(2022, 5, 6));
        order.setCost(170);
        order.setProducts(products);

        productRepository.deleteProductByIdAndName(2L, "Beer");

        Optional<OrderEntity> foundOrder = orderRepository.findById(2L);
        assertTrue(foundOrder.isPresent());
        assertEquals(order, foundOrder.get());
    }

    @Test
    public void deleteOrder() {
        orderRepository.deleteById(1L);
        assertTrue(orderRepository.findById(1L).isEmpty());
    }
}
