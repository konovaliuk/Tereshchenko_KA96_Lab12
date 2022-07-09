package com.things.retail.commands;

import com.things.retail.entities.Order;
import com.things.retail.entities.User;
import com.things.retail.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandAllOrders implements ICommand{
    private final static String ALL_ORDERS_PAGE = "/allOrders.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = new OrderService().getAllUnprocessedOrders();
        request.setAttribute("orders", orders);
        return ALL_ORDERS_PAGE;
    }
}
