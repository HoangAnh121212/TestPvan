package com.example.testlifetext.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTOAll {
    private String fullName;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Integer gender;
    private List<PersonInforDTO> personInforDTOS;
    private double GPA;
}
