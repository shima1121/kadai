package com.example.test1.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class PlaceForm {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date w_date;
    @NotBlank
    @Length(max = 50)
    private String place;
}
