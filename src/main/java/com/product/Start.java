package main.java.com.product;

import main.java.com.product.Factory.ProductFactory;
import main.java.com.product.connection.ConnectionBd;
import main.java.com.product.controller.ProductController;
import main.java.com.product.dictionary.ProductDictionary;
import main.java.com.product.service.ProductService;

public class Start {

    public static void main(String[] args) {

        ConnectionBd connectionBd = new ConnectionBd();
        ProductFactory productFactory = new ProductFactory(connectionBd);
        ProductService productService = new ProductService(productFactory);
        ProductController productController = new ProductController(productService);

        int interruptor = 100;

        while (interruptor > 0) {
            String name = getArrayNames();
            String category = getCategory();
            float price = ProductDictionary.prices();
            int quantityStock = ProductDictionary.quantityStock();
            int id = productController.getRandomProductId();
            productController.createProduct(name, category, price, quantityStock);

            if (interruptor <= 80) {
                productController.updateProduct(id);
            }

            --interruptor;
        }

        productController.deleteAllProducts();
    }


    public static String getArrayNames() {
        int option = (int) (Math.random() * 3);
        String randomProduct = null;

        switch (option) {
            case 0:
                randomProduct = getRandomProduct(ProductDictionary.namesBoardGames);
                break;
            case 1:
                randomProduct = getRandomProduct(ProductDictionary.namesGirlsToys);
                break;
            case 2:
                randomProduct = getRandomProduct(ProductDictionary.namesBoysToys);
                break;
        }

        return randomProduct;
    }

    public static String getCategory() {
        int option = (int) (Math.random() * 3);
        int index = option;
        return ProductDictionary.categories[index];
    }

    private static String getRandomProduct(String[] products) {
        int randomIndex = (int) (Math.random() * products.length);
        return products[randomIndex];
    }
}
