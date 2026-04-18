package com.sprint.project.business_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint.project.business_management_system.entity.OrderDetail;
import com.sprint.project.business_management_system.entity.OrderDetailId;
import com.sprint.project.business_management_system.repository.OrderDetailRepository;
@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository repo;
    public List<OrderDetail> getAll() {
        return repo.findAll();
    }
    public OrderDetail save(OrderDetail od) {
        return repo.save(od);
    }
    public void delete(OrderDetailId id) {
        repo.deleteById(id);
    }
}
