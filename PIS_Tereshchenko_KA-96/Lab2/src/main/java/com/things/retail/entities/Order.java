package com.things.retail.entities;

import lombok.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Order {
    public static final String tableName = "user_order";

    private Long orderId;
    private Long userId;
    private String deliveryInfo;
    private OrderStatus status;
    private Timestamp createdTime;

    public static Order fromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();

        order.setOrderId(rs.getLong("orderId"));
        order.setUserId(rs.getLong("userId"));
        order.setDeliveryInfo(rs.getString("deliveryInfo"));
        order.setCreatedTime(rs.getTimestamp("createdTime"));

        return order;
    }

    public static Optional<Order> fromRow(ResultSet rs) {
        Optional<Order> maybeObject = Optional.empty();
        try {
            if (rs.next()) {
                maybeObject = Optional.of(Order.fromResultSet(rs));
            }
        } catch (SQLException ignored) {
        }
        return maybeObject;
    }

    public static ArrayList<Order> fromRows(ResultSet rs) {
        ArrayList<Order> entities = new ArrayList<>();

        try {
            if (!rs.next()) {
                return entities;
            }

            do {
                entities.add(Order.fromResultSet(rs));
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
