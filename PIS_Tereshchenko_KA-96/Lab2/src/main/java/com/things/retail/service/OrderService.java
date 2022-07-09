package com.things.retail.service;

import com.things.retail.dao.OrderDaoImpl;
import com.things.retail.dao.impl.OrderDaoImpl;
import com.things.retail.entities.Class;
import com.things.retail.entities.Order;
import com.things.retail.entities.Status;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderService {
    public List<Order> getAll(){
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        return orderDAO.getAll();
    }

    public List<Order> getOrdersByUserId(long user_id) {
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        List<Order> orders = orderDAO.getAll();
        List<Order> filtered_orders = new ArrayList<>();
        for (Order order : orders) {
            if(order.getUser_id() == user_id && order.getStatus().equals(Status.valueOf("pending"))) { filtered_orders.add(order); }
        }
        return filtered_orders;
    }

    public List<Order> getAllUnprocessedOrders() {
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        List<Order> orders = orderDAO.getAll();
        List<Order> filtered_orders = new ArrayList<>();
        for (Order order : orders) {
            if(order.getStatus().equals(Status.valueOf("pending"))) { filtered_orders.add(order); }
        }
        return filtered_orders;
    }

    public List<Order> getProcessedOrdersByUserId(long user_id) {
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        List<Order> orders = orderDAO.getAll();
        List<Order> filtered_orders = new ArrayList<>();
        for (Order order : orders) {
            if(order.getUser_id() == user_id && !order.getStatus().equals(Status.valueOf("pending"))) { filtered_orders.add(order); }
        }
        return filtered_orders;
    }

    public List<Order> getAllProcessedOrders() {
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        List<Order> orders = orderDAO.getAll();
        List<Order> filtered_orders = new ArrayList<>();
        for (Order order : orders) {
            if(!order.getStatus().equals(Status.valueOf("pending"))) { filtered_orders.add(order); }
        }
        return filtered_orders;
    }

    public Order findOrderById(long order_id) {
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        Order order = orderDAO.get(order_id);
        return order;
    }

    public void add(Order order) {
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        orderDAO.create(order);
    }

    public void edit(long order_id, Order order){
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        orderDAO.update(order_id, order);
    }

    public void accept(long order_id, long item_id){
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        orderDAO.accept(order_id, item_id);
    }

    public void deny(long order_id){
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        orderDAO.deny(order_id);
    }

    public void delete(long order_id) {
        OrderDaoImpl orderDAO = new OrderDaoImpl();
        orderDAO.delete(order_id);
    }
}
