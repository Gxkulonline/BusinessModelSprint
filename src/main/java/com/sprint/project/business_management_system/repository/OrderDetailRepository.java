package com.sprint.project.business_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.project.business_management_system.entity.OrderDetail;
import com.sprint.project.business_management_system.entity.OrderDetailId;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
	 @Query("SELECT od FROM OrderDetail od WHERE od.quantityOrdered > :qty")
	    List<OrderDetail> findLargeOrders(@Param("qty") int qty);

}
