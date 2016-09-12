package am.aca.AddressBook.controller;

import am.aca.AddressBook.comman.exception.MyException;
import am.aca.AddressBook.comman.model.TelNumber;
import am.aca.AddressBook.comman.model.User;
import am.aca.AddressBook.repository.Implementation.NumberRepositoryImpl;
import am.aca.AddressBook.repository.Implementation.UserRepositoryImpl;
import com.mysql.cj.api.jdbc.Statement;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static am.aca.AddressBook.comman.model.TypeNumber.HOME;
import static am.aca.AddressBook.comman.model.TypeNumber.MOBILE;
import static am.aca.AddressBook.comman.util.MyUtil.*;


/**
 * Created by Armen on 9/10/2016.
 */
public class Controller {
    public static List<User> users=new ArrayList<>();
    static User currentUser;
    static User currentFriend;
    Scanner input = new Scanner(System.in);
    String comman;
    List<String> numberList = new ArrayList<>();

    public void executor() throws MyException, SQLException {

        try {
            properties.load(new FileInputStream(FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }

        printMessage("begin");
        comman = input.nextLine();
 /*       while (true){*/
            switch (comman){
                case "1":
                    signUp();
                    break;
                case "2":
                    signIn(null,null);
                    break;
                case "3":
                    addTelNumber();
                    break;
                case "4":
                    showTelNumbers();
                    break;
                case "5":
                    deleteTelNumber();
                    break;
                case "6":
                    addFriend();
                    break;
                case "7":
                    showFriendList();
                    break;
                case "8":
                    deleteFriend();
                    break;
                case "9":
                    deleteUser();
                    break;
                case "10":
                    executor();
                    break;
                case "11":
                    showUserList();
                    break;
                case "12":
                    changeUserInfo();
                    break;
                case "13":
                    printMessage("help");
                    printMessage("SIGNUPorSIGNIN");
                    break;
                default:
                    printMessage("invalidCommand");
                    printMessage("write.sign.up.or.sign.in");
            }
            comman = input.nextLine();
        }

    private void changeUserInfo() throws MyException {
        UserRepositoryImpl userimpl = new UserRepositoryImpl();
        printMessage("writeID");
        Integer id;
        id = input.nextInt();
        userimpl.editUser(id);
    }


    private void signUp() throws MyException, SQLException {
        Scanner input = new Scanner(System.in);
        String username,
                password,
                comman;
        printMessage("writeUsername");
        username = input.nextLine();

        printMessage("writePassword");
        password = input.nextLine();
        User user = new User(username,password);
        user.setUserName(username);
        user.setPassword(password);

        try{
            Connection connection =  DriverManager.getConnection(URL,PASSWORD,LOGIN);
            Statement st = (Statement) connection.createStatement();

            String line ="insert into user " + "(username,password)"+
                    "values ( '"+username+"','"+password+"')" ;
            st.executeUpdate(line);
            connection.close();
            st.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        printMessage("successfullyCreate");
        comman = input.nextLine();
        user.setId(generateId());
        if(comman.equals("Sign In"))
            signIn(username,password);
        else if(comman.equals("Sign Up"))
            signUp();
        else
            executor();
    }


    private void signIn(String username,String password)throws MyException {
        User user = new User();
        Scanner input = new Scanner(System.in);
        String name;
        String pass;
        printMessage("writeUsername");
        name = input.nextLine();

        printMessage("writePassword");
        pass = input.nextLine();
        int count = 0;
        if ((!(name.equals(username))) || (!(pass.equals(password)))) {
            printMessage("errorUsernameOrPassword");
            ++count;
            signIn(username, password);
        }
        if ((!(name.equals(username))) || (!(pass.equals(password)))) {
            printMessage("errorUsernameOrPassword");
            ++count;
            signIn(username, password);
        }
        if (count != 3) {
            printMessage("successfullyAddedOrShowed");
            if (input.nextLine().equals("Add Tel")) {
                addTelNumber();
            } else if (input.nextLine().equals("Show Tel"))
                showTelNumbers();
            else if (input.nextLine().equals("Sign Out"))
                System.exit(404);
            else if (input.nextLine().equals("Help"))
                printMessage("help");
            else if (input.nextLine().equals("Sign Out"))
                System.exit(404);
        } else
            System.exit(444);
    }
    private void addTelNumber()throws MyException {                                                  // ----------------ADD
        TelNumber telNumber = new TelNumber();
        Scanner input = new Scanner(System.in);
       printMessage("writeTelNumber");
        String number = input.nextLine();

        telNumber.setTelNumber(number);
        //numberRepo.addNumber(number);
        printMessage("successfullyLoggedaddNumberOrShowNumber");
        numberList.add(number);

        if(number.startsWith("374 10")){
            telNumber.setTypeNumber(HOME);
            try{
                Connection connection =  DriverManager.getConnection(URL,PASSWORD,LOGIN);
                Statement st = (Statement) connection.createStatement();

                String line ="insert into telnumber " + "(HomeNumber,MobileNumber)"+
                        "values ( '"+number+"', '"+null+"')" ;
                st.executeUpdate(line);
                connection.close();
                st.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        else{
            telNumber.setTypeNumber(MOBILE);
            try{
                Connection connection =  DriverManager.getConnection(URL,PASSWORD,LOGIN);
                Statement st = (Statement) connection.createStatement();

                String line ="insert into telnumber " + "(HomeNumber,MobileNumber)"+
                        "values ( '"+null+"', '"+number+"')" ;
                st.executeUpdate(line);
                connection.close();
                st.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if(input.nextLine().equals("Add Tel"))
            addTelNumber();
        else if(input.nextLine().equals("Show Tel"))
            showTelNumbers();
        else
            System.exit(444);

    }


    private void addFriend() {
        Scanner input = new Scanner(System.in);
        String friendName;
        printMessage("writeFriendUsername");
        friendName = input.nextLine();
        try{
            Connection connection =  DriverManager.getConnection(URL,PASSWORD,LOGIN);
            Statement st = (Statement) connection.createStatement();

            String line ="insert into friendlist " + "(FriendName)"+
                    "values ( '"+friendName+"')" ;
            st.executeUpdate(line);
            connection.close();
            st.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        printMessage("addOrShow");
        if(input.nextLine().equals("Add Friend"))
            addFriend();
        else
            System.exit(4);
    }
    private  void showUserList() throws MyException {                                                       //------------SHOW
        UserRepositoryImpl usImpl = new UserRepositoryImpl();
        usImpl.getUser(null);
    }

    private void showTelNumbers() throws MyException {
        NumberRepositoryImpl numImpl = new NumberRepositoryImpl();
        printMessage("writeID");
        Integer id;
        id = input.nextInt();

        numImpl.getTelNumbers(id);

    }

    private void showFriendList()throws MyException {

        try {
            Connection connection = DriverManager.getConnection(URL, PASSWORD, LOGIN);
            Statement statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from addressbook.friendlist");
            while(resultSet.next()) {
                int friendID = resultSet.getInt("id");
                String friendName = resultSet.getString("FriendName");
                System.out.println("id =[" + friendID + "]" +" FriendName =[" + friendName + "]");
            }
            connection.close();
            statement.close();
            resultSet.close();
            System.exit(404);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteUser() throws MyException,SQLException {                                                        // ----------------DELETE
        UserRepositoryImpl userImpl = new UserRepositoryImpl();
        printMessage("deleteUser");
        Integer id = input.nextInt();
        userImpl.deleteUser(id);
    }

    private void deleteFriend() {
        try{
            Scanner input = new Scanner(System.in);
            String friendname;
            printMessage("deleteFriend");
            friendname = input.nextLine();
            Connection connection = DriverManager.getConnection(URL, PASSWORD, LOGIN);
            Statement statement = (Statement) connection.createStatement();
            String sql = "DELETE FROM addressbook.friendlist where FriendName = '" + friendname + "'" ;
            statement.executeUpdate(sql);
            sql = "select * from addressbook.friendlist";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("•••••••••After deleting•••••••••");
            while(resultSet.next()) {
                int friendID = resultSet.getInt("id");
                String friendName = resultSet.getString("FriendName");
                System.out.print("ID: [" + friendID + "]");
                System.out.println(" FriendName: [" + friendName + "]");
            }
            connection.close();
            statement.close();
            resultSet.close();
            System.exit(404);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void deleteTelNumber() {
        printMessage("homeORmobile");
        Scanner input = new Scanner(System.in);
        String number = input.nextLine();
        if(number.equals("Home"))
            try{
                String homeNumber;
                printMessage("deleteHomeNumber");
                homeNumber = input.nextLine();
                Connection connection = DriverManager.getConnection(URL, PASSWORD, LOGIN);
                Statement statement = (Statement) connection.createStatement();
                String sql = "DELETE FROM addressbook.telnumber where HomeNumber = '" + homeNumber + "'" ;
                statement.executeUpdate(sql);
                sql = "select * from addressbook.telnumber";
                ResultSet resultSet = statement.executeQuery(sql);

                System.out.println("•••••••••After deleting•••••••••");
                while(resultSet.next()) {
                    int telNumberID = resultSet.getInt("id");
                    homeNumber = resultSet.getString("HomeNumber");
                    String mobileNumber = resultSet.getString("MobileNumber");

                    System.out.print("ID: [" + telNumberID + "]");
                    System.out.print(" HomeName: [" + homeNumber + "]");
                    System.out.println(" MobileName: [" + mobileNumber + "]");

                }
                connection.close();
                statement.close();
                resultSet.close();
                System.exit(404);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            else if(number.equals("Mobile"))
            try{
                printMessage("deleteMobileNumber");
                String mobileNumber = input.nextLine();
                Connection connection = DriverManager.getConnection(URL, PASSWORD, LOGIN);
                Statement statement = (Statement) connection.createStatement();
                String sql = "DELETE FROM addressbook.telnumber where MobileNumber = '" + mobileNumber + "'" ;
                statement.executeUpdate(sql);
                sql = "select * from addressbook.telnumber";
                ResultSet resultSet = statement.executeQuery(sql);
                while(resultSet.next()) {
                    int telNumberID = resultSet.getInt("id");
                    String homeNumber = resultSet.getString("HomeNumber");
                    mobileNumber = resultSet.getString("MobileNumber");

                    System.out.println("•••••••••After deleting•••••••••");
                    System.out.print("ID: [" + telNumberID + "]");
                    System.out.print(" HomeName: [" + homeNumber + "]");
                    System.out.println(" MobileName: [" + mobileNumber + "]");

                }
                connection.close();
                statement.close();
                resultSet.close();
                System.exit(404);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            else System.exit(404);
    }

}
