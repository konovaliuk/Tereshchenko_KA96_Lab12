package com.things.retail.commands;

import com.things.retail.entities.Order;
import com.things.retail.entities.User;
import com.things.retail.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandProcessedOrders implements ICommand {
    private final static String USER_ORDERS_PAGE = "/processedOrders.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        List<Order> orders = new OrderService().getProcessedOrdersByUserId(user.getUser_id());
        request.setAttribute("orders", orders);
        return USER_ORDERS_PAGE;
    }
}
