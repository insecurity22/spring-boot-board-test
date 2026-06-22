package board.board_spring;

import board.board_spring.domain.Member;
import board.board_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

// MemoryMemberRepository 테스트
// - 클레스 단위 또 전체 클래스를 전부 테스트해볼 수 있다.
// - 일반적으로 빌드 과정에서 테스트를 통과하지 못하면 다음 단계로 진행되지 않는다.
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        // - 테스트는 실행 순서와 관계없이 독립적으로 동작하도록 작성해야 한다.
        // - @AfterEach는 테스트 메서드 실행 후마다 호출된다.
        // - 각 테스트가 끝날 때마다 저장소를 초기화한다.
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("testMember");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByIdName() {
        Member member1 = new Member();
        member1.setName("testMember");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("testMember2");
        repository.save(member2);

        // 1. "testMember2"로 변경하면 테스트가 실패한다.
        Member result = repository.findByName("testMember").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("testMember");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("testMember2");
        repository.save(member2);

        // 1. 기대 값을 3으로 변경하면 테스트가 실패한다.
        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}

// - 반대로 테스트를 먼저 작성하고 구현을 진행하는 방식을 테스트 주도 개발(TDD, Test Driven Development)이라고 한다.
// - 즉, 테스트를 먼저 만들고 이를 통과하도록 구현 코드를 작성하는 방식이다.

// - 일반적으로 구현 코드를 먼저 작성한 뒤, 정상 동작 여부를 검증하기 위해 테스트 코드를 작성한다.
// - 테스트가 많아지면 전체 테스트를 한 번에 실행해 자동으로 검증할 수 있다.
// - 프로젝트 규모가 커질수록 테스트 코드 없이 개발하기는 사실상 어렵다.
// - 물론 가능은 하지만, 버그가 발생할 가능성이 높다.
// - 따라서 테스트 관련 내용은 꼭 깊이 있게 공부하는 것이 좋다.
