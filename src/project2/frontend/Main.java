package project2.frontend;

import project2.backend.LinkedList;
import project2.backend.Node;
import project2.referenceclasses.Course;
import project2.referenceclasses.Student;
import project2.referenceclasses.Year;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author ROXAS, LACANILAO, JASMIN, TANK
 * @version 2.00 (24 September 2023)
 * Template for object Main.
 * This class contains the main graphical user-interface of the program
 */
public class Main {
    /**
     * Resources used (fonts, colors, padding, layouts, etc.) for all GUI classes.
     */
    private final Resources resources = new Resources();

    /**
     * TODO: Documentation
     */
    public static LinkedList<Year> computerScience = StudentUtility.createYearLinkedList("BSCS");

    /**
     * TODO: Documentation
     */
    public static LinkedList<Student> studentLinkedList = new LinkedList<Student>();

    /**
     * Main entry point of the program.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Main main;
        try {
            main = new Main();
            main.run(); // runs backend code
            new LoginForm(); // invokes login form components
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } // end of try-catch
    } // end of main method

    /**
     * Invokes the backend code.
     */
    private void run() {
        resources.loadFonts();

        Student studentObj = new Student("2233293", "Rithik", "Tank", computerScience);
        Student studentObj1 = new Student("2233375", "Johan Rickardo", "Roxas", computerScience);
        Student studentObj2 = new Student("2230153", "Marvin Patrick", "Lacanilao", computerScience);
        Student studentObj3 = new Student("2230748", "Ramon Emmiel", "Jasmin", computerScience);
        Student studentObj4 = new Student("2233205", "Sebastian", "Siccuan", computerScience);
        studentLinkedList.insert(studentObj);
        studentLinkedList.insert(studentObj1);
        studentLinkedList.insert(studentObj2);
        studentLinkedList.insert(studentObj3);
        studentLinkedList.insert(studentObj4);
    } // end of run method

    /**
     * @TODO: Documentation
     * @param studentID
     * @return
     */
    public static Student search(String studentID) {
        Node<Student> curr = studentLinkedList.getHead();

        for (int i = 0; i < studentLinkedList.getSize(); i++) {
            if (curr.getData().getIdNumber().equals(studentID)) {
                return curr.getData();
            }
            curr = curr.getNext();
        } // end of for
        return null;
    } // end of search method
} // end of class Main
