package com.contact.service;

import com.contact.dto.ContactDTO;
import com.contact.entity.Contact;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    List<ContactDTO> findAll();
    ContactDTO findById(UUID id);
    ContactDTO save(ContactDTO contact);
    ContactDTO update(ContactDTO contact);
    void delete(UUID id);
}
