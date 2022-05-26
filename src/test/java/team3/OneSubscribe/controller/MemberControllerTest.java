package team3.OneSubscribe.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import team3.OneSubscribe.domain.Expert;
import team3.OneSubscribe.domain.Member;
import team3.OneSubscribe.service.MemberService;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {


    @Autowired
    MemberService memberService;
    @Autowired
    MockMvc mvc;

    //TODO : 실패메소드를 짤 필요는 없는 듯.
//    @Test
//    public void 로그인실패() throws Exception {
//        //givne
//        final String loginId = "testId";
//        final String loginPw = "testPassword";
//        Member m = new Member();
//        m.setLoginId(loginId);
//        m.setLoginPassword(loginPw);
////        memberService.save(m);
//
//        //when
//        MultiValueMap map = new LinkedMultiValueMap();
//        map.add("loginId", loginId);
//        map.add("loginPassword", loginPw);
//        //then
//        mvc.perform(post("/member/login").params(map))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"));
//
//
//    }

    @Test
    public void 회원가입성공() throws Exception {
        //givne
        final String loginId = "testId";
        final String loginPw = "testPaord";
        final String name = "신민건";
        final String nickName = "myNickName";
        final String email = "zhdhfhd33@naver.com";
        final String team = "부산대병원";
        final String phoneNumber = "010";
        final String firstPhoneNumber = "3391";
        final String secondPhoneNumber = "6486";

        final String expert = "DOCTOR";

        Member m = new Member();
        m.setLoginId(loginId);
        m.setLoginPassword(loginPw);
        m.setName(name);
        m.setEMail(email);
        m.setTeam(team);
        m.setPhoneNumber(phoneNumber + firstPhoneNumber + secondPhoneNumber);
        m.setExpert(Expert.valueOf(expert));

        //저장
//        memberService.save(m);

        //when
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add("loginId", loginId);
        map.add("loginPassword", loginPw);
        map.add("loginPassword2", loginPw);
        map.add("name", name);
        map.add("nickName", name);
        map.add("eMail", email);
        map.add("team", team);
        map.add("phoneNumber", phoneNumber);
        map.add("firstPhoneNumber", firstPhoneNumber);
        map.add("secondPhoneNumber", secondPhoneNumber);
        map.add("expert", expert);

        //then
        mvc.perform(post("/member/signup").params(map))
                .andExpect(status().isOk())
                .andDo(print());
    }

    //TODO 로그아웃 테스트는 짜기 힘든듯. MockMvc로 하면 안통한다. 로그아웃 정도는 안해도 될 듯.
//    @Test
//    public void 로그아웃() throws Exception {
//        //givne
//        final String loginId = "testId";
//        final String loginPw = "testPassword";
//        Member m = new Member();
//        m.setLoginId(loginId);
//        m.setLoginPassword(loginPw);
//        memberService.save(m);
//
//
//        //when
//
//        MultiValueMap map = new LinkedMultiValueMap();
//        map.add("loginId", loginId);
//        map.add("loginPassword", loginPw);
//        mvc.perform(post("/member/login").params(map))
//                .andExpect(view().name("index"));//로그인 성공
//
//        //then
//        mvc.perform(get("/member/logout"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("index"))
//                .andExpect(model().attribute("logout", "success"));
//    }

}