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
public class User {
    public static final String tableName = "users";

    private long userId;
    private String login;
    private String passHash;
    private Timestamp registeredTime;

    public static User fromResultSet(ResultSet rs) throws SQLException {
        User user = new User();

        user.setUserId(rs.getLong("userId"));
        user.setLogin(rs.getString("login"));
        user.setPassHash(rs.getString("passHash"));
        user.setRegisteredTime(rs.getTimestamp("registeredTime"));

        return user;
    }

    public static Optional<User> fromRow(ResultSet rs) {
        Optional<User> maybeObject = Optional.empty();
        try {
            if (rs.next()) {
                maybeObject = Optional.of(User.fromResultSet(rs));
            }
        } catch (SQLException ignored) {
        }
        return maybeObject;
    }

    public static ArrayList<User> fromRows(ResultSet rs) {
        ArrayList<User> entities = new ArrayList<>();

        try {
            if (!rs.next()) {
                return entities;
            }

            do {
                entities.add(User.fromResultSet(rs));
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entities;
    }
}
