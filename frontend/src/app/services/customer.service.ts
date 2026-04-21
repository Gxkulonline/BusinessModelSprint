import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class CustomerService {
  private baseUrl = 'http://localhost:9960/customers';

  constructor(private http: HttpClient) {}

  // Fetch all customers for the table view
  getAll(): Observable<any[]> { 
    return this.http.get<any[]>(this.baseUrl); 
  }

  // Fetch single customer by ID
  getById(id: string): Observable<any> { 
    return this.http.get<any>(`${this.baseUrl}/${id}`); 
  }

  // POST: Create a new customer (Swagger/DB Integration)
  create(customerData: any): Observable<any> {
    return this.http.post<any>(this.baseUrl, customerData);
  }

  // DELETE: Remove customer by ID
  delete(id: string): Observable<any> { 
    return this.http.delete(`${this.baseUrl}/${id}`); 
  }
}