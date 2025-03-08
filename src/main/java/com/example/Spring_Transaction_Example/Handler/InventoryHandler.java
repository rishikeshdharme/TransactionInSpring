package com.example.Spring_Transaction_Example.Handler;

import com.example.Spring_Transaction_Example.Entity.Product;
import com.example.Spring_Transaction_Example.Repository.InventoryRepository;
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
        //forcefully added the exception simulate the transaction
        if(product.getPrice()>2000)
        {
            throw new RuntimeException("Db Crashed..");
        }

        return inventoryrepo.save(product);
    }

    public Product getProduct(int id)
    {
        return inventoryrepo.findById(id)
                .orElseThrow(
                        ()-> new RuntimeException("Product not found"+id)
                );

    }
}
