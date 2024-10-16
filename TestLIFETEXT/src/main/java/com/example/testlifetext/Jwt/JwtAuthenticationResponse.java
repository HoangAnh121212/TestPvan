package com.example.testlifetext.Jwt;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Getter
@Setter
public class JwtAuthenticationResponse {
    private String token;
    private UserDetails user;
}
