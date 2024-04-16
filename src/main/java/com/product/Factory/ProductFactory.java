package main.java.com.product.Factory;

import main.java.com.product.connection.ConnectionBd;
import main.java.com.product.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ProductFactory {

    private final ConnectionBd connectionBd;

    public ProductFactory(ConnectionBd connectionBd) {
        this.connectionBd = connectionBd;
    }


    public void createProduct(ProductDTO productDTO) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;



        try {
            String sql = "INSERT INTO product(name,category,price,quantity_stock) VALUES(?,?,?,?)";

            connection = this.connectionBd.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, productDTO.getName());
            preparedStatement.setString(2, productDTO.getCategory());
            preparedStatement.setFloat(3, productDTO.getPrice());
            preparedStatement.setInt(4, productDTO.getQuantityStock());


            preparedStatement.executeUpdate();
            System.out.println("producto creado llamado: " + productDTO.getName() + ", de la categoria: " + productDTO.getCategory() + " de costo " + productDTO.getPrice() + " con una cantidad de " + productDTO.getQuantityStock());

        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }


    public void updateProduct(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String selectSql = "SELECT name, quantity_stock FROM product WHERE id = ?";
            String updateSql = "UPDATE product SET quantity_stock = ? WHERE id = ?";

            connection = this.connectionBd.getConnection();
            preparedStatement = connection.prepareStatement(selectSql);

            preparedStatement.setInt(1, id);

            int currentStock = 0;
            String productName = null;

            var product = preparedStatement.executeQuery();
            if (product.next()) {
                currentStock = product.getInt("quantity_stock");
                productName = product.getString("name");
            }

            if (currentStock > 0) {
                preparedStatement = connection.prepareStatement(updateSql);
                preparedStatement.setInt(1, currentStock - (int) (Math.random() * 10));
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();

                System.out.println("Stock actualizado del producto " + productName + " con Stock actual de " + currentStock);
            } else {
                deleteProduct(id);
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
        }
    }


    public void deleteProduct(int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String selectSql = "SELECT name,id from product WHERE id = ?";
            String deleteSql = "DELETE FROM product WHERE id = ?";

            connection = this.connectionBd.getConnection();
            preparedStatement = connection.prepareStatement(selectSql);

            preparedStatement.setInt(1, id);

            var product = preparedStatement.executeQuery();
            String productName = null;

            if (product.next()) {
                productName = product.getString("name");

                preparedStatement = connection.prepareStatement(deleteSql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("El producto " + productName + " ha sido borrado ya que se agoto");
            }


        } catch (SQLException e) {
            System.out.println("Error al borrar el producto: " + e.getMessage());
        }
    }


    public void deleteAllProducts() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            String deleteSql = "DELETE FROM product";

            connection = this.connectionBd.getConnection();
            preparedStatement = connection.prepareStatement(deleteSql);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println("Se han borrado " + rowsAffected + " productos.");

        } catch (SQLException e) {
            System.out.println("Error al borrar todos los productos: " + e.getMessage());
        }
    }


    public int getRandomProductId() {
        List<Integer> existingIds = new ArrayList<>();
        String selectIdsSql = "SELECT id FROM product";

        try (Connection connection = this.connectionBd.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectIdsSql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                existingIds.add(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los IDs existentes: " + e.getMessage());
        }

        if (existingIds.isEmpty()) {
            System.out.println("No hay registros en la base de datos.");
            return -1;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(existingIds.size());
        return existingIds.get(randomIndex);
    }

}

