package com.contact.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class ContactDTO extends RepresentationModel<ContactDTO> {

    private UUID uuid;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 100, message = "Name size must be between 3 and 100")
    private String name;

    @NotBlank(message = "Phone number is required")
    @Size(min = 9, max = 20, message = "Phone number size must be between 9 and 20")
    private String phoneNumber;
}
