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
  searchId: string = '';

  newProductLine: any = {
    product_line: '',
    html_description: null,
    image: null,
    text_description: ''
  };

  newPayment: any = {
    check_number: '',
    customer_number: null,
    amount: 0,
    payment_date: ''
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
    this.searchId = '';
    if (type === 'POST') this.resetForms();
  }

  resetForms() {
    this.newProductLine = { product_line: '', html_description: null, image: null, text_description: '' };
    this.newPayment = { check_number: '', customer_number: null, amount: 0, payment_date: '' };
  }

  closeModal() {
    this.isModalOpen = false;
    this.cdr.detectChanges();
  }

  executeAction() {
    this.isLoading = true;
    this.errorMessage = '';
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
          next: (res) => { this.apiResult = [res]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Error saving product line'; this.isLoading = false; this.cdr.detectChanges(); }
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
          next: (res) => { this.apiResult = [res]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Error saving payment'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
    }
  }
}