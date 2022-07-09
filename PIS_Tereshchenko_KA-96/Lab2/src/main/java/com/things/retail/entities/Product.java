package com.things.retail.entities;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Product {
    public static final String tableName = "vehicles";

    private Long productId;
    private String model;
    private int stock;
    private int price;
    private ProductStatus status;

    public static Product fromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();

        product.setProductId(rs.getLong("productId"));
        product.setModel(rs.getString("model"));
        product.setStock(rs.getInt("stock"));
        product.setPrice(rs.getInt("price"));

        return product;
    }

    public static Optional<Product> fromRow(ResultSet rs) {
        Optional<Product> maybeObject = Optional.empty();
        try {
            if (rs.next()) {
                maybeObject = Optional.of(Product.fromResultSet(rs));
            }
        } catch (SQLException ignored) {
        }
        return maybeObject;
    }

    public static ArrayList<Product> fromRows(ResultSet rs) {
        ArrayList<Product> entities = new ArrayList<>();

        try {
            if (!rs.next()) {
                return entities;
            }

            do {
                entities.add(Product.fromResultSet(rs));
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
