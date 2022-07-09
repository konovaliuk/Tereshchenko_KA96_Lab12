package com.things.retail.commands;

import com.things.retail.entities.Order;
import com.things.retail.entities.Item;
import com.things.retail.service.OrderService;
import com.things.retail.service.ItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CommandAddItemToOrder implements ICommand{
    private static final String ADD_ROOM_PAGE = "/addItemToOrder.jsp";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        OrderService orderService = new OrderService();
        long order_id = Long.parseLong(request.getParameter("order_id"));
        Order order = orderService.findOrderById(order_id);
        request.setAttribute("order", order);

        if ("GET".equals(request.getMethod())) {
            List<Item> items = new ItemService().getAll();
            request.setAttribute("items", items);
            return ADD_ROOM_PAGE;
        }
        else {
            long item_id = Long.valueOf(request.getParameter("item_id"));
            orderService.accept(order_id, item_id);
            return "redirect:/allOrders";
        }
    }
}
