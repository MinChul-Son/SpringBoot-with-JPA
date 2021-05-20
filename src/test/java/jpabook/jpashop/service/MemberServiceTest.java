package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() {
        Member member = new Member();
        member.setName("son");

        Long savedId = memberService.join(member);

        assertThat(member).isEqualTo(memberRepository.findOne(savedId));
    }

    @Test// 이 예외가 발생하면 테스트 성공
    public void 중복회원_예외() {
        Member member1 = new Member();
        member1.setName("son");

        Member member2 = new Member();
        member2.setName("son");

        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
//        try {
//            memberService.join(member2); // 예외 발생해야함.
//        } catch (IllegalStateException e) {
//            return;
//        }


//        Assert.fail("예외가 발생해야 한다."); // 이 메서드가 실행되면 테스트는 실패!
    }

}