package main.java.com.product.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String Category;
    private Double Price;
    private int quantityStock;
}
