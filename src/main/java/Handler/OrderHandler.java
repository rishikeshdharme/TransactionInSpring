package Handler;

import Entity.Order;
import Repository.OrderRepository;
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
