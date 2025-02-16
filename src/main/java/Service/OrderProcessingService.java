package Service;

import Handler.InventoryHandler;
import Handler.OrderHandler;
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
}
