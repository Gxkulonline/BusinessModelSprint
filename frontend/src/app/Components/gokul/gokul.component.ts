import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EmployeeService } from '../../services/employee.service';
import { OfficeService } from '../../services/office.service';

@Component({
  selector: 'app-gokul',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './gokul.component.html',
  styleUrls: ['./gokul.component.css']
})
export class GokulComponent {
  isModalOpen = false;
  currentModalTitle = '';
  currentModalType: 'GET_ALL' | 'GET_ID' | 'POST' | null = null;
  currentEntity: 'EMPLOYEE' | 'OFFICE' | null = null;

  apiResult: any = null;
  isLoading: boolean = false;
  errorMessage: string = '';
  successMessage: string = '';
  searchId: string = '';

  // Matching backend camelCase and structure
  newEmployee: any = {
    employeeNumber: null,
    firstName: '',
    lastName: '',
    extension: '',
    email: '',
    jobTitle: '',
    office: {
      officeCode: ''
    },
    reportsTo: null
  };

  newOffice: any = {
    officeCode: '',
    city: '',
    phone: '',
    addressLine1: '',
    addressLine2: '',
    state: '',
    country: '',
    postalCode: '',
    territory: ''
  };

  constructor(
    private employeeService: EmployeeService,
    private officeService: OfficeService,
    private cdr: ChangeDetectorRef 
  ) {}

  openModal(entity: 'EMPLOYEE' | 'OFFICE', type: 'GET_ALL' | 'GET_ID' | 'POST', title: string) {
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
    this.newEmployee = { employeeNumber: null, firstName: '', lastName: '', extension: '', email: '', jobTitle: '', office: { officeCode: '' }, reportsTo: null };
    this.newOffice = { officeCode: '', city: '', phone: '', addressLine1: '', addressLine2: '', state: '', country: '', postalCode: '', territory: '' };
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

    if (this.currentEntity === 'EMPLOYEE') {
      this.handleEmployeeActions();
    } else {
      this.handleOfficeActions();
    }
  }

  private handleEmployeeActions() {
    switch (this.currentModalType) {
      case 'GET_ALL':
        this.employeeService.getAll().subscribe({
          next: (res) => { this.apiResult = res; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Failed to fetch employees'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'GET_ID':
        this.employeeService.getById(this.searchId).subscribe({
          next: (res) => { this.apiResult = res ? [res] : []; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Employee not found'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'POST':
        this.employeeService.create(this.newEmployee).subscribe({
          next: (res) => { 
            this.apiResult = [res]; 
            this.successMessage = 'Employee record successfully added to database!';
            this.isLoading = false; 
            this.cdr.detectChanges(); 
            setTimeout(() => { this.successMessage = ''; this.cdr.detectChanges(); }, 5000);
          },
          error: (err) => { 
            this.errorMessage = 'Error saving employee: ' + (err.error?.message || 'Check required fields'); 
            this.isLoading = false; 
            this.cdr.detectChanges(); 
          }
        });
        break;
    }
  }

  private handleOfficeActions() {
    switch (this.currentModalType) {
      case 'GET_ALL':
        this.officeService.getAll().subscribe({
          next: (res) => { this.apiResult = res; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Failed to fetch offices'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
      case 'POST':
        this.officeService.create(this.newOffice).subscribe({
          next: (res) => { 
            this.apiResult = [res]; 
            this.successMessage = 'Office location registered successfully!';
            this.isLoading = false; 
            this.cdr.detectChanges(); 
            setTimeout(() => { this.successMessage = ''; this.cdr.detectChanges(); }, 5000);
          },
          error: (err) => { 
            this.errorMessage = 'Error saving office: ' + (err.error?.message || 'Check required fields'); 
            this.isLoading = false; 
            this.cdr.detectChanges(); 
          }
        });
        break;
    }
  }
}