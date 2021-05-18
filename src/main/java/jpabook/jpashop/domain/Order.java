package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // 테이블의 이름을 지정, default로 클래스의 첫글자를 소문자로 바꾼 것이 매핑.
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id") // 칼럼과 매핑
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 항상 1:N 관계에서 N(다)쪽에 외래키(FK)가 있고 양방향 연관관계의 주인이다.
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) // 모든 엔티티들은 각자 persist를 해줘야하는데 cascade 옵션을 통해 order가 persist되면 같이 저장
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderData; // 자바8 부터는 @Temporal을 사용하지 않고 LocalDateTime을 쓰면 hibernate가 자동으로 치환

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Enum 타입, 주문상태 [ORDER, CANCEL]

    // 연관관계 메서드 //
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
