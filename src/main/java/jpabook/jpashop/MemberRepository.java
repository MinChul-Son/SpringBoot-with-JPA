package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository // 컴포넌트 스캔의 대상(자동 스프링 빈 등록)
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 커맨드와 쿼리를 분리해라!!
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
