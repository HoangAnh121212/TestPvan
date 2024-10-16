package com.example.testlifetext.Repository;

import com.example.testlifetext.Entity.Relationship;
import com.example.testlifetext.Entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Transactional
    @Modifying
    @Query("update Student u set u.GPA = ?2 , u.birthDate = ?3,u.phoneNumber = ?4,u.fullName = ?5,u.gender.gender_id = ?6  where u.student_id = ?1")
    void updateStudent(int student_id, double GPA, Date birthDate, String phoneNumber, String fullName, int gender);

    @Modifying
    @Query("DELETE FROM Student u WHERE u.student_id = ?1")
    void deleteStudent(@Param("student_id") int student_id);
}
