package dao;

import dao.impl.OrderDaoImpl;
import dao.impl.OrderItemDaoImpl;
import dao.impl.ProductDaoImpl;
import dao.impl.UserDaoImpl;
import dao.inte.OrderItemsDao;
import dao.inte.ProductDao;
import dao.inte.UserDao;
import dao.inte.OrderDao;

import java.sql.Connection;

public class DaoFactory {
    public static ProductDao getProductDao(Connection conn) {
        return new ProductDaoImpl(conn);
    }

    public static OrderItemsDao getItemsDao(Connection conn) {
        return new OrderItemDaoImpl(conn);
    }

    public static UserDao getUserDao(Connection conn) {
        return new UserDaoImpl(conn);
    }
    public static OrderDao getOrderDao(Connection conn) {
        return new OrderDaoImpl(conn);
    }
}
