package com.contact.controller;

import com.contact.dto.ContactDTO;
import com.contact.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/contacts")
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/{id}")
    public ContactDTO findById(@PathVariable UUID id){
        ContactDTO contact = contactService.findById(id);
        contact.add(linkTo(methodOn(ContactController.class).findAll()).withRel("contacts"));
        contact.add(linkTo(methodOn(ContactController.class).delete(id)).withRel("delete"));
        contact.add(linkTo(methodOn(ContactController.class).update(null)).withRel("update"));
        return contact;
    }

    @GetMapping
    public List<ContactDTO> findAll() {
        List<ContactDTO> contacts = contactService.findAll();
        contacts.forEach(contact -> contact.add(linkTo(methodOn(ContactController.class).findById(contact.getUuid())).withSelfRel()));
        return contacts;
    }

    @PostMapping
    public ResponseEntity<ContactDTO> save(@RequestBody @Valid ContactDTO contact) {
        ContactDTO contactSaved = contactService.save(contact);
        contactSaved.add(linkTo(methodOn(ContactController.class).findById(contact.getUuid())).withSelfRel());
        contactSaved.add(linkTo(methodOn(ContactController.class).delete(contact.getUuid())).withRel("delete"));
        contactSaved.add(linkTo(methodOn(ContactController.class).update(contact)).withRel("update"));
        return status(HttpStatus.CREATED).body(contactSaved);
    }

    @PutMapping
    public ContactDTO update(@RequestBody @Valid ContactDTO contact) {
        ContactDTO contactUpdated = contactService.update(contact);
        contactUpdated.add(linkTo(methodOn(ContactController.class).findById(contact.getUuid())).withSelfRel());
        contactUpdated.add(linkTo(methodOn(ContactController.class).delete(contact.getUuid())).withRel("delete"));
        return contactUpdated;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        contactService.delete(id);
        return noContent().build();
    }

}
