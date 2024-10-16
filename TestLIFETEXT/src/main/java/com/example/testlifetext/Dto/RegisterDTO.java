package com.example.testlifetext.Dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
//    @Size(min=5,message = "NAME_NULL")
    private String username;
//    @Size(min=4,message = "PASS_INVALID")
    private String password;
//    @NotNull(message = "MUST_REQUIRED")
    private String checkPass;


}
