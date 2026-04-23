import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  private baseUrl = 'http://localhost:9960/api/reports';

  constructor(private http: HttpClient) {}

  getCustomerExposure(): Observable<any> {
    return this.http.get(`${this.baseUrl}/customer-exposure`).pipe(map((res: any) => res.data));
  }


  getSalesByCountry(): Observable<any> {
    return this.http.get(`${this.baseUrl}/sales-by-country`).pipe(map((res: any) => res.data));
  }


  getMonthlyRevenue(): Observable<any> {
    return this.http.get(`${this.baseUrl}/monthly-revenue`).pipe(map((res: any) => res.data));
  }

  getHighRiskCustomers(): Observable<any> {
    return this.http.get(`${this.baseUrl}/high-risk-customers`).pipe(map((res: any) => res.data));
  }
}
