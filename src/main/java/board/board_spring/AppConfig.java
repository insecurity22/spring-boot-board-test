package board.board_spring;

import board.board_spring.discount.DiscountPolicy;
import board.board_spring.discount.FixDiscountPolicy;
import board.board_spring.member.MemberServiceImpl;
import board.board_spring.order.OrderService;
import board.board_spring.order.OrderServiceImpl;
import board.board_spring.member.MemberRepository;
import board.board_spring.repository.MemoryMemberRepository;
import board.board_spring.member.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /**
     * 회원 저장소 역할
     */
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    /**
     * 회원 서비스 역할
     */
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    /**
     * 주문 서비스 역할 - 저장소, 할인 정책 사용
     */
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * 할인 정책 역할 - 고정 할인 금액 사용
     */
    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
