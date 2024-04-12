package main.java.com.product.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private float price;
    private int quantityStock;
}
