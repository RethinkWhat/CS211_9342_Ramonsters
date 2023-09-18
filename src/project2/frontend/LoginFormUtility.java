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
 * @author ROXAS, Johan Rickardo
 * @version 1.00 (16 September 2023)
 * Template for LoginFormUtility object.
 */
public class LoginFormUtility {
    public boolean validate(String userName, char[] password){
        LinkedList<Admin> adminLinkedList = new LinkedList<Admin>();
        try {
            Scanner fileReader = new Scanner(new File("src/project2/backend/adminlist"));
            while (fileReader.hasNext()) {
                String[] lines = fileReader.nextLine().split(",");
                Admin adminObj = new Admin(lines[3], lines[4]);
                adminLinkedList.insert(adminObj);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Admin list not found.");
        }
        Node<Admin> tempPointer = adminLinkedList.getHead();
        Admin checkAccount = new Admin(userName,String.valueOf(password));
        while (tempPointer!=null) {
            if (checkAccount.equals(tempPointer)) {
                //TODO Call Portal method
                System.out.println("LOGGED IN");
                return true;
            }
            tempPointer = tempPointer.getNext();
        }
        return false;
    }

} // end of class LoginFormUtility
