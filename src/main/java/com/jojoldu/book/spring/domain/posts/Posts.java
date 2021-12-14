package com.jojoldu.book.spring.domain.posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    //@Entity : 테이블과 링크될 클래스
    //@Id : 해당 테이블의 PK필드
    //@GeneratedValue : PK의 생성 규칙(스프링부트2.0에서는 GenerationType.IDENTITY옵션 추가시 auto_increment
    //@Column : 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    //Entity클래스에서는 Setter메소드 만들지 않는다!
    //해당 필드 값의 변경이 필요하면 메소드를 직접 추가
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
