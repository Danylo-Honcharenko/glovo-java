package org.coursesjava.glovojava.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class Product {
    private Integer id;
    private String name;
    private Integer cost;
}
