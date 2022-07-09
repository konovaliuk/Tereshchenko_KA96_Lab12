package com.things.retail.commands;

import com.things.retail.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandDeleteUserOrder implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderService();
        long order_id = Long.parseLong(request.getParameter("order_id"));
        orderService.delete(order_id);
        return "redirect:/userOrders";
    }
}
