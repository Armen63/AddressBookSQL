package am.aca.AddressBook.comman.util;

import java.util.Properties;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * Created by Armen on 9/10/2016.
 */
public class MyUtil {
    public final static String URL = "jdbc:mysql://localhost:3306/addressbook?autoReconnect=true&useSSL=false";
    public final static String LOGIN = "root";
    public final static String PASSWORD = "root";
    private static Integer nextUserID = 1;
    public static Integer generateUserID(){
        return ++nextUserID;
    }

    private static Integer nextTelNumerID = 1;
    public static Integer generateTelNumerID(){
        return ++nextTelNumerID;
    }

    public static final String FILE_PATH ="C:\\Users\\Armen\\IdeaProjects\\AddressBookSQL\\src\\am\\aca\\AddressBook\\comman\\resource\\myProperties.properties";
    public static Properties properties=new Properties();


    public static void printMessage(String key) {
        System.out.print(properties.getProperty(key));
    }

    private static Integer next_id=1;
    public static Integer generateId(){
        return next_id++;
    }

}
