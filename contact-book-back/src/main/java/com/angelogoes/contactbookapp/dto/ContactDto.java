package com.angelogoes.contactbookapp.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDto {
    
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Instant registerDate;

    private String login;
}