package com.example.Spring_Transaction_Example.Service;

import com.example.Spring_Transaction_Example.Entity.Order;
import com.example.Spring_Transaction_Example.Entity.Product;
import com.example.Spring_Transaction_Example.Handler.AuditLogHandler;
import com.example.Spring_Transaction_Example.Handler.InventoryHandler;
import com.example.Spring_Transaction_Example.Handler.OrderHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProcessingService
{
   private OrderHandler orderHandler;
   private InventoryHandler inventoryHandler;
   private AuditLogHandler auditLogHandler;

    public OrderProcessingService(InventoryHandler inventoryHandler,OrderHandler orderHandler,AuditLogHandler auditLogHandler) {
        this.inventoryHandler = inventoryHandler;
        this.orderHandler = orderHandler;
        this.auditLogHandler = auditLogHandler;
    }

    @Transactional(readOnly = false,propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
    public Order placeAndOrder(Order order)
    {
        //get product from inventory
        System.out.println("Order class: " + order.getClass().getName());
        Product product = inventoryHandler.getProduct(order.getProductId());

        //validate stock availability<(5)
        validateStockAvailability(order, product);

        //update the total price in order entity
        order.setTotalprice(order.getQuantity()* product.getPrice());
        Order order1 =null;
        try {
            //save order
             order1 = orderHandler.saveOrder(order);

            //update the stock
            updateInventoryStock(product, order1);
            auditLogHandler.logAuditDetails(order,"audit successfull");
        }catch (Exception e)
        {
            auditLogHandler.logAuditDetails(order,"audit falied");
        }

        return order1;
    }

    private static void validateStockAvailability(Order order, Product product) {
        if(order.getQuantity()> product.getStockquantity())
        {
            throw new RuntimeException("Stock is not available (Stock count is only "+ product.getStockquantity()+" )");
        }
    }

    private void updateInventoryStock(Product product, Order order1) {
        product.setStockquantity(product.getStockquantity()- order1.getQuantity());
        inventoryHandler.updateProductDetails(product);
    }
}
