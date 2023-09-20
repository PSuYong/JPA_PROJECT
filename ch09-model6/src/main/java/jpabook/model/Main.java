package jpabook.model;

import jpabook.model.entity.item.Item;
import jpabook.model.entity.item.Book;
import jpabook.model.entity.item.Movie;

import javax.persistence.*;
import java.util.List;

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
            testJoined(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료

    }


    public static void testJoined(EntityManager em){
        Book book = new Book();
        book.setName("Harry Potter and the socerrer's stone");
        book.setAuthor("Joen K Rolling");
        book.setIsbn("1234");
        book.setPrice(12000);
        book.setStockQuantity(200);
        em.persist(book);


        Movie movie = new Movie();
        movie.setName("The Lord of the ring");
        movie.setActor("Elijah Wood");
        movie.setDirector("Peter Jackson");
        //movie.setId(5678L);
        //Id가 자동생성으로 세팅해놨으면 수동으로 설정하면 안된다!
        movie.setPrice(24000);
        movie.setStockQuantity(20);
        em.persist(movie);

        em.flush();
        em.clear();
//
//        List<Book> resultList = em.createQuery("SELECT B FROM Book B",Book.class)
//                .getResultList();
//
//        for (Book b:resultList){
//            System.out.println("Book Name : "+b.getName());
//        }

//        List<Item> resultList1 = em.createQuery("SELECT i FROM Item i",Item.class)
//                .getResultList();
//
//        for (Item i:resultList1){
//            System.out.println("Item Name : "+i.getName());
//        }

//        List<Item> resultList1 = em.createQuery("SELECT i FROM Item i where type(i) in (Book,Movie)",Item.class)
//                .getResultList();
//
//        for (Item i:resultList1){
//            System.out.println("Item Name : "+i.getName());
//        }

        List<Item> resultList1 = em.createQuery("SELECT i FROM Item i where treat(i as Book).author = 'Joen K Rolling'"
                        ,Item.class)
                .getResultList();

        for (Item i:resultList1){
            System.out.println("Item Name : "+i.getName());
        }


    }

}
