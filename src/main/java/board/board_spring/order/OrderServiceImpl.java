package board.board_spring.order;

import board.board_spring.discount.DiscountPolicy;
import board.board_spring.member.Member;
import board.board_spring.member.MemberRepository;
import jakarta.persistence.EntityNotFoundException;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new EntityNotFoundException("회원이 없습니다."));
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
