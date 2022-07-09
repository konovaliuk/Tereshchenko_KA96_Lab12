package dao.impl;

import connection.Query;
import dao.inte.OrderDao;
import dao.inte.OrderItemsDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderItemDaoImpl implements OrderItemsDao {
    private final Connection conn;

    public OrderItemDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<entities.OrderItem> get(Long id) {
        String query = String.format("SELECT * FROM %s WHERE itemId=%s", entities.OrderItem.tableName, id);
        ResultSet rs = Query.executeQuery(query, conn);
        return entities.OrderItem.fromRow(rs);
    }

    @Override
    public List<entities.OrderItem> getAll() {
        String query = String.format("SELECT * FROM %s", entities.OrderItem.tableName);
        ResultSet rs = Query.executeQuery(query, conn);
        return entities.OrderItem.fromRows(rs);
    }

    @Override
    public Long save(entities.OrderItem order) {
        String query = String.format(
                "INSERT INTO %s (orderId. productId, price, amount, aux) VALUES ('%s', '%s', '%s', '%s', '%s') RETURNING itemId",
                entities.OrderItem.tableName,
                order.getOrderId(),
                order.getProductId(),
                order.getPrice(),
                order.getAmount(),
                order.getAux()
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
    public void update(entities.OrderItem order) {
        String query = String.format(
                "UPDATE %s SET orderId = '%s', productId = '%s', price = '%s', amount = '%s', aux = '%s' WHERE orderId = %s",
                entities.Order.tableName,
                order.getOrderId(),
                order.getProductId(),
                order.getPrice(),
                order.getAmount(),
                order.getAux(),
                order.getItemId()
        );
        Query.executeUpdate(query, conn);
    }

    @Override
    public void delete(Long id) {
        String query = String.format("DELETE FROM %s WHERE orderId=%s", entities.OrderItem.tableName, id);
        Query.executeUpdate(query, conn);
    }
}
