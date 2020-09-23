package com.application.repository;

import com.application.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
   // Set<Order> findAllByTableID(int tableId);
}
