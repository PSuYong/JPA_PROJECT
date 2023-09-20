

import org.Entity.*;
import org.h2.engine.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

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
            bulkTest(em);

            tx.commit();//트랜잭션 커밋


        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void querytest1(EntityManager em){
        Team team1 = new Team();
        team1.setName("WWE CHAMPION");
        em.persist(team1);

        Team team2 = new Team();
        team2.setName("WWE ATTITUDE ERA");
        em.persist(team2);


        Member member1 = new Member();
        member1.setUsername("Chris Jerico");
        member1.setAge(30);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("Eddie guerero");
        member2.setAge(40);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("Booker T");
        member3.setAge(50);
        em.persist(member3);

        Member member4 = new Member();
        member4.setUsername("Hulk Hogan");
        member4.setAge(70);
        em.persist(member4);

        Member member5 = new Member();
        member5.setUsername("Macho Man");
        member5.setAge(65);
        em.persist(member5);

        String jpql = "SELECT m FROM Member as m where m.username='Booker T'";
        String jpql1 = "SELECT m FROM Member m";
        String jpql2 = "SELECT m.username , m.age FROM Member m";

//        TypedQuery<Member> query = em.createQuery(jpql1,Member.class);
//        List<Member> result = query.getResultList();
//        for(Member m:result) {
//            System.out.println("Member Name:" + m.getUsername());
//        }
//
//
// ------------------------------------------------------------------------------------------------
//        Query query = em.createQuery(jpql2);
//        List resultList = query.getResultList();
//        for(Object o :resultList) {
//            Object[] result = (Object[]) o;
//            System.out.println("Member Name : " + result[0]);
//            System.out.println("Member Age : " + result[1]);
//        }
        //Query는 타입이 정확하지 않을때 사용한다.
        //위의 경우는 쿼리문에서 결과값이 둘 이상이라서 이렇게 함

//------------------------------------------------------------------------------------------------
        String username = "Hulk Hogan";
        String jpql3 = "SELECT m FROM Member as m where m.username=:membername";

        TypedQuery<Member> query = em.createQuery(jpql3,Member.class);
        query.setParameter("membername",username);
        //jpql에서 membername이 파라미터 네임으로 오고 그 값으로 헐크 호건이 온다.
        List<Member> result = query.getResultList();
        System.out.println("Member Name : " + result.get(0).getUsername());
        //이름
        System.out.println("Member Name : " + result.get(0).getAge());
    }


    public static void querytest2(EntityManager em) {

        Team team1 = new Team();
        team1.setName("WWE CHAMPION");
        em.persist(team1);


        Member member1 = new Member();
        member1.setUsername("Chris Jerico");
        member1.setAge(30);
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("Eddie guerero");
        member2.setAge(40);
        member2.setTeam(team1);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("Booker T");
        member3.setAge(50);
        member3.setTeam(team1);
        em.persist(member3);

        Product product1 = new Product();
        product1.setName("Chicken");
        product1.setPrice(11000);
        em.persist(product1);

        Product product2 = new Product();
        product2.setName("Pizza");
        product2.setPrice(15000);
        em.persist(product2);

        Address address = new Address();
        address.setCity("Daegu");
        address.setStreet("East-Daegu Station");
        address.setZipcode("12345");

        Address address1 = new Address();
        address1.setCity("Seoul");
        address1.setStreet("Seong-Su Station");
        address1.setZipcode("1234-123");

        Order order1 = new Order();
        order1.setMember(member1);
        order1.setAddress(address);
        order1.setProduct(product1);
        order1.setOrderAmount(20000);
        em.persist(order1);

        Order order2 = new Order();
        order2.setMember(member3);
        order2.setAddress(address1);
        order2.setProduct(product2);
        order2.setOrderAmount(100);
        em.persist(order2);


        em.flush();
        em.clear();

//        String jdql = "Select o.address From Order o";
//        List<Address> addresses = em.createQuery(jdql,Address.class)
//                .getResultList();
//        for(Address add : addresses){
//            System.out.println("Order Address : "+ add.getCity() +" "+ add.getStreet());
//        }
//   ------------------------------------------------------------------------------
//        String jdql1 = "Select m.username, m.age FROM Member m";
//        Query query= em.createQuery(jdql1);
//        List resultList = query.getResultList() ;
//        for (Object o:resultList){
//            Object[] result = (Object[]) o;
//            System.out.println("UserName : " + result[0]);
//            System.out.println("UserAge : " + result[1]);
//        }
        //========================================

        Team team = em.find(Team.class,1L);

        String qlString = "select m from Member m where m.team.name = :name";
        List<Member> resultList = em.createQuery(qlString,Member.class)
                .setParameter("name",team.getName())
                .getResultList();

        System.out.println(resultList);

    }

    public static void querytest3(EntityManager em) {

        Team team1 = new Team();
        team1.setName("WWE CHAMPION");
        em.persist(team1);


        Member member1 = new Member();
        member1.setUsername("Chris Jerico");
        member1.setAge(30);
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("Eddie guerero");
        member2.setAge(40);
        member2.setTeam(team1);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("Booker T");
        member3.setAge(50);
        member3.setTeam(team1);
        em.persist(member3);

        Product product1 = new Product();
        product1.setName("Chicken");
        product1.setPrice(11000);
        em.persist(product1);

        Product product2 = new Product();
        product2.setName("Pizza");
        product2.setPrice(15000);
        em.persist(product2);

        Address address = new Address();
        address.setCity("Daegu");
        address.setStreet("East-Daegu Station");
        address.setZipcode("12345");

        Address address1 = new Address();
        address1.setCity("Seoul");
        address1.setStreet("Seong-Su Station");
        address1.setZipcode("1234-123");

        Order order1 = new Order();
        order1.setMember(member1);
        order1.setAddress(address);
        order1.setProduct(product1);
        order1.setOrderAmount(20000);
        em.persist(order1);

        Order order2 = new Order();
        order2.setMember(member3);
        order2.setAddress(address1);
        order2.setProduct(product2);
        order2.setOrderAmount(100);
        em.persist(order2);


        em.flush();
        em.clear();

//        String jdql = "Select o.address From Order o";
//        List<Address> addresses = em.createQuery(jdql,Address.class)
//                .getResultList();
//        for(Address add : addresses){
//            System.out.println("Order Address : "+ add.getCity() +" "+ add.getStreet());
//        }
//   ------------------------------------------------------------------------------
//        String jdql1 = "Select m.username, m.age FROM Member m";
//        Query query= em.createQuery(jdql1);
//        List resultList = query.getResultList() ;
//        for (Object o:resultList){
//            Object[] result = (Object[]) o;
//            System.out.println("UserName : " + result[0]);
//            System.out.println("UserAge : " + result[1]);

//---------------------------------------------------------------------------------------------
//        String username = "Hulk Hogan";
//
//        String jdql2 = "select m from Member m Where m = :member";
//        List resultList = em.createQuery(jdql2)
//                .setParameter("member",username)
//                .getResultList();
//
//        for (Object o: resultList){
//            Object[] result = (Object[]) o;
//            System.out.println("UserName : " + result[0]);
//            System.out.println("UserAge : " + result[1]);
//        }

        Team team = em.find(Team.class,1L);

        String qlString = "select m from Member m where m.team = :team";
        List<Member> resultList = em.createQuery(qlString,Member.class)
                .setParameter("team",team)
                .getResultList();

        System.out.println(resultList);

    }






    public static void querytest(EntityManager em){

        Team team1 = new Team();
        team1.setName("WWE CHAMPION");
        em.persist(team1);

        Team team2 = new Team();
        team2.setName("WWE ATTITUDE ERA");
        em.persist(team2);


        Member member1 = new Member();
        member1.setUsername("Chris Jerico");
        member1.setAge(30);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("Eddie guerero");
        member2.setAge(40);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("Booker T");
        member3.setAge(50);
        em.persist(member3);

        Member member4 = new Member();
        member4.setUsername("Hulk Hogan");
        member4.setAge(70);
        em.persist(member4);

        Member member5 = new Member();
        member5.setUsername("Macho Man");
        member5.setAge(65);
        em.persist(member5);


        em.flush();
        em.clear();

//        TypedQuery<Member> query =
//                em.createQuery("SELECT m FROM Member m",Member.class);

        TypedQuery<UserDTO> query1 =
                em.createQuery("SELECT new org.Entity.UserDTO(m.username,m.age) FROM Member m",UserDTO.class);

        List<UserDTO> resultList = query1.getResultList();

//        String usernameParam = "User1";
//
//        List<Member> members =
//                em.createQuery("SELECT m FROM Member m where m.username=:username",Member.class)
//                        .setParameter("username",usernameParam)
//                        .getResultList();

        System.out.println("******************************************************123");

        for(UserDTO mem : resultList){
            System.out.println("Member Name:" + mem.getAge());
        }
    }

    public static void testPagingAPI(EntityManager em){
//        Team team = em.createQuery("select t from Team t where t.name = :name",Team.class)
//                .setParameter("name","WWE CHAMPION")
//                .getResultList();
        for(int i =0;i<40;i++){
            Member member = new Member();
            member.setAge(20+i);
            member.setUsername("Member"+i);
            System.out.println("Member Name:"+member.getAge());
            em.persist(member);
            em.flush();
            em.clear();
        }

    }

    public static void innerJoin(EntityManager em){
        Team team1 = new Team();
        team1.setName("WWE CHAMPION");
        em.persist(team1);

        Team team2 = new Team();
        team2.setName("WWE ATTITUDE ERA");
        em.persist(team2);


        Member member1 = new Member();
        member1.setUsername("Chris Jerico");
        member1.setAge(30);
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("Eddie guerero");
        member2.setAge(40);
        member2.setTeam(team1);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("Booker T");
        member3.setAge(50);
        member3.setTeam(team1);
        em.persist(member3);

        Member member4 = new Member();
        member4.setUsername("Hulk Hogan");
        member4.setAge(70);
        member4.setTeam(team2);
        em.persist(member4);

        Member member5 = new Member();
        member5.setUsername("Macho Man");
        member5.setAge(65);
        member5.setTeam(team2);
        em.persist(member5);


        em.flush();
        em.clear();

//        Query query = em.createQuery("select m ,t from Member m join m.team t");
//        List<Object[]> objects = query.getResultList();
//        for(Object[] object : objects){
////            Object[] o = new Object[0];
////            Object[] result = (Object[]) o;
//            Team team = (Team)object[0];
//            Member member = (Member)object[1];
//            System.out.println(team.getName()+member.getUsername());
//            }

        Team team = em.find(Team.class,1L);

        String qlString = "select m from Member m where m.team = :team";
        List<Member> resultList = em.createQuery(qlString,Member.class)
                .setParameter("team",team)
                .getResultList();

        System.out.println(resultList);
    }

    public static void fetchJoin(EntityManager em){
        Team team1 = new Team();
        team1.setName("WWE CHAMPION");
        em.persist(team1);

        Team team2 = new Team();
        team2.setName("WWE ATTITUDE ERA");
        em.persist(team2);


        Member member1 = new Member();
        member1.setUsername("Chris Jerico");
        member1.setAge(30);
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("Eddie guerero");
        member2.setAge(40);
        member2.setTeam(team1);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("Booker T");
        member3.setAge(50);
        member3.setTeam(team1);
        em.persist(member3);

        Member member4 = new Member();
        member4.setUsername("Hulk Hogan");
        member4.setAge(70);
        member4.setTeam(team2);
        em.persist(member4);

        Member member5 = new Member();
        member5.setUsername("Macho Man");
        member5.setAge(65);
        member5.setTeam(team2);
        em.persist(member5);


        em.flush();
        em.clear();

//        TypedQuery<Member> query= em.createQuery("SELECT m from Member m join fetch m.team",Member.class);
//        System.out.println("++++"+ query.getResultList());
//        List<Member> members = query.getResultList();
//        System.out.println(members.size());
//        for(Member m:members){
//            System.out.println(m.getUsername() +"   "+ m.getTeam().getName());
//
//        }

//        TypedQuery<Team> query = em.createQuery("SELECT t from Team t join fetch t.members where t.name = :name",
//                Team.class)
//                .setParameter("name", "WWE CHAMPION");
//        List<Team> Teams = query.getResultList();

        List<Team> Teams = em.createQuery("SELECT DISTINCT t FROM Team t join t.members where  t.name = :name",Team.class)
                .setParameter("name","WWE CHAMPION")
                .getResultList();


        for(Team t: Teams){
            System.out.println("team name = "+t.getName());
            for(Member m:t.getMembers()){
                System.out.println("member name : " + m.getUsername() +", Team ID :"+ t.getId());
            }

        }

//        for(Member m:members){
//            System.out.println(m.getUsername() +"   "+ m.getTeam().getName());
//
//        }
    }

    public static void fetchJoin1(EntityManager em){


        Team team1 = new Team();
        team1.setName("WWE CHAMPION");
        em.persist(team1);


        Member member1 = new Member();
        member1.setUsername("Chris Jerico");
        member1.setAge(30);
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("Eddie guerero");
        member2.setAge(40);
        member2.setTeam(team1);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("Booker T");
        member3.setAge(50);
        member3.setTeam(team1);
        em.persist(member3);

        Product product1 = new Product();
        product1.setName("Chicken");
        product1.setPrice(11000);
        em.persist(product1);

        Product product2 = new Product();
        product2.setName("Pizza");
        product2.setPrice(15000);
        em.persist(product2);

        Address address = new Address();
        address.setCity("Daegu");
        address.setStreet("East Daegu");
        address.setZipcode("12345");

        Order order1 = new Order();
        order1.setMember(member1);
        order1.setAddress(address);
        order1.setProduct(product1);
        order1.setOrderAmount(20000);
        em.persist(order1);

        Order order2 = new Order();
        order2.setMember(member3);
        order2.setAddress(address);
        order2.setProduct(product2);
        order2.setOrderAmount(100);
        em.persist(order2);


        em.flush();
        em.clear();

        List<Team> Teams = em.createQuery("SELECT DISTINCT t FROM Team t join t.members where  t.name = :name",Team.class)
                .setParameter("name","WWE CHAMPION")
                .getResultList();


        for(Team t: Teams){
            System.out.println("team name = "+t.getName());
            for(Member m:t.getMembers()){
                System.out.println("member name : " + m.getUsername() +", Team ID :"+ t.getId());
            }

        }


    }

    public static void bulkTest(EntityManager em){

        Team team1 = new Team();
        team1.setName("WWE CHAMPION");
        em.persist(team1);


        Member member1 = new Member();
        member1.setUsername("Chris Jerico");
        member1.setAge(30);
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member();
        member2.setUsername("Eddie guerero");
        member2.setAge(40);
        member2.setTeam(team1);
        em.persist(member2);

        Member member3 = new Member();
        member3.setUsername("Booker T");
        member3.setAge(50);
        member3.setTeam(team1);
        em.persist(member3);

        Product product1 = new Product();
        product1.setName("Chicken");
        product1.setPrice(11000);
        em.persist(product1);

        Product product2 = new Product();
        product2.setName("Pizza");
        product2.setPrice(15000);
        em.persist(product2);

        Address address = new Address();
        address.setCity("Daegu");
        address.setStreet("East-Daegu Station");
        address.setZipcode("12345");

        Address address1 = new Address();
        address1.setCity("Seoul");
        address1.setStreet("Seong-Su Station");
        address1.setZipcode("1234-123");

        Order order1 = new Order();
        order1.setMember(member1);
        order1.setAddress(address);
        order1.setProduct(product1);
        order1.setOrderAmount(20000);
        em.persist(order1);

        Order order2 = new Order();
        order2.setMember(member3);
        order2.setAddress(address1);
        order2.setProduct(product2);
        order2.setOrderAmount(100);
        em.persist(order2);


        em.flush();
        em.clear();

        String jdql = "select p from Product p where p.name = :name";
        String jdql1 = "update Product p set p.price = p.price*1.1";
        String jdql2 = "select m from Member m";

////      0. DB에는 반영이 되었지만 영속성 컨텍스트에서는 벌크 연산 인식을 못한 경우
//        Product productA = em.createQuery(jdql, Product.class)
//                .setParameter("name","Pizza")
//                .getSingleResult();
//
//        System.out.println("ProductA 수정전 = " + productA.getPrice());
//
//        em.createQuery(jdql1).executeUpdate();
//
//        System.out.println("ProductA 수정후 = " + productA.getPrice());

////      1.em.refresh()를 사용하는 경우
//        Product productA = em.createQuery(jdql, Product.class)
//                .setParameter("name","Pizza")
//                .getSingleResult();
//
//        System.out.println("ProductA 수정전 = " + productA.getPrice());
//
//        em.createQuery(jdql1).executeUpdate();
//        em.refresh(productA);
//
//        System.out.println("ProductA 수정후 = " + productA.getPrice());

//        2. 벌크연산을 먼저 실행할 경우
//            em.createQuery(jdql1).executeUpdate();
//
//            Product productA = em.createQuery(jdql, Product.class)
//            .setParameter("name","Pizza")
//            .getSingleResult();
//
//        System.out.println("ProductA 수정후 = " + productA.getPrice());

//        3. 벌크연산을 수행하고 영속성 컨텍스트를 초기화 하는 경우
//        Product productA = em.createQuery(jdql, Product.class)
//                .setParameter("name","Pizza")
//                .getSingleResult();
//
//        System.out.println("ProductA 수정전 = " + productA.getPrice());
//
//        em.createQuery(jdql1).executeUpdate();
//        em.clear();
//
//        Product updatedProductA = em.createQuery(jdql, Product.class)
//                .setParameter("name", "Pizza")
//                .getSingleResult();
//
//        System.out.println("ProductA 수정후 = " + updatedProductA.getPrice());



        System.out.println(member1.getId());
        Long id = member1.getId();
        em.find(Member.class,id);

        Member memberA = em.createQuery(jdql2,Member.class)
                .getSingleResult();

        System.out.println(memberA.getUsername());



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


