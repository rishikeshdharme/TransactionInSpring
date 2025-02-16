package com.example.Spring_Transaction_Example.Repository;

import com.example.Spring_Transaction_Example.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository  extends JpaRepository<Product,Integer>
{

}
