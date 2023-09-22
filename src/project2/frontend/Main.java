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
        resources.loadFonts();

        // TODO: Supporting methods/code.
    } // end of run method

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
    public static void addStudent(Student newStudent){
        studentLinkedList.insert(newStudent);
    }

    public static void removeStudent(String idNumber){
        Node<Student> curr = studentLinkedList.getHead();
    }
} // end of class Main
