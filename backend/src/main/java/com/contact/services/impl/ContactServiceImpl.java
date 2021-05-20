package com.contact.services.impl;


import com.contact.entity.Contact;
import com.contact.repository.ContactRepository;
import com.contact.services.ContactService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;

    @Override
    public List<Contact> findAll() {
        repository.save(Contact.builder().uuid("dsadads").name("teste").phoneNumber("22332").build());
        return repository.findAll();
    }

    @Override
    public Contact findById(String id) {
        Optional<Contact> contact = repository.findById(id);
        if (!contact.isPresent()) {
         //  throw new IllegalAccessException("Não achou ngm");
        }
        return contact.get();
    }

    @Override
    public Contact save(Contact contact) {
        return null;
    }

    @Override
    public Contact update(Contact contact) {
        return null;
    }

    @Override
    public void delete(String id) {

    }
}
