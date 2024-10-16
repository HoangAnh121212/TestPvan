package com.example.testlifetext.Service.Impl;


import com.example.testlifetext.Dto.LoginRequest;
import com.example.testlifetext.Dto.RegisterDTO;
import com.example.testlifetext.Entity.Role;
import com.example.testlifetext.Entity.User;
import com.example.testlifetext.Exception.AppException;
import com.example.testlifetext.Exception.ErrorCode;
import com.example.testlifetext.Jwt.JwtAuthenticationResponse;
import com.example.testlifetext.Jwt.UserDetailsServiceImpl;
import com.example.testlifetext.Repository.RoleRepository;
import com.example.testlifetext.Repository.UserRepository;
import com.example.testlifetext.Service.JWTService;
import com.example.testlifetext.Service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public User signup(RegisterDTO userDTO) {
        String pass = passwordEncoder.encode(userDTO.getPassword());
        Role userRole = roleRepository.findById(1); //Default la CUSTOMER
        User user = new User(
                0,
                userDTO.getUsername().trim(),
                pass,
                userRole
        );
        return userRepository.save(user);
    }


    @Override
    public JwtAuthenticationResponse signin(LoginRequest loginRequest) {
        UserDetails user;
        try {
            user = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new AppException(ErrorCode.WRONG_USER_NAME);
        }
        // Kiểm tra xem mật khẩu nhập vào có khớp với mật khẩu lưu trong cơ sở dữ liệu không
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new AppException(ErrorCode.WRONG_PASS);
        }
        // Nếu mọi thứ đều đúng, tạo JWT token và trả về
        var jwt = jwtService.generateToken(new HashMap<>(), user);
        // var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken(jwt);
      //  jwtAuthenticationResponse.setRefreshToken("");
        jwtAuthenticationResponse.setUser(user);
        return jwtAuthenticationResponse;
    }
}


