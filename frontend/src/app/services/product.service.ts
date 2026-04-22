import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ProductService {
  private baseUrl = 'http://localhost:9960/products';

  constructor(private http: HttpClient) {}

  // Fetch all products
  getAll(): Observable<any[]> { 
    return this.http.get<any>(this.baseUrl).pipe(
      map(response => response.data)
    ); 
  }

  // Fetch single product by product_code
  getById(id: string): Observable<any> { 
    return this.http.get<any>(`${this.baseUrl}/${id}`).pipe(
      map(response => response.data)
    ); 
  }

  // POST: Add new product
  create(productData: any): Observable<any> {
    return this.http.post<any>(this.baseUrl, productData).pipe(
      map(response => response.data)
    );
  }

  // DELETE: Remove product
  delete(id: string): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${id}`);
  }
}