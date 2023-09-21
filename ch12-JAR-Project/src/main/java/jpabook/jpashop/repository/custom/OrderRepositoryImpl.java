//package jpabook.jpashop.repository.custom;
//
//import com.mysema.query.jpa.JPQLQuery;
//import jpabook.jpashop.domain.Order;
//import jpabook.jpashop.domain.OrderSearch;
//import jpabook.jpashop.domain.QMember;
//import jpabook.jpashop.domain.QOrder;
//import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
//import org.springframework.util.StringUtils;
//
//import java.util.List;
//
///**
// * @author holyeye
// */
//public class OrderRepositoryImpl extends QueryDslRepositorySupport implements CustomOrderRepository {
//
//    public OrderRepositoryImpl() {
//        super(Order.class);
//    }
//
//    @Override
//    public List<Order> search(OrderSearch orderSearch) {
//
//        QOrder order = QOrder.order;
//        QMember member = QMember.member;
//
//        JPQLQuery query = from(order);
//
//        if (StringUtils.hasText(orderSearch.getMemberName())) {
//            query.leftJoin(order.member, member)
//                    .where(member.name.contains(orderSearch.getMemberName()));
//        }
//
//        if (orderSearch.getOrderStatus() != null) {
//            query.where(order.status.eq(orderSearch.getOrderStatus()));
//        }
//
//        return query.list(order);
//    }
//}



//------------------------------QueryDSL 안 쓰는 코드 ----------------------------------
package jpabook.jpashop.repository.custom;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.domain.OrderStatus;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements CustomOrderRepository {

    private final EntityManager entityManager;

    public OrderRepositoryImpl(EntityManager entityManager) {
        super(Order.class);
        this.entityManager = entityManager;
    }

    @Override
    public List<Order> search(OrderSearch orderSearch) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> orderRoot = criteriaQuery.from(Order.class);
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Join<Object, Object> memberJoin = orderRoot.join("member", JoinType.INNER);
            predicates.add(criteriaBuilder.like(memberJoin.get("name"), "%" + orderSearch.getMemberName() + "%"));
        }

        if (orderSearch.getOrderStatus() != null) {
            predicates.add(criteriaBuilder.equal(orderRoot.get("status"), orderSearch.getOrderStatus()));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Order> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }
}



