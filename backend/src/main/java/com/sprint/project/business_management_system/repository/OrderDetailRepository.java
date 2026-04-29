package com.sprint.project.business_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sprint.project.business_management_system.entity.OrderDetail;
import com.sprint.project.business_management_system.entity.OrderDetailId;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {

}
