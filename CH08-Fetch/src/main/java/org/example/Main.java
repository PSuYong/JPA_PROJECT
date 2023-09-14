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
            System.out.println("Team ID before persist: " + team1.getId());

// Team 엔티티를 영속화
            em.persist(team1);

// 이제 ID 값이 설정됨
            System.out.println("Team ID after persist: " + team1.getId());

            member1.setTeam(team1);
            em.persist(member1);


            Long key = member1.getId();
            em.flush();
            em.clear();
            em.close();
            emf.close();
            System.out.println("************************************");
            System.out.println("Member ID after persist: " + key);
            printUserAndTeam(key);
            tx.commit();//트랜잭션 커밋


        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }


//    public static String testSave(EntityManager em) {
//        //회원1 저장
//        Member member1 = new Member("회원1");
//        Member member2 = new Member("회원2");
//        em.persist(member1);
//        em.persist(member2);
//
//        Team team1 = new Team( "팀 1");
//        em.persist(team1);
//
//        member1.setTeam(team1) ;
//        member2.setTeam(team1) ;
//
//        String key =  member1.getId();
//
//        return key;
//
//    }

    public static void printUserAndTeam(Long id){

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Member member1 = em.find(Member.class,id);
        System.out.println("ID: " + id);
        System.out.println("MEMBER: " + member1);
        System.out.println("Not YEt Team Info...............");
        Team team = member1.getTeam();
        System.out.println("회원이름 : "+ member1.getUsername());
        System.out.println("소속팀 : "+ team.getName());

        tx.commit();
        System.out.println("Good Bye");

    }

    public static void testReference(EntityManager em,Long id){
        em.close();
        Member member = em.getReference(Member.class,"id");
        member.getUsername();
    }

//    public static void testSave(EntityManager em) {
//
//
//        //회원1 저장
//        Member member1 = new Member("회원1");
//        Member member2 = new Member("회원2");
//        em.persist(member1);
//        em.persist(member2);
//
//        Team team1 = new Team( "팀 1");
//        em.persist(team1);
//
//        member1.setTeam(team1) ;
//        member2.setTeam(team1) ;
//
//    }

//    public static void Fetch(EntityManager em) {
//
//        TypedQuery<Member> query = em.createQuery(
//                "SELECT m FROM Member m WHERE m.username = :username", Member.class);
//        query.setParameter("username", "회원1");
//        Member member = query.getSingleResult();
//
//        Member retreivedmember = em.find(Member.class,member.getId());
//        System.out.println("Member Name:" + retreivedmember.getUsername());
//        System.out.println("Team Id:" + retreivedmember.getTeam().getId());
//        System.out.println("Team Name:" + retreivedmember.getTeam().getName());
//
//    }





}


