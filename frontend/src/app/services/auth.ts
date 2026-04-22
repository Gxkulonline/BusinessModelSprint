import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of, tap, catchError, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = 'http://localhost:9960/api/auth';

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<boolean> {
    const credentials = btoa(`${username}:${password}`);
    const headers = new HttpHeaders({
      Authorization: `Basic ${credentials}`
    });

    return this.http.get<any>(`${this.authUrl}/me`, { headers }).pipe(
      tap(user => {
        localStorage.setItem('credentials', credentials);
        localStorage.setItem('user', JSON.stringify(user));
      }),
      map(() => true),
      catchError(() => of(false))
    );
  }

  getUser() {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  isLoggedIn() {
    return !!localStorage.getItem('credentials');
  }

  logout() {
    localStorage.removeItem('credentials');
    localStorage.removeItem('user');
  }
}