package domain;

public class ShippingInfo {
    private String recieverPhoneNumber;
    private String shippingAddress1;
    private String shippingAddress2;
    private String shippingZipcode;

    public String getReceiverName() {
        return receiverName;
    }

    public String getRecieverPhoneNumber() {
        return recieverPhoneNumber;
    }

    public String getShippingAddress1() {
        return shippingAddress1;
    }

    public String getShippingAddress2() {
        return shippingAddress2;
    }

    public String getShippingZipcode() {
        return shippingZipcode;
    }

    private String receiverName;

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public void setRecieverPhoneNumber(String recieverPhoneNumber) {
        this.recieverPhoneNumber = recieverPhoneNumber;
    }

    public void setShippingAddress1(String shippingAddress1) {
        this.shippingAddress1 = shippingAddress1;
    }

    public void setShippingAddress2(String shippingAddress2) {
        this.shippingAddress2 = shippingAddress2;
    }

    public void setShippingZipcode(String shippingZipcode) {
        this.shippingZipcode = shippingZipcode;
    }
}
