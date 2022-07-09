package com.things.retail.dao.impl;

import com.things.retail.entities.Order;
import connection.Query;
import com.things.retail.dao.inte.OrderDao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private final Connection conn;

    public OrderDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Optional<Order> get(Long id) {
        String query = String.format("SELECT * FROM %s WHERE orderId=%s", Order.tableName, id);
        ResultSet rs = Query.executeQuery(query, conn);
        return Order.fromRow(rs);
    }

    @Override
    public List<Order> getAll() {
        String query = String.format("SELECT * FROM %s", Order.tableName);
        ResultSet rs = Query.executeQuery(query, conn);
        return Order.fromRows(rs);
    }

    @Override
    public Long save(Order order) {
        String query = String.format(
                "INSERT INTO %s (deliveryInfo, userId, status, createdTime) VALUES ('%s', '%s', '%s', '%s') RETURNING orderId",
                Order.tableName,
                order.getDeliveryInfo(),
                order.getUserId(),
                order.getStatus(),
                order.getCreatedTime()
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
    public void update(Order order) {
        String query = String.format(
                "UPDATE %s SET deliveryInfo = '%s', userId = '%s', status = '%s', createdTime = '%s' WHERE orderId = %s",
                Order.tableName,
                order.getDeliveryInfo(),
                order.getUserId(),
                order.getStatus(),
                order.getCreatedTime(),
                order.getOrderId()
        );
        Query.executeUpdate(query, conn);
    }

    @Override
    public void delete(Long id) {
        String query = String.format("DELETE FROM %s WHERE orderId=%s", Order.tableName, id);
        Query.executeUpdate(query, conn);
    }
}
