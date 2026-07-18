package board.board_spring;

import board.board_spring.repository.*;
import board.board_spring.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//    1, 2, 3.
//    private DataSource dataSource;

//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    4.
//    private EntityManager entityManager;
//
//    @Autowired
//    public SpringConfig(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        // 1. return new MemoryMemberRepository();
//        // 2. return new JdbcMemberRepository(dataSource);
//        // 3. return new JdbcTemplateMemberRepository(dataSource);
//        // 4. return new JpaMemberRepository(entityManager);
//    }
}