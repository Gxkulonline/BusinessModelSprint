import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ProductService {
  private baseUrl = 'http://localhost:9960/products';

  constructor(private http: HttpClient) {}

  // Fetch all products for the inventory overview
  getAll(): Observable<any[]> { 
    return this.http.get<any[]>(this.baseUrl); 
  }

  // Fetch single product by product_code
  getById(id: string): Observable<any> { 
    return this.http.get<any>(`${this.baseUrl}/${id}`); 
  }

  // POST: Add new product to inventory
  create(productData: any): Observable<any> {
    return this.http.post<any>(this.baseUrl, productData);
  }
}