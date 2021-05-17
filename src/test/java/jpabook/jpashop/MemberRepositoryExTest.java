package jpabook.jpashop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest // 실제 스프링 프레임워크를 띄워서 테스트하는 것. 그래서 @Autowired로 빈을 주입받을 수 있음.
public class MemberRepositoryExTest {
    @Autowired
    MemberRepositoryEx memberRepository;

    @Test
    @Transactional // 테스트에 @Transactional이 있으면 테스트가 끝난 후에 ROLLBACK을 해줌. @Rollback(false)를 통해 진짜 넣을 수도 있음.
    public void testMember() throws Exception {
        MemberEx member = new MemberEx();
        member.setUsername("memberA");

        Long savedId = memberRepository.save(member);
        MemberEx findMember = memberRepository.find(savedId);

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

}