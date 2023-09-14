package jpabook.model;

import jpabook.model.entity.item.Album;
import jpabook.model.entity.item.Book;
import jpabook.model.entity.item.Movie;
import jpabook.model.entity.item.Item;

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
            testSingletable(em);//TODO 비즈니스 로직
            tx.commit();//트랜잭션 커밋
            //find(em);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void testSingletable(EntityManager em){
        Book book = new Book();
        book.setAuthor("Hurricane");
        book.setIsbn("ASS1234");

        Album album = new Album();
        album.setArtist("Booker T");
        album.setEtc("Scissors Kick");

        Movie movie = new Movie();
        movie.setActor("Shawn Michales");
        movie.setDirector("Hulk Hogan");



        em.persist(book);
        em.persist(album);
        em.persist(movie);
        em.flush();

        Long key = book.getId();
        System.out.println("#########################################    " + key);
    }

//    public static void find(EntityManager em){
//        Book foundBook = new Book();
//        foundBook = em.find(Book.class,key);
//        System.out.println("Product name ="+foundBook.getAuthor());
//    }


//    public static void save( EntityManager em){
//
//        Item item = new Item();
//        Album album = new Album();
//        Movie movie = new Movie();
//        Book book = new Book();
//
//        em.persist(item);
//        em.persist(album);
//        em.persist(movie);
//        em.persist(book);
//
//
//    }

}
