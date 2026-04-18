package com.sprint.project.business_management_system.service;

import java.util.List;
<<<<<<< HEAD

import com.sprint.project.business_management_system.entity.Order;
=======
import com.sprint.project.business_management_system.requestDto.OrderRequestDto;
import com.sprint.project.business_management_system.responseDto.OrderResponseDto;
>>>>>>> refs/heads/master

public interface OrderService {

    OrderResponseDto createOrder(OrderRequestDto dto);

    OrderResponseDto getOrderById(Integer id);

    List<OrderResponseDto> getOrdersByStatus(String status);
}