import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PaymentService {
  private baseUrl = 'http://localhost:9960/payments';
  constructor(private http: HttpClient) {}
  
  getAll(): Observable<any[]> { 
    return this.http.get<any>(this.baseUrl).pipe(map(res => res.data)); 
  }
  
  getByCustomerId(id: string): Observable<any[]> { 
    return this.http.get<any>(`${this.baseUrl}/customer/${id}`).pipe(map(res => res.data)); 
  }

  getTotalByCustomerId(id: string): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/customer/${id}/total`).pipe(map(res => res.data));
  }
  
  create(data: any): Observable<any> { 
    return this.http.post<any>(this.baseUrl, data).pipe(map(res => res.data)); 
  }

  delete(id: string): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${id}`);
  }
}