package org.coursesjava.glovojava.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
public class Order {
    private Integer id;
    private LocalDate date;
    private Integer cost;
    private List<Product> products;
}
