package com.example.testlifetext.Exception;

import com.example.testlifetext.Dto.StudentDTO;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Pattern;

@Component
public class CheckCondition {
    public boolean checkFullName(String name) {
        Pattern p = Pattern.compile("^[a-zA-ZÀ-ỹ\\s]+$");
        return p.matcher(name).find();
    }

    //trong khoảng từ 1 đến 5
    public boolean checkRelationInvalid(Integer relation_id) {
        Pattern p = Pattern.compile("^[1-5]$");
        return p.matcher(relation_id.toString()).find();
    }

    public boolean checkGenderInvalid(Integer gender_id) {
        Pattern p = Pattern.compile("^[1-2]$");
        return p.matcher(gender_id.toString()).find();
    }
}
