import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ContactsEditComponent } from './components/contacts-edit/contacts-edit.component';
import { ContactsNewComponent } from './components/contacts-new/contacts-new.component';
import { ContactsComponent } from './components/contacts/contacts.component';
import { LoginComponent } from './components/login/login.component';

const routes: Routes = [
  { path: 'contacts/new', component: ContactsNewComponent },
  {
    path: 'contacts/edit/:id',
    pathMatch: 'full',
    component: ContactsEditComponent,
  },
  { path: 'contacts', component: ContactsComponent },
  { path: 'login', component: LoginComponent },
  { path: '', pathMatch: 'full', redirectTo: 'login' },
  { path: '**', pathMatch: 'full', redirectTo: 'login' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
