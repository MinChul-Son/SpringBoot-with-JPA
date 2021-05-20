package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor // 스프링 데이터 jpa에서 엔티티 메니저를 생성자 주입으로 넣을 수 있게 지원해줌.
public class MemberRepository {

//    @PersistenceContext // 엔티티 매니저 주입
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); // find 메서드는 단건 조회, (타입, PK)
    }

    public List<Member> findAll() { // createQuery ==> (JPQL, 반환타입) , JPQL은 엔티티 객체를 대상으로
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) { // 이름으로 조회
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
