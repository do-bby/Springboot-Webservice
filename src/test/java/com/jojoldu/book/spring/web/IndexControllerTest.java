package com.jojoldu.book.spring.web;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.notification.RunListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void 메인페이지로딩() {
        //when
        String body = this.restTemplate.getForObject("/",String.class);
        //then
        assertThat(body).contains("스프링부트로 시작하는 웹 서비스");

    }
    //실제로 URL 호출시 페이지 내용이 제대로 호출되는지에 대한 테스트
    //TestRestTemplate를 통해 "/"를 호출했을 떄 index.mustache에 "스프링 부트로 시작하는 웹 서비스"문자열이 포함되어있는지 비교
}
