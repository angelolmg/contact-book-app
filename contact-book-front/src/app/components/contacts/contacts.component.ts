import { Contact } from './../../models/contact';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { catchError, Observable, of, Subscription, tap } from 'rxjs';
import { ContactService } from 'src/app/services/contact.service';
import { ConfirmDeletionDialogComponent } from 'src/app/shared/components/confirm-deletion-dialog/confirm-deletion-dialog.component';
import { ErrorDialogComponent } from 'src/app/shared/components/error-dialog/error-dialog.component';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.scss']
})
export class ContactsComponent implements OnInit, OnDestroy {

  listSub!: Subscription;
  contacts$!: Observable<Contact[]>;

  displayedColumns = ['name', 'phone', 'email', 'actions'];

  constructor(
    private service: ContactService,
    private dialog: MatDialog,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnDestroy(): void {
    this.listSub.unsubscribe();
  }

  ngOnInit(): void {
    this.listContacts();
    this.listeningEvent();
  }

  listeningEvent() {
    this.listSub = this.service.listeningEmitter().subscribe(() => {
      this.listContacts();
    });
  }

  listContacts() {
    this.contacts$ = this.service.list().pipe(
      tap((n) => console.log(n)),
      catchError(() => {
        this.onError('Erro ao carregar contatos.');
        return of([]);
      })
    );
  }

  onError(errorMsg: string) {
    this.dialog.open(ErrorDialogComponent, {
      data: errorMsg,
    });
  }

  onAdd() {
    this.router.navigate(['new'], { relativeTo: this.route });
  }

  onEdit(contact: Contact) {
    let url = `edit/${contact.id}`;
    this.router.navigate([url], { relativeTo: this.route });
  }

  onDelete(contact: Contact) {
    this.dialog
      .open(ConfirmDeletionDialogComponent, { data: contact.name })
      .afterClosed()
      .subscribe((result) => {
        if (result) {
          this.service.delete(Number(contact.id)).subscribe(
            (_result: any) => {
              this.service.onSuccess('Contato deletado com sucesso.'),
                this.listContacts();
            },
            (_error: any) => this.service.onError('Erro ao deletar contato.')
          );
        }
      });
  }

}
