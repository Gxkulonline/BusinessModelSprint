package com.sprint.project.business_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.project.business_management_system.Entity.OrderDetail;
import com.sprint.project.business_management_system.Entity.OrderDetailId;
import com.sprint.project.business_management_system.repository.OrderDetailRepository;
@Service
@Transactional
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository repo;
    public List<OrderDetail> getAll() {
        return repo.findAll();
    }
    public OrderDetail save(OrderDetail od) {
        return repo.save(od);
    }
    public OrderDetail update(OrderDetail od) {
        return repo.save(od); // JPA save handles updates if ID is present
    }

}
