package com.eazykart.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eazykart.orders.entity.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>{

}
