import java.util.Objects;
import java.util.Scanner;

public class AccountHandler {
    public AccountHandler(){
    }
    String filename ="Accounts.txt";
    FileHandler myHandler = new FileHandler(filename,30);


    public boolean NewAccount(String Username,String Password,String StudentTeacher){
        String Answer;
        myHandler.appendRecord(Username + "," + Password + "," + StudentTeacher, 30 );
        System.out.println("Done");
        return true;
    }

    public char Login(String Username,String password){
        // Read user input
        System.out.println("Starting Login");
        if (myHandler.findRecord(Username + "," + password + "," + "Teacher") == true){
            System.out.println(" teacher account found");
            return 'T';
        }
        if (myHandler.findRecord(Username + "," + password + "," + "Student") == true){
            System.out.println(" student account found");
            return 'S';
        }
        return ' ';
    }
}

