package com.lis.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {  //데이터 전송전에 작업하는곳(service)
    private final ItemRepository itemRepository;

    //상품 저장
    public void saveItem(String title, Integer price, String imgURL){
        if (price < 0) {
            throw new RuntimeException("음수 안됩니다");      //가격이 음수일때 예외처리
        }
        Item item = new Item();
        item.setTitle(title);   //private니깐 세터함수 이용.
        item.setPrice(price);
        item.setImgURL(imgURL);
        itemRepository.save(item);
    }

    public void editItem(String title, Integer price, Long id) {    //상품정보 수정 기능(post 들어올때)
        if (title.length() > 100) {
            throw new RuntimeException("title 100자이상 안됩니다");      //title 너무 클때 예외처리
        }
        Item item = new Item();
        item.setId(id);
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
    }
}