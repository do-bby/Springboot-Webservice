package com.jojoldu.book.spring.service.posts;
import com.jojoldu.book.spring.domain.posts.PostsRepository;
import com.jojoldu.book.spring.web.Dto.PostsSaveRequestDto;
import com.jojoldu.book.spring.web.Dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

}
