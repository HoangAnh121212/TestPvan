package com.example.testlifetext.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Relationship", schema = "LifeText", catalog = "")
public class Relationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relation_id")
    private Integer relation_id;

    @Column(name = "relationName")
    private String relationName;

//    @OneToMany(mappedBy = "relationship")
//    private List<PersonalInformations> relaInformations;


}
