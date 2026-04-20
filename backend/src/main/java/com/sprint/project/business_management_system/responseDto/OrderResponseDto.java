package com.sprint.project.business_management_system.responseDto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class OrderResponseDto {
	    private Integer orderNumber;
	    public Integer getOrderNumber() {
			return orderNumber;
		}
		public void setOrderNumber(Integer orderNumber) {
			this.orderNumber = orderNumber;
		}
		public LocalDate getOrderDate() {
			return orderDate;
		}
		public void setOrderDate(LocalDate orderDate) {
			this.orderDate = orderDate;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		private LocalDate orderDate;
	    private String status;
	    
	    
    
    
}
