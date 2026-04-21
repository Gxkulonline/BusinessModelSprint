import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class PaymentService {
  private baseUrl = 'http://localhost:9960/payments';
  constructor(private http: HttpClient) {}
  getAll() { return this.http.get<any[]>(this.baseUrl); }
  getByCustomerId(id: string) { return this.http.get<any>(`${this.baseUrl}/customer/${id}`); }
  getTotalByCustomerId(id: string) { return this.http.get<any>(`${this.baseUrl}/customer/${id}/total`); }
  create(data: any) { return this.http.post<any>(this.baseUrl, data); }
}