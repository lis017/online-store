package com.lis.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;    //이 변수로 db입출력함수들 사용가능
    @GetMapping("/list")    //list페이지 보여줌
    String list(Model model) {
        List<Item> result = itemRepository.findAll();   //list에 굳이 담는 이유는, 이래야 정상적으로 보인다. 안하면 암호처럼 나옵니다.
        model.addAttribute("items", result);
        return "list.html";
    }

    @GetMapping("/write")   //고객이 db에 저장가능한 페이지 보여줌
    String write() {
        return "write.html";
    }
    @PostMapping("/add")    //write에서 고객이 데이터보내면, db저장후 list페이지 보여줌.
    String writePost(String title, Integer price) {
        Item item = new Item();
        item.setTitle(title);       //private니깐 세터함수 이용.
        item.setPrice(price);
        itemRepository.save(item);
        return "redirect:/list";
    }
    @GetMapping("/detail/{id}") //상품 상세페이지 보여줌.
    String detail() {
        return "detail.html";
    }

}
