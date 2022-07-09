package entities;

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
public class OrderItem {
    public static final String tableName = "users";

    private long itemId;
    private long orderId;
    private long productId;
    private int price;
    private int amount;
    private String aux;

    public static OrderItem fromResultSet(ResultSet rs) throws SQLException {
        OrderItem order_item = new OrderItem();

        order_item.setItemId(rs.getLong("itemId"));
        order_item.setOrderId(rs.getLong("orderId"));
        order_item.setProductId(rs.getLong("productId"));
        order_item.setPrice(rs.getInt("price"));
        order_item.setAmount(rs.getInt("amount"));
        order_item.setAux(rs.getString("aux"));

        return order_item;
    }

    public static Optional<OrderItem> fromRow(ResultSet rs) {
        Optional<OrderItem> maybeObject = Optional.empty();
        try {
            if (rs.next()) {
                maybeObject = Optional.of(OrderItem.fromResultSet(rs));
            }
        } catch (SQLException ignored) {
        }
        return maybeObject;
    }

    public static ArrayList<OrderItem> fromRows(ResultSet rs) {
        ArrayList<OrderItem> entities = new ArrayList<>();

        try {
            if (!rs.next()) {
                return entities;
            }

            do {
                entities.add(OrderItem.fromResultSet(rs));
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
