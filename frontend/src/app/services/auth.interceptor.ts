import { HttpInterceptorFn, HttpRequest, HttpHandlerFn, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { Router } from '@angular/router';

export const authInterceptor: HttpInterceptorFn = (req: HttpRequest<unknown>, next: HttpHandlerFn) => {
  const router = inject(Router);
  const credentials = localStorage.getItem('credentials');

  let authReq = req;
  if (credentials) {
    authReq = req.clone({
      setHeaders: {
        Authorization: `Basic ${credentials}`
      }
    });
  }

  return next(authReq).pipe(
    catchError((error: HttpErrorResponse) => {
      if (error.status === 401 || error.status === 403) {
        // Clear credentials on auth error and redirect to landing/login
        localStorage.removeItem('credentials');
        localStorage.removeItem('user');
        router.navigate(['/']);
      }
      return throwError(() => error);
    })
  );
};
