package domain;

import javax.persistence.*;

@Embeddable
public class ShippingInfo {
    // 각 용도에 맞는 도메인 밸류 타입으로 변경 했음
    /*
    private String recieverPhoneNumber;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingZipcode;
    private String receiverName;
    */

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "zipCode", column = @Column(name = "shipping_zipcode")),
                    @AttributeOverride(name = "address1", column = @Column(name = "shipping_addr1")),
                    @AttributeOverride(name = "address2", column = @Column(name = "shipping_addr2")),
            }
    )
    private Address address;

    @Column(name = "shipping_message")
    private String message;

    @Embedded
    private Receiver receiver;


    public String getAddress() {
        return "주소";
    }
}
