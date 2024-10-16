package com.example.testlifetext.Repository;

import com.example.testlifetext.Entity.PersonalInformations;
import com.example.testlifetext.Entity.Relationship;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface PersonInforRepository extends JpaRepository<PersonalInformations, Integer> {

    @Query("SELECT u FROM PersonalInformations u WHERE u.student.student_id = ?1")
    List<PersonalInformations> getParentByStudentId(@Param("student_id") int student_id);

    @Modifying
    @Query("DELETE FROM PersonalInformations u WHERE u.information_id = ?1")
    void deletePersonalInfo(@Param("information_id") int information_id);

    @Transactional
    @Modifying
    @Query("update PersonalInformations u set u.birthDate = ?2 , u.fullName = ?3,u.gender.gender_id = ?4,u.relationship.relation_id = ?5,u.student.student_id = ?6  where u.information_id = ?1")
    void updatePersonalInfo(int information_id, Date birthDate, String fullName, int gender, int relation_id, int student_id);
}
