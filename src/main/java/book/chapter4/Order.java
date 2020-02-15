package book.chapter4;

import javax.persistence.*;
import java.util.List;

public class Order {
    @Column(name = "total_amounts")
    @Convert(converter = MoneyConverter.class)
    private Money totalAmounts;

    @ElementCollection
    @CollectionTable(name = "order_line", joinColumns = @JoinColumn(name = "order_number"))
    @OrderColumn(name = "line_idx")
    private List<OrderLine> orderLines;
}

