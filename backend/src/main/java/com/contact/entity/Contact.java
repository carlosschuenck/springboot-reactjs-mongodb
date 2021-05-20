package com.contact.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document
public class Contact {

    @Id
    private String uuid;
    private String name;
    private String phoneNumber;
}
