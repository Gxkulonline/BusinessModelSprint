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
  searchId: string = '';

  newEmployee: any = {
    employee_number: null,
    email: '',
    first_name: '',
    job_title: '',
    last_name: '',
    office_code: '',
    extension: '',
    reports_to: null
  };

  newOffice: any = {
    office_code: '',
    city: '',
    country: '',
    phone: '',
    address_line1: '',
    address_line2: '',
    postal_code: '',
    state: '',
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
    this.searchId = '';
    if (type === 'POST') this.resetForms();
  }

  resetForms() {
    this.newEmployee = { employee_number: null, email: '', first_name: '', job_title: '', last_name: '', office_code: '', extension: '', reports_to: null };
    this.newOffice = { office_code: '', city: '', country: '', phone: '', address_line1: '', address_line2: '', postal_code: '', state: '', territory: '' };
  }

  closeModal() {
    this.isModalOpen = false;
    this.cdr.detectChanges();
  }

  executeAction() {
    this.isLoading = true;
    this.errorMessage = '';
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
          next: (res) => { this.apiResult = [res]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Error saving employee'; this.isLoading = false; this.cdr.detectChanges(); }
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
          next: (res) => { this.apiResult = [res]; this.isLoading = false; this.cdr.detectChanges(); },
          error: () => { this.errorMessage = 'Error saving office'; this.isLoading = false; this.cdr.detectChanges(); }
        });
        break;
    }
  }
}