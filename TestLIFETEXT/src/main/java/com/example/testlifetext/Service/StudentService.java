package com.example.testlifetext.Service;

import com.example.testlifetext.Dto.PersonInforDTO;
import com.example.testlifetext.Dto.StudentDTO;
import com.example.testlifetext.Dto.StudentDTOAll;
import com.example.testlifetext.Entity.PersonalInformations;
import com.example.testlifetext.Entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student addStudent(StudentDTO studentDTO);
    Student updateStudent(int student_id,StudentDTO studentDTO);
    PersonalInformations addPersonInfo(PersonInforDTO personInforDTO);
    void deleteStudent(int student_id);
    PersonalInformations updatePersonalInfo(int info_id,PersonInforDTO personInforDTO);
    void deletePersonalInfo(int info_id);
    List<PersonalInformations> getAllPersonInfoByStudentId(int student_id);
    StudentDTOAll getStudentPersonById(int student_id);

}
