package main.java.com.product.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBd {

    public static void main(String[] args) {
        Connection connection = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/sales_inventory";
            String username ="root";
            String password = "";

            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión exitosa con la base de datos.");

        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se pudo cargar el controlador de MySQL.");

        } catch (SQLException e) {
            System.out.println("Error: No se pudo establecer la conexión con la base de datos.");

        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Conexión cerrada.");
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión.");

                }
            }
        }



    }

}
