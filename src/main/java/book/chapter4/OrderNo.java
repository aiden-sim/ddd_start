package book.chapter4;

import javax.persistence.Column;
import java.io.Serializable;

public class OrderNo implements Serializable {
    @Column(name = "order_number")
    private String number;

    public boolean is2ndGeneration() {
        return number.startsWith("N");
    }
}
