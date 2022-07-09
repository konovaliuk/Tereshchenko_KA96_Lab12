package com.things.retail.commands;

import com.things.retail.entities.Class;
import com.things.retail.entities.Order;
import com.things.retail.entities.Status;
import com.things.retail.entities.User;
import com.things.retail.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandEditUserOrder implements ICommand{
    private static final String EDIT_ORDER_PAGE = "/editOrder.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderService();
        long order_id = Long.valueOf(request.getParameter("order_id"));
        Order order = orderService.findOrderById(order_id);
        if ("GET".equals(request.getMethod())) {
            request.setAttribute("order", order);
            return EDIT_ORDER_PAGE;
        }
        else {
            long capacity = Long.valueOf(request.getParameter("capacity"));
            Class class_ = Class.valueOf(request.getParameter("class_"));
            String start_date = request.getParameter("start_date");
            String end_date = request.getParameter("end_date");
            Date start_date1 = null;
            Date end_date1 = null;
            try {
                start_date1 = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
                end_date1 = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Order order1 = new Order(capacity, class_, start_date1, end_date1);
            orderService.edit(order_id, order1);
            return "redirect:/userOrders";
        }
    }
}
