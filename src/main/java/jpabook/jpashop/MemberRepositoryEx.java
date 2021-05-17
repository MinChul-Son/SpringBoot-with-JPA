package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository // 컴포넌트 스캔의 대상(자동 스프링 빈 등록)
public class MemberRepositoryEx {

    @PersistenceContext
    private EntityManager em;

    public Long save(MemberEx member) {
        em.persist(member);
        return member.getId(); // 커맨드와 쿼리를 분리해라!!
    }

    public MemberEx find(Long id) {
        return em.find(MemberEx.class, id);
    }
}
