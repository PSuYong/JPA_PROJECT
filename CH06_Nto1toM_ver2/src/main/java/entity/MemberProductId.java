package entity;

import java.io.Serializable;
import java.util.Objects;

public class MemberProductId implements Serializable {

    private String member;
    private String product;
    //hashCode & Equals


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MemberProductId that = (MemberProductId) obj;
        return member.equals(that.member) && product.equals(that.product);
    }

    @Override
    public int hashCode(){
        return Objects.hash(member, product);
    };
}
