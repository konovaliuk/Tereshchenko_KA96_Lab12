package com.things.retail.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public interface ICommand {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
