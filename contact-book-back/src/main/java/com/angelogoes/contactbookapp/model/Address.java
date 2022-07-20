package com.angelogoes.contactbookapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 200, nullable = false)
    private String street;
    @Column(length = 100, nullable = false)
    private String district;
    @Column(length = 20, nullable = false)
    private Long number;
    @Column(length = 20, nullable = false)
    private String uf;
    @Column(length = 10, nullable = false)
    private Long cep;

}
