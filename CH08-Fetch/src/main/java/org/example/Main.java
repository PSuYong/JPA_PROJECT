package org.example;

import org.example.entity.Member;
import org.example.entity.Team;

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
            Member member1 = new Member("회원1");
            Team team1 = new Team("팀 1");
            em.persist(team1);
            member1.setTeam(team1);
            em.persist(member1);

            Long key = member1.getId();
            em.flush();
            tx.commit();
            em.clear();
            em.close();
            //emf.close();



            //emf = Persistence.createEntityManagerFactory("jpabook");
            em = emf.createEntityManager();

            printUserAndTeam(em,key);
            tx.commit();//트랜잭션 커밋


        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }


    public static void printUserAndTeam(EntityManager em ,Long id){

//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
//        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member memberA = em.find(Member.class,id);
        System.out.println("MEMBER: " + memberA);
        System.out.println("Not YEt Team Info...............");
        Team team = memberA.getTeam();
        System.out.println("회원이름 : "+ memberA.getUsername());
        System.out.println("소속팀 : "+ team.getName());

        tx.commit();
        System.out.println("Good Bye");

    }

    public static void testReference(EntityManager em,Long id){
        em.close();
        Member member = em.getReference(Member.class,"id");
        member.getUsername();
    }







}


