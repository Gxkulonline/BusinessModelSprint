package com.sprint.project.business_management_system.impl;

import com.sprint.project.business_management_system.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
@Service
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Map<String, Object>> getCustomerExposure() {
        String sql = "SELECT c.customer_number, c.customer_name, c.credit_limit, " +
                     "SUM(od.quantity_ordered * od.price_each) as total_order_value " +
                     "FROM customers c " +
                     "LEFT JOIN orders o ON c.customer_number = o.customer_number " +
                     "LEFT JOIN orderdetails od ON o.order_number = od.order_number " +
                     "GROUP BY c.customer_number, c.customer_name, c.credit_limit";
        return jdbcTemplate.queryForList(sql);
    }
    @Override
    public List<Map<String, Object>> getSalesByCountry() {
        String sql = "SELECT c.country, SUM(od.quantity_ordered * od.price_each) as total_sales " +
                     "FROM customers c " +
                     "JOIN orders o ON c.customer_number = o.customer_number " +
                     "JOIN orderdetails od ON o.order_number = od.order_number " +
                     "GROUP BY c.country " +
                     "ORDER BY total_sales DESC";
        return jdbcTemplate.queryForList(sql);
    }
    @Override
    public List<Map<String, Object>> getMonthlyRevenue() {
        String sql = "SELECT EXTRACT(YEAR FROM o.order_date) as year, " +
                     "EXTRACT(MONTH FROM o.order_date) as month, " +
                     "SUM(od.quantity_ordered * od.price_each) as revenue " +
                     "FROM orders o " +
                     "JOIN orderdetails od ON o.order_number = od.order_number " +
                     "GROUP BY EXTRACT(YEAR FROM o.order_date), EXTRACT(MONTH FROM o.order_date) " +
                     "ORDER BY year DESC, month DESC";
        return jdbcTemplate.queryForList(sql);
    }
    @Override
    public List<Map<String, Object>> getHighRiskCustomers() {
        String sql = "SELECT * FROM (" +
                     "  SELECT c.customer_number, c.customer_name, c.credit_limit, " +
                     "  SUM(od.quantity_ordered * od.price_each) as total_order_value " +
                     "  FROM customers c " +
                     "  JOIN orders o ON c.customer_number = o.customer_number " +
                     "  JOIN orderdetails od ON o.order_number = od.order_number " +
                     "  GROUP BY c.customer_number, c.customer_name, c.credit_limit" +
                     ") as report " +
                     "WHERE total_order_value >= credit_limit * 0.8 " +
                     "ORDER BY total_order_value DESC";
        return jdbcTemplate.queryForList(sql);
    }
}
