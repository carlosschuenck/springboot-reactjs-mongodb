package com.contact.services.impl;


import com.contact.entity.Contact;
import com.contact.repository.ContactRepository;
import com.contact.services.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;

    @Override
    public List<Contact> findAll() {
        return repository.findAll();
    }

    @Override
    public Contact findById(String id) {
        Optional<Contact> contact = repository.findById(id);
        return contact.get();
    }

    @Override
    public Contact save(Contact contact) {
        return repository.save(contact.setUuid(randomUUID()));
    }

    @Override
    public Contact update(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public void delete(String id) {
        Contact c = new Contact();
        repository.delete(c);
    }
}
