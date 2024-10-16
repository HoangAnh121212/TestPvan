package com.example.testlifetext.Config;

import com.example.testlifetext.Dto.PersonInforDTO;
import com.example.testlifetext.Dto.StudentDTO;
import com.example.testlifetext.Entity.PersonalInformations;
import com.example.testlifetext.Entity.Student;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // Áp dụng chiến lược khớp chính xác
        //yêu cầu các trường trong dto và unity phải có tên và kiểu dữ liệu giống nhau

        modelMapper.typeMap(PersonalInformations.class, PersonInforDTO.class)
                .addMapping(src -> src.getStudent().getStudent_id(), PersonInforDTO::setStudent_id)
                .addMapping(src -> src.getRelationship().getRelation_id(), PersonInforDTO::setRelation_id)
                .addMapping(PersonalInformations::getFullName, PersonInforDTO::setFullName)
                .addMapping(PersonalInformations::getBirthDate, PersonInforDTO::setBirthDate)
                .addMapping(src -> src.getGender().getGender_id(), PersonInforDTO::setGender);

        modelMapper.typeMap(Student.class, StudentDTO.class)
                .addMapping(Student::getFullName, StudentDTO::setFullName)
                .addMapping(Student::getBirthDate, StudentDTO::setBirthDate)
                .addMapping(Student::getGPA, StudentDTO::setGPA)
                .addMapping(Student::getPhoneNumber, StudentDTO::setPhoneNumber)
                .addMapping(src -> src.getGender().getGender_id(), StudentDTO::setGender);
        return modelMapper;
    }

}