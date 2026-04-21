import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({ providedIn: 'root' })
export class EmployeeService {
  private baseUrl = 'http://localhost:9960/employees';
  constructor(private http: HttpClient) {}
  getAll() { return this.http.get<any[]>(this.baseUrl); }
  getById(id: string) { return this.http.get<any>(`${this.baseUrl}/${id}`); }
  create(data: any) { return this.http.post<any>(this.baseUrl, data); }
}