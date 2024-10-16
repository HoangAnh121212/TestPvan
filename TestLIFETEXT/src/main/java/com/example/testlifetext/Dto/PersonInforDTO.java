package com.example.testlifetext.Dto;

import com.example.testlifetext.Entity.Relationship;
import com.example.testlifetext.Entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInforDTO {
//    @NotNull(message = "NAME_NULL")
    //@Size(min=5,message = "tên phải nhập trên 5 kí tự")
    private String fullName;
    @NotNull(message = "GENDER_NULL")
    private Integer gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "DATE_NULL")
    private Date birthDate;

    private Integer relation_id;
    private Integer student_id;

}
