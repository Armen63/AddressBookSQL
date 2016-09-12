package am.aca.AddressBook.repository.Implementation;

import am.aca.AddressBook.comman.exception.MyException;
import am.aca.AddressBook.repository.Interface.NumberRepository;
import com.mysql.cj.api.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static am.aca.AddressBook.comman.util.MyUtil.LOGIN;
import static am.aca.AddressBook.comman.util.MyUtil.PASSWORD;
import static am.aca.AddressBook.comman.util.MyUtil.URL;

/**
 * Created by Armen on 9/12/2016.
 */
public class NumberRepositoryImpl implements NumberRepository {
    @Override
    public void getTelNumbers(Integer id) throws MyException {
        try {
            Connection connection = DriverManager.getConnection(URL, PASSWORD, LOGIN);
            Statement statement = (Statement) connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from addressbook.telnumber where id = " + id);
            while(resultSet.next()) {
                int telNumberId = resultSet.getInt("id");
                String homeNumber = resultSet.getString("HomeNumber");
                String mobileNumber = resultSet.getString("MobileNumber");
                System.out.println("id = " + telNumberId + " HomeNumber = " + homeNumber + " MobileNumber " + mobileNumber);
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
