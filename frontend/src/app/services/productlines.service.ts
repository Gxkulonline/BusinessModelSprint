import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class ProductLineService {
  private baseUrl = 'http://localhost:9960/productlines';
  constructor(private http: HttpClient) {}
  getAll() { return this.http.get<any[]>(this.baseUrl); }
  create(data: any) { return this.http.post<any>(this.baseUrl, data); }
}