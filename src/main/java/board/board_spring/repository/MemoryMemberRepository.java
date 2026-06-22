package board.board_spring.repository;

import board.board_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 1. Map<회원 ID, 회원>
    // - 회원 ID를 Key, 회원 객체를 Value로 저장한다.
    // - 여러 스레드에서 공유된다면 ConcurrentHashMap 사용을 고려할 수 있다.
    private static Map<Long, Member> store = new HashMap<>();

    // 2. 회원 ID 생성용 시퀀스 (0, 1, 2, ...)
    // - 동시성 문제를 고려한다면 long 대신 AtomicLong을 사용하는 것이 안전하다.
    private static long sequence = 0;

    @Override
    public Member save(Member member) {
        // 3. id setting, and save store map
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
