package com.example.testlifetext.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@NoArgsConstructor
@Data
@AllArgsConstructor
public class StudentDTO {
    @NotNull(message = "NAME_NULL")
    private String phoneNumber;
//    @NotNull(message = "NAME_NULL")
//    @Size(min=5,message = "tên phải nhập trên 5 kí tự")
    private String fullName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "DATE_NULL")
    private Date birthDate;
    @NotNull(message = "GENDER_NULL")
    private Integer gender;
    @NotNull(message = "GPA_NULL")
    private Double GPA;

}
