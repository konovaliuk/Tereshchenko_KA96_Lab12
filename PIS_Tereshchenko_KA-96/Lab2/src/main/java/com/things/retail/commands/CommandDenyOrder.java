package com.things.retail.commands;

import com.things.retail.entities.Order;
import com.things.retail.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandDenyOrder implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderService();
        long order_id = Long.parseLong(request.getParameter("order_id"));
        orderService.deny(order_id);
        return "redirect:/allOrders";
    }
}
