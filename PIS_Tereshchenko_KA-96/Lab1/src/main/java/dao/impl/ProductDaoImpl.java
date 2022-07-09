package dao.impl;

import connection.Query;
import dao.inte.OrderDao;
import dao.inte.ProductDao;

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
    public Optional<entities.Product> get(Long id) {
        String query = String.format("SELECT * FROM %s WHERE productId=%s", entities.Product.tableName, id);
        ResultSet rs = Query.executeQuery(query, conn);
        return entities.Product.fromRow(rs);
    }

    @Override
    public List<entities.Product> getAll() {
        String query = String.format("SELECT * FROM %s", entities.Product.tableName);
        ResultSet rs = Query.executeQuery(query, conn);
        return entities.Product.fromRows(rs);
    }

    @Override
    public Long save(entities.Product product) {
        String query = String.format(
                "INSERT INTO %s (model, stock, price, status) VALUES ('%s', '%s', '%s', '%s') RETURNING productId",
                entities.Product.tableName,
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
    public void update(entities.Product vehicle) {
        String query = String.format(
                "UPDATE %s SET model = '%s', stock = '%s', price = '%s', status = '%s' WHERE productId = %s",
                entities.Product.tableName,
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
        String query = String.format("DELETE FROM %s WHERE productId=%s", entities.Product.tableName, id);
        Query.executeUpdate(query, conn);
    }
}
