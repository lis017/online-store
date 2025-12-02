package com.lis.shop.member;

import com.lis.shop.Item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    //회원가입 페이지 보여주기
    @GetMapping("/register")
    public String register(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {  //null이면 뒤에 함수 안호출해서, 오류안나게 처리.
            return "redirect:/list";
        }
        return "register.html";
    }

    //회원가입 데이터/ bcrypt로 해싱해서 db저장 / //setter로 저장
    @PostMapping("/member")
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
    @GetMapping("/my-page") //마이페이지
    public String myPage(Authentication auth) {                 //auth에 현재 유저 로그인정보 다 있음.
        CustomUser result = (CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);

        System.out.println(auth);
        System.out.println(auth.getName()); //아이디출력가능
        System.out.println(auth.isAuthenticated()); //로그인여부 검사가능
        System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("regular")));
        return "mypage.html";
    }

    //유저 상세페이지
    @GetMapping("/user/1")
    @ResponseBody
    public Data getUser() {
        var a = memberRepository.findById(1L);
        var result = a.get();
        var data = new Data(result.getUsername(), result.getDisplayName());
        return data;
    }
}

//DTO
class Data {
    public String username;
    public String displayName;
    Data(String a, String b){
        this.username = a;
        this.displayName = b;
    }
}