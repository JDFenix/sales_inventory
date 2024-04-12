package main.java.com.product.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBd {

    public Connection getConnection() {

        Connection connection = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/sales_inventory";
            String username = "root";
            String password = "";

            connection = DriverManager.getConnection(url, username, password);
          //  System.out.println("Conexión correcta con la bd");


        } catch (SQLException e) {
            System.out.println("Error: No se pudo establecer la conexión con la base de datos.");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }


}

