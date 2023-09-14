package jpabook.start;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
//@SequenceGenerator(
//        name = "BOARD_SEQ_GENERATOR",
//        sequenceName = "BOARD_SEQ",
//        initialValue = 1 ,allocationSize = 1
//)
//@TableGenerator(name = "BOARD_SEQ_GENERATOR",
//        table = "MY_SEQUENCES",
//        pkColumnValue= "BOARD_SEQ" ,allocationSize = 50)

public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,
//    generator = "BOARD_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.TABLE,
//            generator = "BOARD_SEQ_GENERATOR")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    public Long getId(){
        return id;
    }

}
