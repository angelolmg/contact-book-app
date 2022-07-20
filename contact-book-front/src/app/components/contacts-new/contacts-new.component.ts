import { ContactService } from 'src/app/services/contact.service';

import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { tap } from 'rxjs';


@Component({
  selector: 'app-contacts-new',
  templateUrl: './contacts-new.component.html',
  styleUrls: ['./contacts-new.component.scss'],
})
export class ContactsNewComponent implements OnInit {
  form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private service: ContactService
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

  ngOnInit(): void {}

  onSubmit() {

    if (this.form.valid) {
      this.service
        .save(this.form.value)
        .pipe(
          tap(() => {
            this.service.emitter();
          })
        )
        .subscribe(
          (_result) => this.service.onSuccess('Contato salvo com sucesso.'),
          (_error) => {
            console.log(_error);
            this.service.onError('Erro ao criar contato.')
          }
        );
      this.onCancel();
    }
  }

  onCancel() {
    // set as pristine first so it doesn't activate the deactivate guard
    //this.form.markAsPristine();
    this.service.onCancel();
  }

}
