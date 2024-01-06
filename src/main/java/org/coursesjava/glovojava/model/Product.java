package org.coursesjava.glovojava.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer cost;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}
