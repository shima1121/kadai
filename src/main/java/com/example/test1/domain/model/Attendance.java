package com.example.test1.domain.model;

import lombok.Data;

import java.util.Date;

@Data
public class Attendance {
    private Integer id;
    private Date a_date;
    private  Date w_hours;
    private Date c_time;
}
