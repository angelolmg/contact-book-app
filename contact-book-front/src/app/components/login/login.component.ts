import { ContactService } from './../../services/contact.service';
import { LoginService } from './../../services/login.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(
    private router: Router,
    private service: LoginService,
    private contactService: ContactService
  ) {
    this.loginForm = new FormGroup({
      login: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {}

  onSubmit() {
    if (!this.loginForm.valid) {
      return;
    }

    this.service.checkCredentials(this.loginForm.value).subscribe(
      (res) => {
        console.log(res);
        localStorage.setItem('login', this.loginForm.value['login']);
        console.log(this.loginForm.value['login']);
        this.router.navigate(['/contacts']);
      },
      (_error) => {
        console.log(_error);
        this.contactService.onError('Usuário não encontrado');
        this.loginForm.reset();
        return;
      }
    );
  }
}
