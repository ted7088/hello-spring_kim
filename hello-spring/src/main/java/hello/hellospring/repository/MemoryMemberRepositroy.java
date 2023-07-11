package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepositroy implements MemberRepository{  //메모리에만 담는거 (디비에 담기지 않음)

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {  //아이디를 찾아주는 메소드
        return Optional.ofNullable(store.get(id)); //널이더라도 감싸서 return 시킴
    }

    @Override
    public Optional<Member> findByName(String name) {  //이름을 찾아주는 메소드
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
