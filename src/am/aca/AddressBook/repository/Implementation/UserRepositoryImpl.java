package am.aca.AddressBook.repository.Implementation;

import am.aca.AddressBook.comman.exception.MyException;
import am.aca.AddressBook.comman.model.User;
import am.aca.AddressBook.repository.Interface.UserRepository;
import com.mysql.cj.api.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static am.aca.AddressBook.comman.util.MyUtil.*;

/**
 * Created by Armen on 9/11/2016.
 */
public class UserRepositoryImpl implements UserRepository {
    @Override
    public User addUser(User user) throws MyException {
        return null;
    }

    @Override
    public void editUser(Integer id) throws MyException {
        Scanner input = new Scanner(System.in);
        String username,
                password;
        printMessage("writeUsername");
        username = input.nextLine();
        printMessage("writePassword");
        password = input.nextLine();
        try {
            Connection connection = DriverManager.getConnection(URL, PASSWORD, LOGIN);
            Statement statement = (Statement) connection.createStatement();
            String update =  ("UPDATE user SET Username = '"+ username + "', Password = '" + password +"' where id =" + id);
            ResultSet resultSet = statement.executeQuery(update);
            while(resultSet.next()) {
                int userID = resultSet.getInt("id");
                username = resultSet.getString("Username");
                password = resultSet.getString("Password");
                System.out.println("id = " + userID + " Username = " + username + " Password " + password);
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Integer id) throws MyException {/// etam gam esi dzemmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
        try {
            Connection connection = DriverManager.getConnection(URL, PASSWORD, LOGIN);
            Statement statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from addressbook.user where id = " + id);
            while(resultSet.next()) {
                int userID = resultSet.getInt("id");
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                System.out.println("id = " + userID + " Username = " + username + " Password " + password);
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getUser(Integer id) throws MyException {
        Scanner input = new Scanner(System.in);
        String comman;
        id = input.nextInt();
        try {
            Connection connection = DriverManager.getConnection(URL, PASSWORD, LOGIN);
            Statement statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from addressbook.user where id = " + id );

            while (resultSet.next()) {
                System.out.print(resultSet.getString(1) + " ");
                System.out.print(resultSet.getString(2) + " ");
                System.out.print(resultSet.getString(3) + " ");
                System.out.print("\n");

            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
