package main.java.com.product.controller;

import main.java.com.product.dto.ProductDTO;
import main.java.com.product.service.ProductService;

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    public void createProduct(String name, String category, float price, int quantityStock) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setCategory(category);
        productDTO.setPrice(price);
        productDTO.setQuantityStock(quantityStock);

        productService.createProduct(productDTO);
    }


    public void updateProduct(int id){
        productService.updateProduct(id);
    }

    public void deleteProduct(int id) {
        productService.deleteProduct(id);
    }

    public void deleteAllProducts() {
        productService.deleteAllProducts();
    }
    public int getRandomProductId() {
        return   productService.getRandomProductId();

    }

}
