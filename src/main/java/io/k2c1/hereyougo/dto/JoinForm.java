package io.k2c1.hereyougo.dto;

import lombok.Data;

@Data
public class JoinForm {
    private String email;
    private String password;
    private String confirmPassword;
    private String nickname;
    private String businessType;
    private String address;
    private String detailAddress;
}
