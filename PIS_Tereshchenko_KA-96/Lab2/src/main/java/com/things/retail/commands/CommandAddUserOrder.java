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

public class CommandAddUserOrder implements ICommand{
    private static final String ADD_ORDER_PAGE = "/addOrder.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderService();
        User user = (User) request.getSession().getAttribute("user");
        if ("GET".equals(request.getMethod())) {
            return ADD_ORDER_PAGE;
        }
        else {
            long user_id = user.getUser_id();
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
            Order order = new Order(capacity, class_, start_date1, end_date1, Status.pending, user_id);

            orderService.add(order);
            return "redirect:/userOrders";
        }
    }
}
