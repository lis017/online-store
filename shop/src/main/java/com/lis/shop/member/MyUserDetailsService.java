package com.lis.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//security 자동기능 정리---
//제출비번vsdb비번 비교 자동
//내가 만들어둔 encoder bean을써서 해싱도 자동
//오류시 에러발생 자동
//session쿠키 보내기 자동
//session데이터 저장 자동
//입장권 검사 자동

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override                                                                                   //login으로 post보낸게, 여기로옴.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   //로그인시, 유저정보 확인
        var result = memberRepository.findByUsername(username);                                 //정보 꺼내면, 알아서 비교해줍니다. //이상하면 에러발생 //이상없으면 쿠키(세션)줍니다.
        if (result.isEmpty()){
            throw new UsernameNotFoundException("해당 아이디는 존재하지 않습니다.");
        }
        var user = result.get();
        List<GrantedAuthority> authorities = new ArrayList<>();                  //권한 변수
        authorities.add(new SimpleGrantedAuthority("regular"));             //일반유저
        //이 내용만이 auth변수에 들어감
        CustomUser customUser = new CustomUser(user.getUsername(), user.getPassword(), authorities);
        customUser.id = user.getId();
        customUser.displayName = user.getDisplayName();
        return customUser;
    }
}

class CustomUser extends User {
    public Long id;
    public String displayName;

    public CustomUser(String username,
                      String password,
                      List<GrantedAuthority> authorities) {
        super(username, password, authorities);
    }
}