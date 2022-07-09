package com.things.retail.controller;

import com.things.retail.commands.CommandSignIn;
import com.things.retail.commands.CommandSignUp;
import com.things.retail.commands.ICommand;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Controller", value = "/")
public class Controller extends HttpServlet {
    ControllerHelper controllerHelper = ControllerHelper.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String webPage = null;
        // System.out.println("New servlet request");
        if (request.getSession().getAttribute("user") == null && !(controllerHelper.getCommand(request) instanceof CommandSignUp))
            webPage = new CommandSignIn().execute(request, response);
        else {
            ICommand command = controllerHelper.getCommand(request);
            webPage = command.execute(request, response);
        }

        if (webPage.startsWith("redirect:")) {
            response.sendRedirect(webPage.substring(9));
            return;
        }

        request.getRequestDispatcher(webPage).forward(request, response);
    }
}
