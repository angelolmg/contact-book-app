package com.angelogoes.contactbookapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.angelogoes.contactbookapp.model.ContactBook;
import com.angelogoes.contactbookapp.model.User;


@Repository
public interface ContactBookRepository extends JpaRepository<ContactBook, Long> {

    Optional<ContactBook> findByUser(User user);
    
}
