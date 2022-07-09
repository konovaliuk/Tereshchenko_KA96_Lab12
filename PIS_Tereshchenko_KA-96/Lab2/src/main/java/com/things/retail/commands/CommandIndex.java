package com.things.retail.commands;

import com.things.retail.dao.UserDAO;
import com.things.retail.entities.Order;
import com.things.retail.entities.User;
import com.things.retail.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class CommandIndex implements ICommand{
    private final static String MAIN_PAGE = "/index.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("username", user.getUsername());
        return MAIN_PAGE;
    }
}
