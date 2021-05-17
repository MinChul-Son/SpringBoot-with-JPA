package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { //JPA 스펙상 만든 코드
    }
    public Address(String city, String street, String zipcode) { // Getter만 열어두고 생성자를 통해 생성될 때만 값을 설정하도록 설계
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }


}
