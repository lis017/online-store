package com.lis.shop.Item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity //entity로만든 클래스는 테이블하나 만드는것(클래스 이름으로)
//이 클래스가 db랑 연동돼있다 보면됨.
@ToString   //Lombok인데, 이걸통해 tostring하면 모든변수출력가능

//이런거 쓰는 이유는, 로직에 추가 보안요소를 추가할수있어서 이다.(변수 API느낌) (이런거 추가하려면 따로 정의하긴 해야함)
@Getter     //변수가 private일때, 이걸 다른곳에서 출력가능케하는 함수. 를 쓸수있게해줌.
@Setter     //변수가 private일때, 이걸 다른곳에서 수정가능케하는 함수. 를 쓸수있게해줌.
public class Item {
    //여기 변수는 컬럼 만드는거(테이블내의 옵션이름같은)

    //gene어노로 상품추가마다 자동으로 id에 +1 된 값 지정
    //@id가 primary key설정
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(length = 200)   //제약조건 추가 //column으로 여러 제약조건 추가 가능
    private String title;
    private Integer price;  //int는 JPA에서 불가
    private String username;
    private String imgURL;

}
