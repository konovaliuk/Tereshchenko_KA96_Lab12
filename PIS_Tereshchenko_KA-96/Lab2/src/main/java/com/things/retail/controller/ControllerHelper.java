package com.things.retail.controller;

import com.things.retail.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


public class ControllerHelper {
    private static final String COMMAND = "command";
    private static ControllerHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("/signin", new CommandSignIn());
        commands.put("/signup", new CommandSignUp());
        commands.put("/signout", new CommandSignOut());
        commands.put("/index", new CommandIndex());
        commands.put("/userOrders", new CommandUserOrders());
        commands.put("/addOrder", new CommandAddUserOrder());
        commands.put("/editOrder", new CommandEditUserOrder());
        commands.put("/deleteOrder", new CommandDeleteUserOrder());
        commands.put("/allOrders", new CommandAllOrders());
        commands.put("/addItemToOrder", new CommandAddItemToOrder());
        commands.put("/deny", new CommandDenyOrder());
        commands.put("/processedOrders", new CommandProcessedOrders());
        commands.put("/allProcessedOrders", new CommandAllProcessedOrders());

    }

    public ICommand getCommand(HttpServletRequest request) {
        // System.out.println(request.getRequestURI());
        return commands.get(request.getRequestURI());
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {instance = new ControllerHelper();}
        return instance;
    }


}