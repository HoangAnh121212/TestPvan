package com.example.testlifetext.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Table(name = "PersonalInformations", schema = "LifeText", catalog = "")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonalInformations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "information_id")
    private Integer information_id;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "birthDate")
    private Date birthDate;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "relation_id")
    private Relationship relationship;

    @ManyToOne
    @JsonBackReference
    @JsonIgnore
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "gender_id")
    private Gender gender;


}
