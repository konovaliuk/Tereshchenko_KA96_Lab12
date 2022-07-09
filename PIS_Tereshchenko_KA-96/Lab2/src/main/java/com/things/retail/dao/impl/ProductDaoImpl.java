package com.things.retail.dao.impl;

import com.things.retail.entities.Order;
import com.things.retail.entities.Product;
import connection.Query;
import com.things.retail.dao.inte.ProductDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    private final Connection conn;

    public ProductDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Product> get(Long id) {
        String query = String.format("SELECT * FROM %s WHERE productId=%s", Product.tableName, id);
        ResultSet rs = Query.executeQuery(query, conn);
        return Product.fromRow(rs);
    }

    @Override
    public List<Product> getAll() {
        String query = String.format("SELECT * FROM %s", Product.tableName);
        ResultSet rs = Query.executeQuery(query, conn);
        return Product.fromRows(rs);
    }

    @Override
    public Long save(Product product) {
        String query = String.format(
                "INSERT INTO %s (model, stock, price, status) VALUES ('%s', '%s', '%s', '%s') RETURNING productId",
                Product.tableName,
                product.getModel(),
                product.getStock(),
                product.getPrice(),
                product.getStatus()
        );
        ResultSet rs = Query.executeQuery(query, conn);

        Long retVal = null;
        try {
            rs.next();
            retVal = rs.getLong("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void update(Product vehicle) {
        String query = String.format(
                "UPDATE %s SET model = '%s', stock = '%s', price = '%s', status = '%s' WHERE productId = %s",
                Product.tableName,
                vehicle.getStock(),
                vehicle.getModel(),
                vehicle.getPrice(),
                vehicle.getStatus(),
                vehicle.getProductId()
        );
        Query.executeUpdate(query, conn);
    }

    @Override
    public void delete(Long id) {
        String query = String.format("DELETE FROM %s WHERE productId=%s", Product.tableName, id);
        Query.executeUpdate(query, conn);
    }
}
