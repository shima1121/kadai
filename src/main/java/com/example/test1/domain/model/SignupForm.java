package com.example.test1.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class SignupForm {
    @NotBlank
    private String id;
    @NotBlank
    @Length(min = 4, max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;
}
