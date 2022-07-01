package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired //스프링 컨테이너의 멤버와 연결
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") //url 입력하면 실행하는 방식이 get 방식이고 입력했을때 경로가 매핑이 되면 실행한다.
    public String createForm() {return "members/createMemberForm";}
    // /members/new에 진입시 members/create...를 반환하여 createMemberForm.html을 실행?시킨다.

    @PostMapping("/members/new")
    //post 방식으로 실행한다. createMemberForm의 url이 매핑되고 <form>의 method가 post이므로 이 메서드가 실행된다. 일반적으로 데이터 등록은 post방식을 사용
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //홈 화면을 반환
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
