package com.lis.shop.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);   //usrename으로 해당 user행 찾아오기
                                                        //Optional: null이어도 오류x.(실행x)
                                                        //Derived Query Methods
}
