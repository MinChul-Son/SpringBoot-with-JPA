package jpabook.jpashop;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class MemberEx {

    @Id @GeneratedValue // 값이 자동으로 증가(Auto_Increase)
    private Long id;
    private String username;
}
