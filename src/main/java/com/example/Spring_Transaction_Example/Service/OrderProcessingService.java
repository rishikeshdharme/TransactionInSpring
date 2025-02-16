package com.example.Spring_Transaction_Example.Service;

import com.example.Spring_Transaction_Example.Entity.Order;
import com.example.Spring_Transaction_Example.Entity.Product;
import com.example.Spring_Transaction_Example.Handler.InventoryHandler;
import com.example.Spring_Transaction_Example.Handler.OrderHandler;
import org.springframework.stereotype.Service;

@Service
public class OrderProcessingService
{
   private OrderHandler orderHandler;
   private InventoryHandler inventoryHandler;

    public OrderProcessingService(InventoryHandler inventoryHandler,OrderHandler orderHandler) {
        this.inventoryHandler = inventoryHandler;
        this.orderHandler = orderHandler;
    }

    public Order placeAndOrder(Order order)
    {
        //get product from inventory
        System.out.println("Order class: " + order.getClass().getName());
        Product product = inventoryHandler.getProduct(order.getId());

        //validate stock availability<(5)
        validateStockAvailability(order, product);

        //update the total price in order entity
        order.setTotalprice(order.getQuantity()* product.getPrice());

        //save order
        Order order1=orderHandler.saveOrder(order);

        //update the stock
        updateInventoryStock(product, order1);


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
