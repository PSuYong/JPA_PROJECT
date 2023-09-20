package entity;

import javax.persistence.*;
import java.util.List;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 */
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;
    private String name;

    @Embedded Period workPeriod;
    @Embedded Address homeAddress;

    @Embedded Address companyAddress;




}