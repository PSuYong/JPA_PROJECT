

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

            tx.commit();//트랜잭션 커밋


        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

//    public static void save( EntityManager em){
//        Product productA = new Product();
//        Member member1 = new Member();
//        productA.setId("Product A");
//        productA.setName("상품 A");
//        productA.getMembers().add(member1);
//        em.persist(productA);
//
//
//        member1.setId("member1");
//        member1.setUsername("커트 앵글");
//        member1.getProducts().add(productA);
//        member1.addProduct(productA);
//
//        em.persist(member1);
//
//    }
//
//    public static void find(EntityManager em){
//
//        Member member = em.find(Member.class,"member1");
//        List<Product> products = member.getProducts();
//        for(Product product : products){
//            System.out.println("Product name ="+product.getName());
//        }
//    }
//
//    public static void findInverse(EntityManager em){
//
//        Product product = em.find(Product.class,"Product A");
//        //위에 save 메소드 부분에서 setId의 값이 일치해야 한다. 요주의하자!
//        List<Member> members = product.getMembers();
//        //System.out.println("Member ="+members);
//        if (members != null) {
//            for (Member member : members) {
//                System.out.println("Member =" + member.getUsername());
//            }
//        }
//    }

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


