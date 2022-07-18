package com.angelogoes.contactbookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelogoes.contactbookapp.model.Contact;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
}
