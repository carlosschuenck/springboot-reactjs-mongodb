package com.contact.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Builder
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Contact {

    @Id
    private UUID uuid;
    private String name;
    private String phoneNumber;
}
