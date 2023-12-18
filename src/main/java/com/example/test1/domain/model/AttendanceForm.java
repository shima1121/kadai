package com.example.test1.domain.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AttendanceForm {
    private Integer id;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date a_date;
    private  Date w_hours;
    private Date c_time;
}
