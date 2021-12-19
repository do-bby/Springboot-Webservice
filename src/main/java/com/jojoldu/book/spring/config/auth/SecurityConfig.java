package com.jojoldu.book.spring.config.auth;

import com.jojoldu.book.spring.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@RequiredArgsConstructor
@EnableWebSecurity
//Spring Security 설정들을 활성화시켜 줍니다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and().authorizeRequests().antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                .anyRequest().authenticated().and().logout().logoutSuccessUrl("/")
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);

        // csrf().disable().headers().frameOptions().disable() : h2-console화면을 사용하기 위해 해당 옵션 disable
        // authorizeRequests : URL별 권한 권리 설정하는 옵션 시작점(authorizeRequests선언 되야 andMatchers옵션 사용가능)
        // andMatchers : 권한 관리 대상 지정하는 옵션( URL, HTTP 메소드별 관리 가능)
        // "/" 지정 URL들은 permitAll()옵션을 통해 전체 열람 권한, "/api/v1/**"주소를 가진 API는 USER권한 가진 사람만 가능
        // anyRequest : 설정된 값들 이외 나머지 URL들을 나타냄
        // logout().logoutSuccessUrl("/") : 로그아웃 기능에 대한 여러 설정 진입점(로그아웃 성공시 /주소로 이동)
        // oauth2Login : OAuth2 로그인 기능에 대한 여러 설정 진입점
        // userInfoEndpoint : OAuth2 로그인 성공 이후 사용자 정보 가져올떄의 설정 담당
        // userService : 소셜 로그인 성공 시 후속 조치 진행할 UserService인터페이스 구현체 등록

    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(defaultHttpFirewall());
    }
    @Bean
    public HttpFirewall defaultHttpFirewall() {
        return new DefaultHttpFirewall();
    }
}
