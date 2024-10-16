package com.example.testlifetext.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INVALID_NAME_FORMAT(1001,"Sai Format Của Tên ",HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999,"Lỗi Hệ Thống", HttpStatus.INTERNAL_SERVER_ERROR), //day la loai exception khong ngo toi
    WRONG_USER_NAME(1002,"Sai Tên đăng nhập",HttpStatus.BAD_REQUEST),
    WRONG_PASS(1003,"Sai mật khẩu",HttpStatus.BAD_REQUEST),
    PHONE_NULL(1004,"Số điện thoại không được bỏ trống",HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED(1005,"Token hết hạn , vui lòng đăng nhập lại",HttpStatus.UNAUTHORIZED),
    NOT_FOUND(1006,"Không tìm thấy kết quả tìm kiếm ",HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(1007,"Bạn không có quyền truy cập thao tác này ",HttpStatus.FORBIDDEN),
    NAME_NULL(1008,"Tên không được bỏ trống",HttpStatus.BAD_REQUEST),
    DATE_NULL(1009,"Ngày tháng không được bỏ trống",HttpStatus.BAD_REQUEST),
    GENDER_NULL(1010,"Giới tính không được bỏ trống",HttpStatus.BAD_REQUEST),
    GENDER_INVALID(1011,"Giới tính không hợp lệ (chỉ nhập 1 (nam) hoặc 2 (nữ))",HttpStatus.BAD_REQUEST),
    RELATION_INVALID(1012,"Mối quan hệ không hợp lệ(1: Bố, 2: Mẹ, 3: Anh, 4: Chị 5: Em)",HttpStatus.BAD_REQUEST),
    RELATION_NULL(1013,"Mối quan hệ không được bỏ trống",HttpStatus.BAD_REQUEST),
    INVALID_KEY(1014,"Invalid Message Key",HttpStatus.BAD_REQUEST), //dinh nghĩa 1 số cái nhập sai ở dto, ví dụ như PAS_INVALID mà thiếu chữ S
    GPA_NULL(1015,"Gpa không được bỏ trống",HttpStatus.BAD_REQUEST),
    DATE_INVALID(1016,"Sai định dạng ngày tháng , ngày tháng cần phải nhập theo format yyyy-MM-dd",HttpStatus.BAD_REQUEST),

    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode=statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

}
