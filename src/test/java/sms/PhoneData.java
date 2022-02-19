package sms;

public class PhoneData {
    private String numberId;
    private String phoneNumber;

    public PhoneData(String numberId, String phoneNumber) {
        this.numberId = numberId;
        this.phoneNumber = phoneNumber;
    }

    public String getNumberId() {
        return numberId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
