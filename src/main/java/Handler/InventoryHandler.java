package Handler;

import Entity.Product;
import Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryHandler
{
    @Autowired
    private InventoryRepository inventoryrepo;

    public InventoryHandler(InventoryRepository inventoryrepo)
    {
        this.inventoryrepo = inventoryrepo;
    }


    public Product updateProductDetails(Product product)
    {
        return inventoryrepo.save(product);
    }

    public Product getProduct(int id)
    {
        return inventoryrepo.findById(id)
                .orElseThrow(
                        ()-> new RuntimeException("Product not found")
                );

    }
}
