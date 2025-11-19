package com.lis.shop.member;

import com.lis.shop.Item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/register")    //회원가입 페이지 보여주기
    public String register() {
        return "register.html";
    }
    @PostMapping("/member")     //회원가입 데이터/ bcrypt로 해싱해서 db저장 / //setter로 저장
    public String addMember(
            String username,
            String password,
            String displayName ) throws Exception {
        memberService.saveMember(username, password, displayName);
        return "redirect:/list";
    }
    @GetMapping("/login")   //로그인 페이지
    public String login() {
        return "login.html";
    }
}
