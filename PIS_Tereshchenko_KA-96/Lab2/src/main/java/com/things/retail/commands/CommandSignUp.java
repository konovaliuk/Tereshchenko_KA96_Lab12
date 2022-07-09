package com.things.retail.commands;

import com.things.retail.entities.User;
import com.things.retail.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CommandSignUp implements ICommand{
    private final static String REG_PAGE = "/signup.jsp";
    private final static String MAIN_PAGE = "redirect:/index";
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // System.out.println("Executing sign up");
        if (request.getSession().getAttribute("user") != null) {
            return MAIN_PAGE;
        }
        if ("GET".equals(request.getMethod())) {
            return REG_PAGE;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || username.equals("") || password == null || password.equals("")) {
            request.setAttribute("message", "CREDENTIALS MUSTN'T BE EMPTY!");
            return REG_PAGE;
        }
        User user = new UserService().findUserbyUsername(request.getParameter("username"));
        if (user != null) {
            request.setAttribute("message","USERNAME OCCUPIED!");
            return REG_PAGE;
        }
        UserService.signUp(username, password);
        request.getSession().setAttribute("user", user);
        return MAIN_PAGE;
    }
}
