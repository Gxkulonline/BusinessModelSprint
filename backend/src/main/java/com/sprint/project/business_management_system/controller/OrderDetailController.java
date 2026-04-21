//package com.sprint.project.business_management_system.controller;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import com.sprint.project.business_management_system.Entity.OrderDetail;
//import com.sprint.project.business_management_system.Entity.OrderDetailId;
//import com.sprint.project.business_management_system.repository.OrderDetailRepository;
//import com.sprint.project.business_management_system.service.OrderDetailService;
//@RestController
//@RequestMapping("/orderdetails")
//public class OrderDetailController {
//    @Autowired
//    private OrderDetailRepository repo;
//    @GetMapping
//    public List<OrderDetail> getAll() {
//        return repo.findAll();
//    }
//    @PostMapping
//    public OrderDetail save(@RequestBody OrderDetail od) {
//        return repo.save(od);
//    }
//    @DeleteMapping
//    public void delete(@RequestBody OrderDetailId id) {
//        repo.deleteById(id);
//    }
//    
//}
package com.sprint.project.business_management_system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.sprint.project.business_management_system.Entity.OrderDetail;
import com.sprint.project.business_management_system.Entity.OrderDetailId;
import com.sprint.project.business_management_system.service.OrderDetailService;

@RestController
@RequestMapping("/orderdetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService service;

    // ✅ GET ALL -> 200 OK
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll() {
        List<OrderDetail> list = service.getAll();

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Order details fetched successfully",
                "data", list
            )
        );
    }

    // ✅ POST -> 201 CREATED
    @PostMapping
    public ResponseEntity<Map<String, Object>> save(@RequestBody OrderDetail od) {
        OrderDetail saved = service.save(od);

        return ResponseEntity.status(HttpStatus.CREATED).body(
            Map.of(
                "status", "success",
                "message", "Order detail created successfully",
                "data", saved
            )
        );
    }

    // ✅ DELETE -> 200 OK (JSON)
    @DeleteMapping
    public ResponseEntity<Map<String, String>> delete(@RequestBody OrderDetailId id) {
        service.delete(id);

        return ResponseEntity.ok(
            Map.of(
                "status", "success",
                "message", "Order detail deleted successfully"
            )
        );
    }
}
