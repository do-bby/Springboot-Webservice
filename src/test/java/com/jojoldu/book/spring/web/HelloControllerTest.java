package com.jojoldu.book.spring.web;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class) // 테스트를 진행할 떄 Junit에 내장된 실행자 외에 다른 실행자 실행, 스프링 부트 테스트와 Junit사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class)
// 여러 스프링 테스트 어노테이션 중 , web에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입 받습니다.
    private MockMvc mvc; // 웹 API를 테스트할 떄 사용, 스프링 MVC테스트 시작점

    @Test
    public void hello가리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello주소로 get요청
                .andExpect(status().isOk()) // mvc.perform의 결과 검증(status 검증 ex) 200,400)
                .andExpect(content().string(hello)); // mvc.perform의 결과 검증(응답 본문의 내용 검증)
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk()).andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }

    //param : API테스트할 떄 사용될 요청 파라미터를 설정(값은 String만 허용)
    //jsonPath : JSON응답값을 필드별로 검증할 수 있는 메소드($를 기준으로 필드명 명시)


}