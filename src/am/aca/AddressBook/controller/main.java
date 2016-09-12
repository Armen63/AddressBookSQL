package am.aca.AddressBook.controller;

import am.aca.AddressBook.comman.exception.MyException;

import java.sql.SQLException;

/**
 * Created by Armen on 9/10/2016.
 */
public class main {
    public static void main(String[] args) throws MyException, SQLException {
        Controller start = new Controller();
        start.executor();
    }
}
