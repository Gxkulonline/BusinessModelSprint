import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class OrderDetailService {
  private baseUrl = 'http://localhost:9960/orderdetails';
  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]> { 
    return this.http.get<any>(this.baseUrl).pipe(map(res => res.data)); 
  }

  getById(id: string): Observable<any> { 
    return this.http.get<any>(`${this.baseUrl}/${id}`).pipe(map(res => res.data)); 
  }

  create(data: any): Observable<any> { 
    return this.http.post<any>(this.baseUrl, data).pipe(map(res => res.data)); 
  }

  update(data: any): Observable<any> { 
    return this.http.put<any>(this.baseUrl, data).pipe(map(res => res.data)); 
  }

  delete(id: any): Observable<any> {
    return this.http.delete<any>(this.baseUrl, { body: id });
  }
}
