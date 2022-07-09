package com.things.retail.dao;

import com.things.retail.dao.impl.OrderDaoImpl;
import com.things.retail.dao.impl.OrderItemDaoImpl;
import com.things.retail.dao.impl.ProductDaoImpl;
import com.things.retail.dao.impl.UserDaoImpl;
import com.things.retail.dao.inte.OrderItemsDao;
import com.things.retail.dao.inte.ProductDao;
import com.things.retail.dao.inte.UserDao;
import com.things.retail.dao.inte.OrderDao;

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
