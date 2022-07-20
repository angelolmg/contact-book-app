package com.angelogoes.contactbookapp.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

import com.angelogoes.contactbookapp.dto.ContactDto;
import com.angelogoes.contactbookapp.exceptions.ContactNotFoundException;
import com.angelogoes.contactbookapp.exceptions.ContactBookNotFoundException;
import com.angelogoes.contactbookapp.mapper.ContactMapper;
import com.angelogoes.contactbookapp.model.Contact;
import com.angelogoes.contactbookapp.repository.ContactBookRepository;
import com.angelogoes.contactbookapp.repository.ContactRepository;
import com.angelogoes.contactbookapp.repository.UserRepository;
import com.angelogoes.contactbookapp.model.ContactBook;
import com.angelogoes.contactbookapp.model.User;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@Slf4j
public class ContactController {

    private final ContactRepository contactRepository;
    private final ContactBookRepository contactBookRepository;
    private final UserRepository userRepository;
    private final ContactMapper contactMapper;

    @GetMapping("/by-user/{login}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<ContactDto> getByLogin(@PathVariable("login") String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
                ContactBook contactBook = contactBookRepository.findByUser(user)
                .orElseThrow(() -> new ContactBookNotFoundException("Agenda não encontrada!"));
        return contactRepository.findAllByContactBook(contactBook)
                .stream()
                .map(contactMapper::mapContactToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/by-id/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ContactDto getById(@PathVariable("id") Long id) {
        Contact contact = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contato não encontrado!"));
        return contactMapper.mapContactToDto(contact);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ContactDto> getAllContacts() {
        return contactRepository.findAll()
                .stream()
                .map(contactMapper::mapContactToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ContactDto create(@RequestBody ContactDto contactDto) {

        User user = userRepository.findByLogin(contactDto.getLogin())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        ContactBook contactBook = contactBookRepository.findByUser(user)
                .orElseThrow(() -> new ContactBookNotFoundException("Agenda não encontrada!"));
        Contact contact = contactMapper.mapDtoToContact(contactDto, contactBook);

        return contactMapper.mapContactToDto(contactRepository.save(contact));
        //return ResponseEntity.status(HttpStatus.CREATED)
        //    .body(contactMapper.mapContactToDto(contactRepository.save(contact)));

    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@RequestBody ContactDto contactDto) {
        Contact contact = contactRepository.findById(contactDto.getId())
                .orElseThrow(() -> new ContactNotFoundException("Contato não encontrado!"));
        contact.setName(contactDto.getName());
        contact.setPhone(contactDto.getPhone());
        contact.setEmail(contactDto.getEmail());
        
        contactRepository.save(contact);
        //return create(contactDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        contactRepository.deleteById(id);
    }

}
