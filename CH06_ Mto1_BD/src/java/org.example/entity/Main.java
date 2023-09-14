package java.org.example.entity;


import org.example.entity.entity.Member;
import org.example.entity.entity.Team;

import javax.persistence.*;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            testSave(em);
            tx.commit();//트랜잭션 커밋
            Fetch(em);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void testSave(EntityManager em) {


        //회원1 저장
        Member member1 = new Member("회원1");
        Member member2 = new Member("회원2");
        em.persist(member1);
        em.persist(member2);

        Team team1 = new Team( "팀 1");
        em.persist(team1);

        member1.setTeam(team1) ;
        member2.setTeam(team1) ;

    }

    public static void Fetch(EntityManager em) {

        TypedQuery<Member> query = em.createQuery(
                "SELECT m FROM Member m WHERE m.username = :username", Member.class);
        query.setParameter("username", "회원1");
        Member member = query.getSingleResult();

        Member retreivedmember = em.find(Member.class,member.getId());
        System.out.println("Member Name:" + retreivedmember.getUsername());
        System.out.println("Team Id:" + retreivedmember.getTeam().getId());
        System.out.println("Team Name:" + retreivedmember.getTeam().getName());

    }





}


