package main.java.com.product.service;

import main.java.com.product.Factory.ProductFactory;
import main.java.com.product.dto.ProductDTO;

public class ProductService {

    private final ProductFactory productFactory;

    public ProductService(ProductFactory productFactory) {

        this.productFactory = productFactory;
    }


    public void createProduct(ProductDTO productDTO) {

        productFactory.createProduct(productDTO);
    }

    public void updateProduct(int id) {
        productFactory.updateProduct(id);
    }

    public void deleteProduct(int id) {
        productFactory.deleteProduct(id);
    }

    public void deleteAllProducts() {
        productFactory.deleteAllProducts();
    }

    public int getRandomProductId() {
      return productFactory.getRandomProductId();
    }

}
