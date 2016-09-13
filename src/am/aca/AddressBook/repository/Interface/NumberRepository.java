package am.aca.AddressBook.repository.Interface;

import am.aca.AddressBook.comman.exception.MyException;

import java.sql.SQLException;

/**
 * Created by Armen on 9/10/2016.
 */
public interface NumberRepository {
    void getTelNumbers(Integer id) throws MyException, SQLException;

}
