package io.k2c1.hereyougo.dto;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class JoinForm {
    private String email;
    private String authCode;

    @Min(6)
    private String password;

    @Min(6)
    private String confirmPassword;

    private String nickname;

    private String categoryId;

    private String roadAddrPart1;
    private String roadAddrPart2;
    private String roadFullAddr;
    private String jibunAddr;
    private String addrDetail;
    private String siNm;
    private String sggNm;
    private String zipNo;
}
