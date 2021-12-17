package com.jojoldu.book.spring.web;
import com.jojoldu.book.spring.config.auth.LoginUser;
import com.jojoldu.book.spring.config.auth.dto.SessionUser;
import com.jojoldu.book.spring.service.posts.PostsService;
import com.jojoldu.book.spring.web.Dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        //(SessionUser) httpSession.getAttribute("user") : 로그인 성공시 httpSession.getAttribute("user")에서 값을 가져올 수 있었다.
        //하지만 @LoginUser SessionUser user를 통해 어느 컨트롤러든지 @LoginUser만 사용하면 세션정보를 가져올 수 있게 되었다.
        if (user != null){
            model.addAttribute("userName", user.getName());
        }
        // 세션에 저장된 값이 있을 때만 model이 userName으로 등록, 값이 없으면 model엔 아무값도 없으므로 로그인 버튼이 보이게 된다.
        return "index";
    }
    //Model : 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
    //postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";

    }

}
