package com.sprint.project.business_management_system.controller;

import com.sprint.project.business_management_system.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/customer-exposure")
    public ResponseEntity<Map<String, Object>> getCustomerExposure() {
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "data", reportService.getCustomerExposure()
        ));
    }


    @GetMapping("/sales-by-country")
    public ResponseEntity<Map<String, Object>> getSalesByCountry() {
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "data", reportService.getSalesByCountry()
        ));
    }


    @GetMapping("/monthly-revenue")
    public ResponseEntity<Map<String, Object>> getMonthlyRevenue() {
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "data", reportService.getMonthlyRevenue()
        ));
    }

    @GetMapping("/high-risk-customers")
    public ResponseEntity<Map<String, Object>> getHighRiskCustomers() {
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "data", reportService.getHighRiskCustomers()
        ));
    }
}
