package com.angelogoes.contactbookapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.angelogoes.contactbookapp.model.Contact;
import com.angelogoes.contactbookapp.repository.ContactRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;

    @GetMapping
    public List<Contact> list() {
        return contactRepository.findAll();
    }

    @GetMapping("/{id}")
    public Contact getById(@PathVariable("id") Long id) {
        Optional<Contact> contact = contactRepository.findById(id);
        return contact.get();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Contact create(@RequestBody Contact contact) {
        return contactRepository.save(contact);
        //return ResponseEntity.status(HttpStatus.CREATED)
        //        .body(ContactRepository.save(Contact));

    }

    @PutMapping
    public Contact update(@RequestBody Contact contact) {
        return create(contact);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        contactRepository.deleteById(id);
    }

}
