package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //테스트를 스프링과 동시에 실행한다.
//이런 어노테이션이 존재하지만 이를 사용하지 않고 단위테스트를 잘 구현하는 것도 매우 중요하다.
@Transactional //테스트케이스에 사용할 시 테스트를 완료한 뒤에 데이터를 롤백한다
class MemberServiceIntTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test //실제 프로젝트에 영향은 주지않고 테스트만 한다.
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원() { //개인 프로젝트 테스트의 경우 한글로 해도 문제는 없음
        //given
        Member member1 =new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원");
//        }
        //then
    }
}