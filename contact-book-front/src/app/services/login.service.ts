import { LoginRequest } from './../dto/LoginRequest';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable, first, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LoginService {

  private readonly API: string = 'http://localhost:8080/api/login';

  constructor(
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router,
  ) {}

  checkCredentials(loginRequest: LoginRequest): Observable<boolean> {
    return this.httpClient.post<boolean>(this.API, loginRequest).pipe(first());
  }
}
