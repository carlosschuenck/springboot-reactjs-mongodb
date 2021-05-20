package com.contact.controllers;

import com.contact.entity.Contact;
import com.contact.services.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/contact")
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/{id}")
    public Contact findById(@PathVariable String id){
        return contactService.findById(id);
    }

    @GetMapping
    public List<Contact> findAll() {
        return contactService.findAll();
    }

    @PostMapping
    public Contact save(Contact contact) {
        return contactService.save(contact);
    }

    @PutMapping
    public Contact update(Contact contact) {
        return contactService.update(contact);
    }

    @DeleteMapping
    public void delete(String id) {
        contactService.delete(id);
    }

}
