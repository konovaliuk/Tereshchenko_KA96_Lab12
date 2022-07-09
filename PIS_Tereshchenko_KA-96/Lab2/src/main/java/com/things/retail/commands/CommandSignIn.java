package com.things.retail.commands;

import com.things.retail.entities.User;
import com.things.retail.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandSignIn implements ICommand {
    private final static String AUTH_PAGE = "/signin.jsp";
    private final static String MAIN_PAGE = "redirect:/index";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // System.out.println("Executing sign in");
        if (request.getSession().getAttribute("user") != null) {
            return MAIN_PAGE;
        }
        if ("GET".equals(request.getMethod())) {
            return AUTH_PAGE;
        }
        User user = new UserService().signIn(request.getParameter("username"),request.getParameter("password"));
        if (user == null) {
            request.setAttribute("message","WRONG CREDENTIALS!");
            return AUTH_PAGE;
        }
        request.getSession().setAttribute("user", user);
        return MAIN_PAGE;
    }
}
