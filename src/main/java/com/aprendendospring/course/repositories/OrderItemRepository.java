package com.aprendendospring.course.repositories;

import com.aprendendospring.course.entities.OrderItem;
import com.aprendendospring.course.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
