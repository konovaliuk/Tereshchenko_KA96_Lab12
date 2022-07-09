package com.things.retail.service;

import com.things.retail.dao.UserDAO;
import com.things.retail.dao.impl.UserDaoImpl;
import com.things.retail.dao.inte.UserDao;
import com.things.retail.entities.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class UserService {
    public static void signUp(String username, String password) {
        UserDaoImpl userDAO = new UserDaoImpl();
        User user = new User(username, password, 0);
        long user_id = userDAO.create(user);
    }

    public User signIn(String username, String password) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByUsername(username);
        userDAO.closeConnection();

        if (user == null) {
            // System.out.println("User: "+username);
            return null;
        }
        // System.out.println("Expected: "+password+"; Real: "+user.getPassword());
        if (password.equals(user.getPassword())) {
            // System.out.println("User: "+username);
            return user;
        }
        else {
            // System.out.println("WRONG CREDENTIALS!");
            return null;
        }
    }

    public User findUserbyUsername(String username) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.findByUsername(username);
        userDAO.closeConnection();

        if (user == null) return null;
        else {
            return user;
        }
    }
}
