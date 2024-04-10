package main.java.com.product.repository;

import main.java.com.product.connection.ConnectionBd;

public class ProductRepository {

    private final ConnectionBd connectionBd;
    public ProductRepository(ConnectionBd connectionBd1){

        this.connectionBd = connectionBd1;
    }


}
