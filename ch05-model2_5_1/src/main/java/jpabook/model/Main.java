package jpabook.model;

import jpabook.model.entity.Member;
import jpabook.model.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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
            testSave(em);//TODO 비즈니스 로직
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void testSave(EntityManager em) {

        //팀 1 저장
        Team team1 = new Team("team1", "팀 1");
        em.persist (team1);
        //회원1 저장
        Member member1 = new Member("member 1" , "회원1");
        member1.setTeam(team1) ; //연관관계 실정 me:nberl -> teaml
        em.persist(member1);
        //회원22 저장
        Member member2 = new Member("member 2" , "회원2");
        member2.setTeam(team1) ; //연관관계 실정 me:nberl -> teaml
        em.persist(member2);
    }

}


