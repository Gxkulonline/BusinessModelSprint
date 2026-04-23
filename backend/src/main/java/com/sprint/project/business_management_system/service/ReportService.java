package com.sprint.project.business_management_system.service;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Map<String, Object>> getCustomerExposure();
    List<Map<String, Object>> getSalesByCountry();
    List<Map<String, Object>> getMonthlyRevenue();
    List<Map<String, Object>> getHighRiskCustomers();
}
