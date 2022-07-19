import { HttpClient } from '@angular/common/http';
import { EventEmitter, Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable, delay, first } from 'rxjs';
import { Contact } from '../models/contact';

@Injectable({
  providedIn: 'root'
})
export class ContactService {

  readonly minNameLenght: number = 1;
  readonly maxNameLenght: number = 50;
  eventEmitter: EventEmitter<any> = new EventEmitter();

  private readonly API: string = 'http://localhost:8080/api/contacts';

  constructor(
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  emitter() {
    return this.eventEmitter.emit();
  }

  listeningEmitter() {
    return this.eventEmitter;
  }

  list() {
    return this.httpClient.get<Contact[]>(this.API).pipe(delay(200), first());
  }

  readById(id: number): Observable<Contact> {
    const url = `${this.API}/${id}`;
    return this.httpClient.get<Contact>(url).pipe(first());
  }

  save(record: Contact) {
    return this.httpClient.post<Contact>(this.API, record).pipe(first());
  }

  delete(id: number) {
    const url = `${this.API}/${id}`;
    return this.httpClient.delete<Contact>(url).pipe(first());
  }

  update(record: Contact) {
    return this.httpClient.put<Contact>(this.API, record).pipe(first());
  }

  showMessage(msg: string, isError: boolean = false): void {
    this.snackBar.open(msg, 'X', {
      duration: 3000,
      horizontalPosition: 'right',
      verticalPosition: 'top',
      panelClass: isError ? ['msg-error'] : ['msg-success'],
    });
  }

  onError(msg: string) {
    this.showMessage(msg, true);
  }

  onSuccess(msg: string) {
    this.showMessage(msg, false);
  }

  onCancel() {
    this.router.navigate(['/home']);
  }
}
