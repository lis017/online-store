package com.lis.shop.Item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


//인터페이스를 가져왔기에, 안보여도 db관련입출력 함수들이 많이 있는 상태임. 그거 쓰면됩니다.
//extends는 가져올 클래스를 복사하는 효과. 상속이라 부름
public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findPageBy(Pageable page);       //전체탐색o, 느림
    Slice<Item> findSliceBy(Pageable page);     //전체탐색x, 빠름
    List<Item> findAllByTitleContains(String title);

    @Query(value = "select * from item where match(title) against(?1)", nativeQuery = true)
    List<Item> rawQuery1(String text);
}
