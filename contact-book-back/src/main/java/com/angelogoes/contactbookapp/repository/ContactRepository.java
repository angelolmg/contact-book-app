package com.angelogoes.contactbookapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelogoes.contactbookapp.model.Contact;
import com.angelogoes.contactbookapp.model.ContactBook;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByContactBook(ContactBook contactBook);
    
}
