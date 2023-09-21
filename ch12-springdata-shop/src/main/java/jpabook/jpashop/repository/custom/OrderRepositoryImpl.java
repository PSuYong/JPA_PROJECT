package jpabook.jpashop.repository.custom;


import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;


import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author holyeye
 */
public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {

    public OrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> search(OrderSearch orderSearch) {

        QOrder order = QOrder.order;
        QMember member = QMember.member;

        JPQLQuery query = from(order);
        //JPQLQuery<Order> query = new JPAQuery<>();
        //JPQLQuery<Order> query = from(order);

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query
                    //.from(order)
                    .leftJoin(order.member, member)
                    .where(member.name.contains(orderSearch.getMemberName()));
        }

        if (orderSearch.getOrderStatus() != null) {
            query.where(order.status.eq(orderSearch.getOrderStatus()));
        }

        return query.fetch();
    }
}
