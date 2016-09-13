package am.aca.AddressBook.repository.Interface;

import am.aca.AddressBook.comman.exception.MyException;
import am.aca.AddressBook.comman.model.User;

import java.sql.SQLException;

/**
 * Created by Armen on 9/10/2016.
 */
public interface UserRepository {
    User addUser(User user)throws MyException;
    void editUser(Integer id) throws MyException, SQLException;
    void deleteUser(Integer id) throws MyException, SQLException;
    void getUser(Integer id) throws MyException, SQLException;
}
