package com.example.testlifetext.Service;


import com.example.testlifetext.Dto.LoginRequest;
import com.example.testlifetext.Dto.RegisterDTO;
import com.example.testlifetext.Entity.User;
import com.example.testlifetext.Jwt.JwtAuthenticationResponse;
import org.springframework.stereotype.Service;
@Service
public interface UserService {
    JwtAuthenticationResponse signin(LoginRequest loginRequest);
     User signup(RegisterDTO userDTO);

}
