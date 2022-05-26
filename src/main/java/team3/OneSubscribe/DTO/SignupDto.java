package team3.OneSubscribe.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupDto {
    private String loginId; // 형식 확인 필요

    private String loginPassword;
    private String pw2;

    private String name;

    private String nickName;

    private String eMail;

    private String team;

    private String phoneNumber;
    private String first;
    private String second;

    private int who;//expertArr의 index

    private final String[] expertArr = {"", "NONEXPERT", "DOCTOR"};

}
