package project2.frontend;

import project2.backend.LinkedList;
import project2.backend.Node;
import project2.referenceclasses.Course;
import project2.referenceclasses.Student;
import project2.referenceclasses.Year;

/**
 * @author ROXAS, LACANILAO, JASMIN, TANK
 * @version 1.00 (16 September 2023)
 * Template for object Main.
 * This class contains the main graphical user-interface of the program
 */
public class Main {
    private final Resources resources = new Resources();
    public static LinkedList<Year> computerScience = StudentUtility.createYearLinkedList("BSCS");
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

        // TODO: Supporting methods/code.
    } // end of run method

    public static Student findStudent(String studentID) {
        Student studentObj = new Student("2233293", "Rithik", "Tank", computerScience);
        Student studentObj1 = new Student("2233375", "Johan", "Roxas", computerScience);
        Student studentObj2 = new Student("2230153", "Patrick", "Lacanilao", computerScience);
        Student studentObj3 = new Student("2230748", "Ramon", "Jasmin", computerScience);
        studentLinkedList.insert(studentObj);
        studentLinkedList.insert(studentObj1);
        studentLinkedList.insert(studentObj2);
        studentLinkedList.insert(studentObj3);

        return search(studentID);
    }

    public static Student search(String studentID) {
        Node<Student> curr = studentLinkedList.getHead();

        for (int i = 0; i < studentLinkedList.getSize(); i++) {
            if (curr.getData().getIdNumber().equals(studentID)) {
                return curr.getData();
            }
            curr = curr.getNext();
        }
        return null;
    }
} // end of class Main
