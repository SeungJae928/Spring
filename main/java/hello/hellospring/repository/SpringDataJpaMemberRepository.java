package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JpaRepository는 대부분의 등록, 가입, 조회 등의 메서드가 구현되어 있음
//메서드의 이름만으로 조회를 할 수 있게 해준다. ex) findByName, findByNameAndId ...
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //Jpa가 짜는 query -> select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
