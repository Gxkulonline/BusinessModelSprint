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
  currentModalType: 'GET_ALL' | 'GET_ID' | 'POST' | 'DELETE' | null = null;
  currentEntity: 'ORDER' | 'ORDER_DETAIL' | null = null;

  apiResult: any = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  searchId: string = '';

  newOrder: any = {
    order_number: null,
    order_date: '',
    required_date: '',
    status: '',
    customer_number: null,
    comments: '',
    shipped_date: ''
  };

  newOrderDetail: any = {
    order_number: null,
    product_code: '',
    price_each: 0,
    quantity_ordered: 0,
    order_line_number: 0
  };

  constructor(
    private orderService: OrderService,
    private detailService: OrderDetailService,
    private cdr: ChangeDetectorRef 
  ) {}

  openModal(entity: 'ORDER' | 'ORDER_DETAIL', type: 'GET_ALL' | 'GET_ID' | 'POST' | 'DELETE', title: string) {
    this.currentEntity = entity;
    this.currentModalType = type;
    this.currentModalTitle = title;
    this.isModalOpen = true;
    this.apiResult = null;
    this.errorMessage = '';
    this.searchId = '';
    if (type === 'POST') this.resetForms();
  }

  resetForms() {
    this.newOrder = { order_number: null, order_date: '', required_date: '', status: '', customer_number: null, comments: '', shipped_date: '' };
    this.newOrderDetail = { order_number: null, product_code: '', price_each: 0, quantity_ordered: 0, order_line_number: 0 };
  }

  closeModal() {
    this.isModalOpen = false;
    this.cdr.detectChanges();
  }

  executeAction() {
    this.isLoading = true;
    this.errorMessage = '';
    
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
          next: (res) => { this.apiResult = [res]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Error saving order'; this.isLoading = false; this.cdr.detectChanges(); }
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
        this.detailService.create(this.newOrderDetail).subscribe({
          next: (res) => { this.apiResult = [res]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Error saving detail'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'DELETE':
        this.detailService.delete(this.searchId).subscribe({
          next: () => { this.apiResult = [{ order_number: this.searchId, status: 'DELETED' }]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Delete failed'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
    }
  }
}