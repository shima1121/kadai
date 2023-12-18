package com.example.test1.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AddressForm {
    @NotBlank
    @Length(max = 50)
    private String name;
    @NotBlank
    @Length(max = 50)
    private String phone;
    @NotBlank
    @Length(max = 100)
    private String address;
}
