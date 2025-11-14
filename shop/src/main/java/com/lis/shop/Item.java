package com.lis.shop;

import jakarta.persistence.*;


@Entity //entity로만든 클래스는 테이블하나 만드는것(클래스 이름으로)
//이 클래스가 db랑 연동돼있다 보면됨.
public class Item {
    //여기 변수는 컬럼 만드는거(테이블내의 옵션이름같은)

    //gene어노로 상품추가마다 자동으로 id에 +1 된 값 지정
    //@id가 primary key설정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 200)   //제약조건 추가 //column으로 여러 제약조건 추가 가능
    public String title;
    public Integer price;  //int는 JPA에서 불가
}
