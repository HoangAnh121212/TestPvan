package com.example.testlifetext.Service.Impl;

import com.example.testlifetext.Dto.PersonInforDTO;
import com.example.testlifetext.Dto.StudentDTO;
import com.example.testlifetext.Dto.StudentDTOAll;
import com.example.testlifetext.Entity.Gender;
import com.example.testlifetext.Entity.PersonalInformations;
import com.example.testlifetext.Entity.Relationship;
import com.example.testlifetext.Entity.Student;
import com.example.testlifetext.Exception.AppException;
import com.example.testlifetext.Exception.CheckCondition;
import com.example.testlifetext.Exception.ErrorCode;
import com.example.testlifetext.Repository.GenderRepository;
import com.example.testlifetext.Repository.PersonInforRepository;
import com.example.testlifetext.Repository.RelationshipRepository;
import com.example.testlifetext.Repository.StudentRepository;
import com.example.testlifetext.Service.StudentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private RelationshipRepository relationshipRepository;
    @Autowired
    private PersonInforRepository personInforRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CheckCondition checkCondition;
    @Autowired
    private GenderRepository genderRepository;
    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Transactional
    @Override
    public Student addStudent(StudentDTO studentDTO) {
        if(!checkCondition.checkFullName(studentDTO.getFullName())){
            throw new AppException(ErrorCode.INVALID_NAME_FORMAT);
        }
        Student a = new Student();
        a.setBirthDate(studentDTO.getBirthDate());
        a.setGPA(studentDTO.getGPA());
        a.setFullName(studentDTO.getFullName());
        Gender g = genderRepository.findById(studentDTO.getGender()).get();
        a.setGender(g);
        a.setPhoneNumber(studentDTO.getPhoneNumber());
        studentRepository.save(a);
        return a;
    }

    @Transactional
    @Override
    public Student updateStudent(int student_id,StudentDTO studentDTO) {
        if(!checkCondition.checkFullName(studentDTO.getFullName())){
            throw new AppException(ErrorCode.INVALID_NAME_FORMAT);
        }
        if(!checkCondition.checkGenderInvalid(studentDTO.getGender())){
            throw new AppException(ErrorCode.GENDER_INVALID);
        }
        Student p = studentRepository.findById(student_id).get();
        if(p == null){
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        studentRepository.updateStudent(student_id,studentDTO.getGPA(),studentDTO.getBirthDate(),studentDTO.getPhoneNumber(),studentDTO.getFullName(),studentDTO.getGender());
        return studentRepository.findById(student_id).get();
    }

    @Override
    public PersonalInformations addPersonInfo(PersonInforDTO personInforDTO) {
        if(!checkCondition.checkFullName(personInforDTO.getFullName())){
            throw new AppException(ErrorCode.INVALID_NAME_FORMAT);
        }
        if(!checkCondition.checkGenderInvalid(personInforDTO.getGender())){
            throw new AppException(ErrorCode.GENDER_INVALID);
        }
        if(!checkCondition.checkRelationInvalid(personInforDTO.getRelation_id())){
            throw new AppException(ErrorCode.RELATION_INVALID);
        }
        PersonalInformations a = new PersonalInformations();
        a.setBirthDate(personInforDTO.getBirthDate());
        a.setFullName(personInforDTO.getFullName());
        Gender g = genderRepository.findById(personInforDTO.getGender()).get();
        a.setGender(g);
        Student s = studentRepository.findById(personInforDTO.getStudent_id()).get();
        a.setStudent(s);
        Relationship re = relationshipRepository.findById(personInforDTO.getRelation_id()).get();
        a.setRelationship(re);
        personInforRepository.save(a);
        return a;
    }

    @Transactional
    @Override
    public void deleteStudent(int student_id) {
        Student a = studentRepository.findById(student_id).get();
        if(a == null){
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        List<PersonalInformations> list = personInforRepository.getParentByStudentId(student_id);
        if(!list.isEmpty()){
            for(PersonalInformations p : list){
                personInforRepository.deletePersonalInfo(p.getInformation_id());
            }
        }
        studentRepository.deleteStudent(student_id);
    }

    @Transactional
    @Override
    public PersonalInformations updatePersonalInfo(int info_id, PersonInforDTO personInforDTO) {
        if(!checkCondition.checkFullName(personInforDTO.getFullName())){
            throw new AppException(ErrorCode.INVALID_NAME_FORMAT);
        }
        if(!checkCondition.checkGenderInvalid(personInforDTO.getGender())){
            throw new AppException(ErrorCode.GENDER_INVALID);
        }
        if(!checkCondition.checkRelationInvalid(personInforDTO.getRelation_id())){
            throw new AppException(ErrorCode.RELATION_INVALID);
        }
        PersonalInformations p = personInforRepository.findById(info_id).get();
        if(p == null){
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        personInforRepository.updatePersonalInfo(info_id,personInforDTO.getBirthDate(),personInforDTO.getFullName(),
                personInforDTO.getGender(),personInforDTO.getRelation_id(),personInforDTO.getStudent_id());
        return personInforRepository.findById(info_id).get();
    }

    @Override
    public void deletePersonalInfo(int info_id) {
        PersonalInformations p = personInforRepository.findById(info_id).get();
        if(p == null){
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        personInforRepository.deletePersonalInfo(info_id);
    }

    @Override
    public List<PersonalInformations> getAllPersonInfoByStudentId(int student_id) {
        List<PersonalInformations> list = personInforRepository.getParentByStudentId(student_id);
        if(list.isEmpty()){
            throw new AppException(ErrorCode.NOT_FOUND);
        }
        return list;
    }

    @Override
    public StudentDTOAll getStudentPersonById(int student_id) {
        Student student = studentRepository.findById(student_id).get();
        StudentDTOAll stu = new StudentDTOAll();
        List<PersonInforDTO> list_dto = new ArrayList<>();
        stu.setBirthDate(student.getBirthDate());
        stu.setGender(student.getGender().getGender_id());
        stu.setGPA(student.getGPA());
        stu.setFullName(student.getFullName());
        List<PersonalInformations> list = personInforRepository.getParentByStudentId(student_id);
        if(!list.isEmpty()){
            list_dto  =  list.stream()
                    .map(person -> modelMapper.map(person, PersonInforDTO.class))
                    .collect(Collectors.toList());
        }

        stu.setPersonInforDTOS(list_dto);
        return stu;
    }


}
