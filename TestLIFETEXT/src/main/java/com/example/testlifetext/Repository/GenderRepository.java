package com.example.testlifetext.Repository;

import com.example.testlifetext.Entity.Gender;
import com.example.testlifetext.Entity.PersonalInformations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
}
