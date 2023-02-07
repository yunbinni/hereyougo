package io.k2c1.hereyougo.dto;

import io.k2c1.hereyougo.domain.Address;
import lombok.Data;

@Data
public class JoinForm {
    private String email;
    private String password;
    private String confirmPassword;
    private String nickname;
    private String businessType;

    private String roadAddrPart1;
    private String roadAddrPart2;
    private String roadFullAddr;
    private String jibunAddr;
    private String addrDetail;
    private String siNm;
    private String sggNm;
    private String zipNo;
}
