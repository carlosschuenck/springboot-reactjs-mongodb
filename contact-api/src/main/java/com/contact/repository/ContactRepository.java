package com.contact.repository;

import com.contact.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactRepository extends MongoRepository<Contact, UUID> {
}
