package com.example.Spring_Transaction_Example.Repository;

import com.example.Spring_Transaction_Example.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer>
{

}
