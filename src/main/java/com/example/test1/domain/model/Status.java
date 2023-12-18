package com.example.test1.domain.model;

import lombok.Data;

import java.util.Date;
@Data
public class Status {
    private Integer id;
    private Date a_date;
    private  Date w_hours;
    private Date c_time;
    private Date w_date;
    private String place;
}
