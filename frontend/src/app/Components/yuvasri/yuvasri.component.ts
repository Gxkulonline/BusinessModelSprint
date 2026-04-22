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
  successMessage: string = '';
  searchId: string = '';

  // ... rest of data structures ...
  newCustomer: any = {
    customerNumber: null,
    customerName: '',
    contactFirstName: '',
    contactLastName: '',
    phone: '',
    addressLine1: '',
    addressLine2: '',
    city: '',
    state: '',
    postalCode: '',
    country: '',
    creditLimit: 0,
    salesRepEmployee: {
      employeeNumber: null
    }
  };

  newProduct: any = {
    productCode: '',
    productName: '',
    productLine: {
      productLine: ''
    },
    productScale: '',
    productVendor: '',
    productDesc: '',
    quantityInStock: 0,
    buyPrice: 0,
    msrp: 0
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
    this.successMessage = '';
    this.searchId = '';
    
    // Clear forms when opening POST/PUT
    if (type === 'POST' || type === 'PUT') {
      this.resetForms();
    }

    // Auto-fetch if it's a GET_ALL request
    if (type === 'GET_ALL') {
      this.executeAction();
    }
  }

  resetForms() {
    this.newCustomer = { 
      customerNumber: null, customerName: '', contactFirstName: '', contactLastName: '', 
      phone: '', addressLine1: '', addressLine2: '', city: '', state: '', 
      postalCode: '', country: '', creditLimit: 0, salesRepEmployee: { employeeNumber: null } 
    };
    this.newProduct = { 
      productCode: '', productName: '', productLine: { productLine: '' }, 
      productScale: '', productVendor: '', productDesc: '', 
      quantityInStock: 0, buyPrice: 0, msrp: 0 
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
          next: (res) => { 
            this.apiResult = [res]; 
            this.successMessage = 'Customer created successfully and added to database!';
            this.isLoading = false; 
            this.cdr.detectChanges(); 
            setTimeout(() => { this.successMessage = ''; this.cdr.detectChanges(); }, 5000);
          },
          error: (err) => { 
            console.error(err);
            this.errorMessage = 'Error saving customer: ' + (err.error?.message || 'Check required fields'); 
            this.isLoading = false; 
            this.cdr.detectChanges(); 
          }
        });
        break;
      case 'DELETE':
        this.customerService.delete(this.searchId).subscribe({
          next: () => { 
            this.apiResult = [{ customerNumber: this.searchId, customerName: 'DELETED SUCCESSFULLY' }]; 
            this.successMessage = 'Customer deleted from database.';
            this.isLoading = false; 
            this.cdr.detectChanges(); 
            setTimeout(() => { this.successMessage = ''; this.cdr.detectChanges(); }, 5000);
          },
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
          next: (res) => { 
            this.apiResult = [res]; 
            this.successMessage = 'Product saved successfully in catalog!';
            this.isLoading = false; 
            this.cdr.detectChanges(); 
            setTimeout(() => { this.successMessage = ''; this.cdr.detectChanges(); }, 5000);
          },
          error: (err) => { 
            console.error(err);
            this.errorMessage = 'Error saving product: ' + (err.error?.message || 'Check required fields'); 
            this.isLoading = false; 
            this.cdr.detectChanges(); 
          }
        });
        break;
    }
  }
}