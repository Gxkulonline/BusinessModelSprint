import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../../services/product.service'; 
import { CustomerService } from '../../services/customer.service';

@Component({
  selector: 'app-yuvasri',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './yuvasri.component.html',
  styleUrls: ['./yuvasri.component.css']
})
export class YuvasriComponent {
  isModalOpen = false;
  currentModalTitle = '';
  // Added 'PUT' to the type definition
  currentModalType: 'GET_ALL' | 'GET_ID' | 'POST' | 'PUT' | 'DELETE' | null = null;
  currentEntity: 'CUSTOMER' | 'PRODUCT' | null = null;

  apiResult: any = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  searchId: string = '';

  // Matching your backend snake_case
  newCustomer: any = {
    customer_number: null,
    customer_name: '',
    phone: '',
    city: '',
    credit_limit: 0
  };

  newProduct: any = {
    product_code: '',
    product_name: '',
    buy_price: 0,
    quantity_in_stock: 0,
    product_vendor: ''
  };

  constructor(
    private productService: ProductService,
    private customerService: CustomerService,
    private cdr: ChangeDetectorRef 
  ) {}

  openModal(entity: 'CUSTOMER' | 'PRODUCT', type: 'GET_ALL' | 'GET_ID' | 'POST' | 'PUT' | 'DELETE', title: string) {
    this.currentEntity = entity;
    this.currentModalType = type;
    this.currentModalTitle = title;
    this.isModalOpen = true;
    this.apiResult = null;
    this.errorMessage = '';
    this.searchId = '';
    
    // Clear forms when opening POST/PUT
    if (type === 'POST' || type === 'PUT') {
      this.resetForms();
    }
  }

  resetForms() {
    this.newCustomer = { customer_number: null, customer_name: '', phone: '', city: '', credit_limit: 0 };
    this.newProduct = { product_code: '', product_name: '', buy_price: 0, quantity_in_stock: 0, product_vendor: '' };
  }

  closeModal() {
    this.isModalOpen = false;
    this.apiResult = null;
    this.searchId = '';
    this.errorMessage = '';
    this.cdr.detectChanges();
  }

  executeAction() {
    this.isLoading = true;
    this.errorMessage = '';
    this.apiResult = null;

    if (this.currentEntity === 'CUSTOMER') {
      this.handleCustomerActions();
    } else {
      this.handleProductActions();
    }
  }

  private handleCustomerActions() {
    switch (this.currentModalType) {
      case 'GET_ALL':
        this.customerService.getAll().subscribe({
          next: (res) => { this.apiResult = res; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Failed to fetch customers'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'GET_ID':
        this.customerService.getById(this.searchId).subscribe({
          next: (res) => { this.apiResult = res ? [res] : []; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Customer not found'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'POST':
        this.customerService.create(this.newCustomer).subscribe({
          next: (res) => { this.apiResult = [res]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Error saving customer'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'DELETE':
        this.customerService.delete(this.searchId).subscribe({
          next: () => { this.apiResult = [{ customer_number: this.searchId, customer_name: 'DELETED SUCCESSFULLY' }]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Delete failed'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
    }
  }

  private handleProductActions() {
    switch (this.currentModalType) {
      case 'GET_ALL':
        this.productService.getAll().subscribe({
          next: (res) => { this.apiResult = res; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Failed to fetch products'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'GET_ID':
        this.productService.getById(this.searchId).subscribe({
          next: (res) => { this.apiResult = res ? [res] : []; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Product not found'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'POST':
        this.productService.create(this.newProduct).subscribe({
          next: (res) => { this.apiResult = [res]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Error saving product'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
    }
  }
}