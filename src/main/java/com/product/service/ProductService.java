package main.java.com.product.service;

import main.java.com.product.Statement.ProductStatement;
import main.java.com.product.dto.ProductDTO;

public class ProductService {

    private final ProductStatement productStatement;

    public ProductService(ProductStatement productStatement) {

        this.productStatement = productStatement;
    }


    public void createProduct(ProductDTO productDTO) {

        productStatement.createProduct(productDTO);
    }

    public void updateProduct(int id) {
        productStatement.updateProduct(id);
    }

    public void deleteProduct(int id) {
        productStatement.deleteProduct(id);
    }

    public void deleteAllProducts() {
        productStatement.deleteAllProducts();
    }

    public int getRandomProductId() {
      return productStatement.getRandomProductId();
    }

}
