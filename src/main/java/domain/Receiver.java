package domain;

import javax.persistence.Column;

/**
 * 받는 사람의 밸류 타입
 */
public class Receiver {
    @Column(name = "receiver_name")
    private String name;
    @Column(name = "receiver_phone")
    private String phoneNumber;

    // JPA를 적용하기 위해 기본 생성자 추가
    protected Receiver() {
    }

    public Receiver(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!(other instanceof Receiver)) {
            return false;
        }

        Receiver that = (Receiver) other;
        return this.name.equals(that.name) && this.phoneNumber.equals(that.phoneNumber);
    }
}
