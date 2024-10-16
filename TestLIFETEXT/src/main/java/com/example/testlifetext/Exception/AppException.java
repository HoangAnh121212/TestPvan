package com.example.testlifetext.Exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor

//taoj AppException riêng để trả về trong service( vis dụ trong userservice)
public class AppException extends  RuntimeException{
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage()); //ke thua constructer cua RuntimeException
        this.errorCode = errorCode;
    }

    private ErrorCode errorCode;
}
