package am.aca.AddressBook.comman.model;

/**
 * Created by Armen on 9/10/2016.
 */
public class TelNumber {
    String telNumber;
    TypeNumber typeNumber;
    Integer telId;

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public TypeNumber getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(TypeNumber typeNumber) {
        this.typeNumber = typeNumber;
    }

    public Integer getTelId() {
        return telId;
    }

    public void setTelId(Integer telId) {
        this.telId = telId;
    }
}