//package com.lis.shop;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//
//@ControllerAdvice
//public class MyExceptionHandler {   //타임리프 말고, 일반적인 에러발생시에 전체적으로 실행되는 에러코드
//                                    //데이터만 주고받는 rest api경우에 유용함
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handler() {
//        return ResponseEntity.status(400).body("모든 컨트롤러 에러시 발동");
//    }
//
//}

//이걸하면 다른 예외메시지가 안떠서, 잠시 꺼둠