package am.aca.AddressBook.comman.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Armen on 9/10/2016.
 */
public class User {
    private String userName;
    private String password ;
    private Integer  id;
    private List<TelNumber> userTelNumberList=new ArrayList<>();
    private Set<User> friendsList=new HashSet<>();
    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
    public User(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<TelNumber> getUserTelNumberList() {
        return userTelNumberList;
    }

    public void setUserTelNumberList(List<TelNumber> userTelTelNumberList) {
        this.userTelNumberList = userTelTelNumberList;
    }

    public Set<User> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(Set<User> friendsList) {
        this.friendsList = friendsList;
    }

    @Override
    public int hashCode() {
        return userName.hashCode();
    }

    @Override
    public boolean equals(Object ob) {
        if (this == ob)
            return true;
        if (ob == null || getClass() != ob.getClass())
            return false;
        User user = (User) ob;
        return userName != null ? userName.equals(user.userName) : user.userName == null
                && (password != null ? password.equals(user.password) : user.password == null);
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", userTelNumberList=" + userTelNumberList +
                ", friendsList=" + friendsList +
                '}';
    }
}

