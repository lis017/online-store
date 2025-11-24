//package com.lis.shop.member;
//
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
////security 자동기능 정리
////제출비번vsdb비번 비교 자동
////내가 만들어둔 encoder bean을써서 해싱도 자동
////오류시 에러발생 자동
////session쿠키 보내기 자동
////session데이터 저장 자동
////입장권 검사 자동
//
//
//
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   //비번검사
//        return new User(id, password, )
//    }
//
//}
