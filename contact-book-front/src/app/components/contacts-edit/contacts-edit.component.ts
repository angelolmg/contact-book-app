import { Contact } from './../../models/contact';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ContactService } from 'src/app/services/contact.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-contacts-edit',
  templateUrl: './contacts-edit.component.html',
  styleUrls: ['./contacts-edit.component.css']
})
export class ContactsEditComponent implements OnInit {

  form: FormGroup;
  contact!: Contact;

  constructor(
    private formBuilder: FormBuilder,
    private service: ContactService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      name: [
        null,
        [
          Validators.required,
          Validators.minLength(service.minNameLenght),
          Validators.maxLength(service.maxNameLenght),
        ],
      ],
      phone: [null],
      email: [null]
    });
  }

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;
    this.service.readById(id).subscribe(
      (contact) => {
        this.contact = contact;
        this.form.setValue({
          name: this.contact.name,
          phone: this.contact.phone,
          email: this.contact.email
        });
      },
      (_error) => {
        this.service.onError('Contato não encontrado.'), this.onCancel();
      }
    );
  }

  onSubmit() {
    if (this.form.valid) {

      this.contact.name = this.form.value['name'];
      this.contact.phone = this.form.value['phone'];
      this.contact.email = this.form.value['email'];
      const id = +this.route.snapshot.paramMap.get('id')!;
      this.contact.id = id;

      this.service.update(this.contact).subscribe(
        (_result) => this.service.onSuccess('Contato editado com sucesso.'),
        (_error) => this.service.onError('Erro ao editar contato.')
      );

      this.onCancel();
    }
  }

  onCancel() {
    this.service.onCancel();

  }

}
