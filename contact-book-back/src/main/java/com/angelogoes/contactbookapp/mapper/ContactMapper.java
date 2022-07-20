package com.angelogoes.contactbookapp.mapper;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.angelogoes.contactbookapp.dto.ContactDto;
import com.angelogoes.contactbookapp.model.Contact;
import com.angelogoes.contactbookapp.model.ContactBook;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContactMapper {

    public Contact mapDtoToContact(ContactDto contactDto, ContactBook contactBook) {
        if (contactDto.getRegisterDate() == null)
            contactDto.setRegisterDate(Instant.now());

        return Contact.builder()
                .name(contactDto.getName())
                .email(contactDto.getEmail())
                .phone(contactDto.getPhone())
                .registerDate(contactDto.getRegisterDate())
                .contactBook(contactBook)
                .build();
    }

    public ContactDto mapContactToDto(Contact contact) {
        return ContactDto.builder()
                .id(contact.getId())
                .name(contact.getName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .registerDate(contact.getRegisterDate())
                .build();
    }
}
