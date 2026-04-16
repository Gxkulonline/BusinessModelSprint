package com.sprint.project.business_management_system.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sprint.project.business_management_system.Entity.OrderDetail;
import com.sprint.project.business_management_system.Entity.OrderDetailId;
import com.sprint.project.business_management_system.repository.OrderDetailRepository;
import com.sprint.project.business_management_system.service.OrderDetailService;
@RestController
@RequestMapping("/orderdetails")
public class OrderDetailController {
    @Autowired
    private OrderDetailRepository repo;
    @GetMapping
    public List<OrderDetail> getAll() {
        return repo.findAll();
    }
    @PostMapping
    public OrderDetail save(@RequestBody OrderDetail od) {
        return repo.save(od);
    }
    @DeleteMapping
    public void delete(@RequestBody OrderDetailId id) {
        repo.deleteById(id);
    }
    
}
