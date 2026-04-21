import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class OfficeService {
  private baseUrl = 'http://localhost:9960/offices';
  constructor(private http: HttpClient) {}
  getAll() { return this.http.get<any[]>(this.baseUrl); }
  create(data: any) { return this.http.post<any>(this.baseUrl, data); }
}
