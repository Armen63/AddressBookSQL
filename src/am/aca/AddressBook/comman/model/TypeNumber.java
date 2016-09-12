package am.aca.AddressBook.comman.model;

/**
 * Created by Armen on 9/10/2016.
 */
public enum TypeNumber {
    HOME(1),
    MOBILE(2);
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    TypeNumber(int id) {
        this.id = id;
    }

}