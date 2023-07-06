package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMeberRepositoryTest {

    MemoryMemberRepositroy repositroy = new MemoryMemberRepositroy();
    @AfterEach
    public void afterEach(){
        repositroy.clearStore(); //객체가 다 지워지면서 새로 만들어줌 , store.clear();를 테스트 클래스 하는 곳에도 만들어 줘야함
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repositroy.save(member);

        Member result = repositroy.findById(member.getId()).get();
        assertThat(member).isEqualTo(result); //테스트 검증 (assertThat)
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repositroy.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repositroy.save(member2);

        Member result = repositroy.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){

        Member member1 = new Member();
        member1.setName("spring1");
        repositroy.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repositroy.save(member2);

        List<Member> result = repositroy.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
