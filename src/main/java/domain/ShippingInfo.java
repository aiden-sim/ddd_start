package domain;

public class ShippingInfo {
    // 각 용도에 맞는 도메인 밸류 타입으로 변경 했음
    /*
    private String recieverPhoneNumber;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingZipcode;
    private String receiverName;
    */

    private Receiver receiver;
    private Address address;

}
