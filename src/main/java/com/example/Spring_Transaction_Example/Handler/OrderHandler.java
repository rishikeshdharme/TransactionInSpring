package com.example.Spring_Transaction_Example.Handler;

import com.example.Spring_Transaction_Example.Entity.Order;
import com.example.Spring_Transaction_Example.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderHandler
{
    @Autowired
    private OrderRepository orderrepo;

    public OrderHandler(OrderRepository orderrepo)
    {
        this.orderrepo = orderrepo;
    }

    public Order saveOrder(Order order)
    {
        return orderrepo.save(order);
    }
}
