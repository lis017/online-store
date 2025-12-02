package com.lis.shop.Item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller     //페이지 이동위주
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;    //이 변수로 db입출력함수들 사용가능
    private final ItemService itemService;
    private final S3Service s3Service;
    @GetMapping("/list")    //list페이지 보여줌
    String list(Model model) {
        List<Item> result = itemRepository.findAll();   //list에 굳이 담는 이유는, 이래야 정상적으로 보인다. 안하면 암호처럼 나옵니다.
        model.addAttribute("items", result);
        return "list.html";
    }
    //list pagination(페이지 나눠서)
    @GetMapping("/list/page/{abc}")
    String getListPage(Model model, @PathVariable Integer abc) {
        Slice<Item> result = itemRepository.findPageBy(PageRequest.of(abc-1, 5));
        model.addAttribute("items", result);
        return "list.html";
    }

    @GetMapping("/write")   //고객이 db에 상품 저장가능한 페이지 반환
    String write() {
        return "write.html";
    }
    @PostMapping("/add")    //상품정보 db저장
    String writePost(String title, Integer price, String imgURL) {
        itemService.saveItem(title, price, imgURL);
        return "redirect:/list";
    }
    @GetMapping("/detail/{id}")
    String detail(@PathVariable Long id, Model model) {

        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()){
            model.addAttribute("data", result.get());
            return "detail.html";
        } else {
            return "redirect:/list";
        }
    }
    @GetMapping("/edit/{id}")   //상품정보 수정페이지
    String edit(@PathVariable Long id, Model model) {
        Optional<Item> result = itemRepository.findById(id);
        if (result.isPresent()) {
            model.addAttribute("data", result.get());
            return "edit.html";
        } else {
            return "redirect:/list";
        }
    }
    @PostMapping("/edit")
    String editItem(String title, Integer price, Long id) {     //post로 받은 상품정보 수정
        itemService.editItem(title, price, id);
        return "redirect:/list";
    }
    @DeleteMapping("/item") //고객의 데이터삭제 api
    ResponseEntity<String> deleteItem(@RequestParam Long id) {
        itemRepository.deleteById(id);
        return ResponseEntity.status(200).body("삭제완료");     //ajax면 html이나 rediret불가능해서, 메시지전달
    }
    //prisignedURL를 write.html에 반환
    @GetMapping("/presigned-url")
    @ResponseBody
    String getURL(@RequestParam String filename){
        var result = s3Service.createPresignedUrl("test/" + filename);
        return result;
    }
}
