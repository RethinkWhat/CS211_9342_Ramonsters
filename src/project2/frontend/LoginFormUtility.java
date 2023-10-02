/**
 * DELETE THIS CLASS AFTER CREATING A CLASS IN THIS PACKAGE
 */
package project2.frontend;

import project2.backend.LinkedList;
import project2.backend.Node;
import project2.referenceclasses.Admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The `LoginFormUtility` class is a utility class for validating user login credentials. It reads admin data from a
 * file, creates an admin linked list, and checks if the provided username and password match any admin account.
 * @author ROXAS, Johan Rickardo
 * @version 1.00 (16 September 2023)
 */
public class LoginFormUtility {
    public static Node<Admin> validate(String userName, char[] password){
        LinkedList<Admin> adminLinkedList = new LinkedList<Admin>();
        try {
            Scanner fileReader = new Scanner(new File("src/project2/backend/adminlist"));
            while (fileReader.hasNext()) {
                String[] lines = fileReader.nextLine().split(",");
                Admin adminObj = new Admin(lines[1],lines[2],lines[3],lines[4],lines[0]);
                adminLinkedList.insert(adminObj);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Admin list not found.");
        }
        Node<Admin> tempPointer = adminLinkedList.getHead();
        Admin checkAccount = new Admin(userName,String.valueOf(password));
        while (tempPointer!=null) {
            if (checkAccount.equals(tempPointer)) {
                return tempPointer;
            }
            tempPointer = tempPointer.getNext();
        }
        return tempPointer;
    }

} // end of class LoginFormUtility
