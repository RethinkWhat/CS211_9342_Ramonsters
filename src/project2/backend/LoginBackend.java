package project2.backend;

import project2.frontend.LoginForm;
import project2.referenceclasses.Admin;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class LoginBackend {
    public static void main(String[] args) {
        LoginForm login = new LoginForm();
    }
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
                new MainBackend().display();
                return true;
            }
            tempPointer = tempPointer.getNext();
        }
        return false;
    }
}
