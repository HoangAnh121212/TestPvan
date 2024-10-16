package com.example.testlifetext.Controller;

import com.example.testlifetext.Dto.*;
import com.example.testlifetext.Entity.PersonalInformations;
import com.example.testlifetext.Entity.Student;
import com.example.testlifetext.Entity.User;
import com.example.testlifetext.Exception.ApiResponse;
import com.example.testlifetext.Jwt.JwtAuthenticationResponse;
import com.example.testlifetext.Service.StudentService;
import com.example.testlifetext.Service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.stereotype.Controller




@RestController
@RequestMapping("/api/student/")
public class Controller {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //Login , Register

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody @Valid LoginRequest loginRequest){
        ApiResponse<JwtAuthenticationResponse> apiResponse= new ApiResponse<>();
        apiResponse.setResult(userService.signin(loginRequest));
        return apiResponse;
    }
    //thêm cái @Valid vi toi bo cai validation vao trong UserDto

    @PostMapping("/registration")
    public ApiResponse<?> registerUserAccount(@RequestBody @Valid RegisterDTO userDto) {
        ApiResponse<User> apiResponse = new ApiResponse<>();

        apiResponse.setResult( userService.signup(userDto));
        return apiResponse;

    }


    //api Student
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("getAllStudent")
    public ApiResponse<?> getAllStudent(){
        ApiResponse<List> a = new ApiResponse();
        a.setResult(studentService.getAll());
        return a;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("deleteStudent")
    public ApiResponse<?> updateStudent(@RequestParam("student_id") int student_id){
        ApiResponse<String> a = new ApiResponse();
        studentService.deleteStudent(student_id);
        a.setResult("Xoá Student Thành Công");
        return a;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("updateStudent")
    public ApiResponse<?> updateStudent(@RequestParam("student_id") int student_id, @RequestBody @Valid StudentDTO studentDTO){
        ApiResponse<Student> a = new ApiResponse();
        a.setResult(studentService.updateStudent(student_id,studentDTO));
        return a;
    }


//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("addStudent")
    public ApiResponse<?> addStudent(@RequestBody @Valid StudentDTO studentDTO){
        ApiResponse<Student> a = new ApiResponse();
        a.setResult(studentService.addStudent(studentDTO));
        return a;
    }

    //api ParentStudent
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping("addPersonInfo")
    public ApiResponse<?> addPersonInfo(@RequestBody @Valid PersonInforDTO personInforDTO){
        ApiResponse<PersonalInformations> a = new ApiResponse();
        a.setResult(studentService.addPersonInfo(personInforDTO));
        return a;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("updatePersonInfo")
    public ApiResponse<?> updatePersonInfo(@RequestParam("info_id") int info_id, @RequestBody @Valid PersonInforDTO personInforDTO){
        ApiResponse<PersonalInformations> a = new ApiResponse();
        a.setResult(studentService.updatePersonalInfo(info_id,personInforDTO));
        return a;
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("deletePersonInfo")
    public ApiResponse<?> deletePersonInfo(@RequestParam("info_id") int info_id){
        ApiResponse<String> a = new ApiResponse();
        studentService.deletePersonalInfo(info_id);
        a.setResult("Xoá Người Thân Thành Công");
        return a;
    }

//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("getAllPersonInfoByStudentId")
    public ApiResponse<?> getAllPersonInfoByStudentId(@RequestParam("student_id") int student_id){
        ApiResponse<List> a = new ApiResponse();
        a.setResult(studentService.getAllPersonInfoByStudentId(student_id));
        return a;
    }

    @GetMapping("getStudentPersonById")
    public ApiResponse<?> getStudentPersonById(@RequestParam("student_id") int student_id){
        ApiResponse<StudentDTOAll> a = new ApiResponse();
        a.setResult(studentService.getStudentPersonById(student_id));
        return a;
    }


}
