package main.java.com.product;

import main.java.com.product.Statement.ProductStatement;
import main.java.com.product.connection.ConnectionBd;
import main.java.com.product.controller.ProductController;
import main.java.com.product.dictionary.ProductDictionary;
import main.java.com.product.service.ProductService;

public class start {

    public static void main(String[] args) {

        ConnectionBd connectionBd = new ConnectionBd();
        ProductStatement productStatement = new ProductStatement(connectionBd);
        ProductService productService = new ProductService(productStatement);
        ProductController productController = new ProductController(productService);


        int interruptor = 25;

        while (interruptor > 0) {
            String name = ProductDictionary.namesBoardGames[(int) (Math.random() * ProductDictionary.namesBoardGames.length) + 1];
            String category = ProductDictionary.categories[(int) (Math.random() * ProductDictionary.categories.length)];
            float price = ProductDictionary.prices();
            int quantityStock = ProductDictionary.quantityStock();
            int id = productController.getRandomProductId();


            productController.createProduct(name, category, price, quantityStock);

            if (interruptor<=10){
                productController.updateProduct(id);
            }


            --interruptor;
        }
        productController.deleteAllProducts();

    }
}
