### Registrar usuário por POST

POST http://localhost:8080/api/signup

{
  "email": "administrador@email.com",
  "login": "admin",
  "password": "senha"
}

{
  "email": "user@email.com",
  "login": "user",
  "password": "user"
}

### Adicionar contato por POST

POST http://localhost:8080/api/contacts

{
    "name":"fulano",
    "phone":"12345",
    "email":"fulano@gmail.com",
    "login":"admin"
}

{
    "name":"fulano2",
    "phone":"12345",
    "email":"fulano2@gmail.com",
    "login":"admin"
}

{
    "name":"fulano3",
    "phone":"12345",
    "email":"fulano3@gmail.com",
    "login":"user"
}

{
    "name":"fulano4",
    "phone":"12345",
    "email":"fulano4@gmail.com",
    "login":"user"
}