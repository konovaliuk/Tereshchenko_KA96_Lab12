package com.things.retail.service;

import com.things.retail.dao.DaoFactory;
import com.things.retail.dao.impl.OrderItemDaoImpl;
import com.things.retail.entities.OrderItem;

import java.util.List;

public class ItemService {
    public List<OrderItem> getAll(){
        OrderItemDaoImpl itemDAO = DaoFactory.getItemsDao();
        return itemDAO.getAll();
    }

    public OrderItem findItemById(long item_id) {
        OrderItemDaoImpl itemDAO = new OrderItemDaoImpl();
        OrderItem item = itemDAO.get(item_id);
        return item;
    }
}
