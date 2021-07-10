package com.contact.service.impl;


import com.contact.dto.ContactDTO;
import com.contact.entity.Contact;
import com.contact.repository.ContactRepository;
import com.contact.service.ContactService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

import static java.util.UUID.randomUUID;

@AllArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<ContactDTO> findAll() {
        List<Contact> contacts = repository.findAll();
        if(contacts.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No contacts registered");
        return contacts.stream().map(contact -> convertToDto(contact)).collect(toList());
    }

    @Override
    public ContactDTO findById(UUID id) {
        Optional<Contact> contact = repository.findById(id);
        if(contact.isPresent()) return convertToDto(contact.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No contact with this id");
    }

    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        contactDTO.setUuid(randomUUID());
        repository.save(convertToEntity(contactDTO));
        return contactDTO;
    }

    @Override
    public ContactDTO update(ContactDTO contactDTO) {
        if(repository.existsById(contactDTO.getUuid())){
            UUID uuid = repository.save(convertToEntity(contactDTO)).getUuid();
            return contactDTO.setUuid(uuid);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No contact with this id");
    }

    @Override
    public void delete(UUID id) {
        if(!repository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No contact with this id");
        repository.delete(Contact.builder().uuid(id).build());
    }

    public ContactDTO convertToDto(Contact contact){
        return modelMapper.map(contact, ContactDTO.class);
    }

    public Contact convertToEntity(ContactDTO contactDTO){
        return modelMapper.map(contactDTO, Contact.class);
    }
}
