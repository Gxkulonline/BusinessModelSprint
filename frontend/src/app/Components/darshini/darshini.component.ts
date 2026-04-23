import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OrderService } from '../../services/order.service'; 
import { OrderDetailService } from '../../services/orderdetail.service';

@Component({
  selector: 'app-darshini',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './darshini.component.html',
  styleUrls: ['./darshini.component.css']
})
export class DarshiniComponent {
  isModalOpen = false;
  currentModalTitle = '';
  currentModalType: 'GET_ALL' | 'GET_ID' | 'POST' | 'PUT' | 'DELETE' | null = null;
  currentEntity: 'ORDER' | 'ORDER_DETAIL' | null = null;

  apiResult: any = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';
  searchId: string = '';

  // ... rest of data structures ...
  newOrder: any = {
    orderNumber: null,
    orderDate: '',
    requiredDate: '',
    shippedDate: '',
    status: '',
    comments: '',
    customer: {
      customerNumber: null
    }
  };

  newOrderDetail: any = {
    id: {
      orderNumber: null,
      productCode: ''
    },
    quantityOrdered: 0,
    priceEach: 0,
    orderLineNumber: 0,
    order: {
      orderNumber: null
    },
    product: {
      productCode: ''
    }
  };

  constructor(
    private orderService: OrderService,
    private detailService: OrderDetailService,
    private cdr: ChangeDetectorRef 
  ) {}

  openModal(entity: 'ORDER' | 'ORDER_DETAIL', type: 'GET_ALL' | 'GET_ID' | 'POST' | 'PUT' | 'DELETE', title: string) {
    this.currentEntity = entity;
    this.currentModalType = type;
    this.currentModalTitle = title;
    this.isModalOpen = true;
    this.apiResult = null;
    this.errorMessage = '';
    this.successMessage = '';
    this.searchId = '';
    if (type === 'POST') this.resetForms();

    // Auto-fetch if it's a GET_ALL request
    if (type === 'GET_ALL') {
      this.executeAction();
    }
  }

  resetForms() {
    this.newOrder = { 
      orderNumber: null, orderDate: '', requiredDate: '', shippedDate: '', 
      status: '', comments: '', customer: { customerNumber: null } 
    };
    this.newOrderDetail = { 
      id: { orderNumber: null, productCode: '' }, 
      quantityOrdered: 0, priceEach: 0, orderLineNumber: 0,
      order: { orderNumber: null }, product: { productCode: '' }
    };
  }

  closeModal() {
    this.isModalOpen = false;
    this.apiResult = null;
    this.searchId = '';
    this.errorMessage = '';
    this.successMessage = '';
    this.cdr.detectChanges();
  }

  executeAction() {
    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';
    this.apiResult = null;
    
    if (this.currentEntity === 'ORDER') {
      this.handleOrderActions();
    } else {
      this.handleDetailActions();
    }
  }

  private handleOrderActions() {
    switch (this.currentModalType) {
      case 'GET_ALL':
        this.orderService.getAll().subscribe({
          next: (res) => { this.apiResult = res; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Fetch failed'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'GET_ID':
        this.orderService.getById(this.searchId).subscribe({
          next: (res) => { this.apiResult = res ? [res] : []; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Order not found'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'POST':
        this.orderService.create(this.newOrder).subscribe({
          next: (res) => { 
            this.apiResult = [res]; 
            this.successMessage = 'Order created and saved in dynamic database!';
            this.isLoading = false; 
            this.cdr.detectChanges(); 
            setTimeout(() => { this.successMessage = ''; this.cdr.detectChanges(); }, 5000);
          },
          error: (err) => { 
            this.errorMessage = 'Error saving order: ' + (err.error?.message || 'Check fields (Date: YYYY-MM-DD)'); 
            this.isLoading = false; 
            this.cdr.detectChanges(); 
          }
        });
        break;
    }
  }

  private handleDetailActions() {
    switch (this.currentModalType) {
      case 'GET_ALL':
        this.detailService.getAll().subscribe({
          next: (res) => { this.apiResult = res; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Fetch failed'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'POST':
        // Sync relations for entity mapping
        this.newOrderDetail.order.orderNumber = this.newOrderDetail.id.orderNumber;
        this.newOrderDetail.product.productCode = this.newOrderDetail.id.productCode;

        this.detailService.create(this.newOrderDetail).subscribe({
          next: (res) => { 
            this.apiResult = [res]; 
            this.successMessage = 'Order detail successfully added!';
            this.isLoading = false; 
            this.cdr.detectChanges(); 
            setTimeout(() => { this.successMessage = ''; this.cdr.detectChanges(); }, 5000);
          },
          error: (err) => { 
            this.errorMessage = 'Error saving detail: ' + (err.error?.message || 'Conflict or missing IDs'); 
            this.isLoading = false; 
            this.cdr.detectChanges(); 
          }
        });
        break;
      case 'PUT':
        // Sync relations for entity mapping
        this.newOrderDetail.order.orderNumber = this.newOrderDetail.id.orderNumber;
        this.newOrderDetail.product.productCode = this.newOrderDetail.id.productCode;

        this.detailService.update(this.newOrderDetail).subscribe({
          next: (res) => { 
            this.apiResult = [res]; 
            this.successMessage = 'Order detail updated successfully!';
            this.isLoading = false; 
            this.cdr.detectChanges(); 
            setTimeout(() => { this.successMessage = ''; this.cdr.detectChanges(); }, 5000);
          },
          error: (err) => { 
            this.errorMessage = 'Error updating detail: ' + (err.error?.message || 'Verify IDs and permissions'); 
            this.isLoading = false; 
            this.cdr.detectChanges(); 
          }
        });
        break;
    }
  }
}