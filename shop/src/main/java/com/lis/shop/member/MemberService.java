package com.lis.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public void saveMember(String username,             //회원가입 처리 메서드
                           String password,
                           String displayName) throws Exception {
        var result = memberRepository.findByUsername(username);
        if (result.isPresent()){                                        //unique=true 있어도, 추후 처리를위해 추가
            throw new Exception("존재하는아이디");                         //유효성 검사후 예외발생
        }
        if (username.length() < 1 || password.length() < 1){
            throw new Exception("id혹은 password가 1자이상 되게 해주세요.");
        }
        Member member = new Member();
        member.setUsername(username);
        var hash = passwordEncoder.encode(password);
        member.setPassword(hash);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }
}
