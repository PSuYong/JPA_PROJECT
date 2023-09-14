package jpabook.start;

import javax.persistence.*;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            logic2(em);  //비즈니스 로직
            System.out.println("******************************************");
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void logic2(EntityManager em){
        Board board = new Board();
        em.persist(board);
        System.out.println("******************************************");
        System.out.println("board.id = "+ board.getId());

//        Board board2 = new Board();
//        em.persist(board2);
//        System.out.println("******************************************");
//        System.out.println("board2.id = "+ board2.getId());
//        for(int i =0; i<50; i++){
//            Board board2 = new Board();
//            em.persist(board2);
//            System.out.println("******************************************2");
//            System.out.println("board2.id = "+ board2.getId());
//        }

    }

    public static void logic(EntityManager em) {

        String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);

        //등록
        em.persist(member);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        //수정
        member.setAge(20);

        //한 건 조회
        Member findMember = em.find(Member.class, id);
        System.out.println("findMember=" + findMember.getUsername() + ", age=" + findMember.getAge());
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");

        //목록 조회
        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        //JPQL(클래스를 대상으로 쿼리문을 실행해라)
        System.out.println("members.size=" + members.size());

        //삭제
        //em.remove(member);

    }
}
