package com.contact.services;

import com.contact.entity.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> findAll();
    Contact findById(String id);
    Contact save(Contact contact);
    Contact update(Contact contact);
    void delete(String id);
}
