import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProductLineService } from '../../services/productlines.service';
import { PaymentService } from '../../services/payment.service';

@Component({
  selector: 'app-keerthesha',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './keerthesha.component.html',
  styleUrls: ['./keerthesha.component.css']
})
export class KeertheshaComponent {
  isModalOpen = false;
  currentModalTitle = '';
  currentModalType: 'GET_ALL' | 'GET_ID' | 'POST' | 'GET_TOTAL' | null = null;
  currentEntity: 'PRODUCTLINE' | 'PAYMENT' | null = null;

  apiResult: any = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';
  searchId: string = '';

  // ... rest of data structures ...
  newProductLine: any = {
    productLine: '',
    textDescription: '',
    htmlDescription: null,
    image: null
  };

  newPayment: any = {
    customerNumber: null,
    checkNumber: '',
    paymentDate: '',
    amount: 0
  };

  constructor(
    private productLineService: ProductLineService,
    private paymentService: PaymentService,
    private cdr: ChangeDetectorRef 
  ) {}

  openModal(entity: 'PRODUCTLINE' | 'PAYMENT', type: any, title: string) {
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
    this.newProductLine = { productLine: '', textDescription: '', htmlDescription: null, image: null };
    this.newPayment = { 
      customerNumber: null, 
      checkNumber: '', 
      paymentDate: '', 
      amount: 0
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

    if (this.currentEntity === 'PRODUCTLINE') {
      this.handleProductLineActions();
    } else {
      this.handlePaymentActions();
    }
  }

  private handleProductLineActions() {
    switch (this.currentModalType) {
      case 'GET_ALL':
        this.productLineService.getAll().subscribe({
          next: (res) => { this.apiResult = res; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Failed to fetch product lines'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'POST':
        this.productLineService.create(this.newProductLine).subscribe({
          next: (res) => { 
            this.apiResult = [res]; 
            this.successMessage = 'New product line category added successfully!';
            this.isLoading = false; 
            this.cdr.detectChanges(); 
            setTimeout(() => { this.successMessage = ''; this.cdr.detectChanges(); }, 5000);
          },
          error: (err) => { 
            this.errorMessage = 'Error saving product line: ' + (err.error?.message || 'Check required fields'); 
            this.isLoading = false; 
            this.cdr.detectChanges(); 
          }
        });
        break;
    }
  }

  private handlePaymentActions() {
    switch (this.currentModalType) {
      case 'GET_ALL':
        this.paymentService.getAll().subscribe({
          next: (res) => { this.apiResult = res; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Failed to fetch payments'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'GET_ID':
        this.paymentService.getByCustomerId(this.searchId).subscribe({
          next: (res) => { this.apiResult = Array.isArray(res) ? res : [res]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'No payments found for this customer'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'GET_TOTAL':
        this.paymentService.getTotalByCustomerId(this.searchId).subscribe({
          next: (res) => { this.apiResult = res; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Could not calculate total'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'POST':
        this.paymentService.create(this.newPayment).subscribe({
          next: (res) => { 
            this.apiResult = [res]; 
            this.successMessage = 'Payment record successfully processed and saved!';
            this.isLoading = false; 
            this.cdr.detectChanges(); 
            setTimeout(() => { this.successMessage = ''; this.cdr.detectChanges(); }, 5000);
          },
          error: (err) => { 
            const msg = err.error?.message || err.error?.errors?.[0]?.defaultMessage || err.message || 'Check IDs (Date: YYYY-MM-DD)';
            this.errorMessage = 'Error saving payment: ' + msg; 
            this.isLoading = false; 
            this.cdr.detectChanges(); 
          }
        });
        break;
    }
  }
}