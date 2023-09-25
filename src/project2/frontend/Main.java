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
    public static LinkedList<Student> studentLinkedList = new LinkedList();

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

        Student studentObj = new Student("2233293", "Rithik", "Tank",  StudentUtility.createYearLinkedList("BSCS"));
        studentObj.getYearList().getHead().getData().getFirstSemSemesterList().getHead().getData().setGrade(100);
        Student studentObj1 = new Student("2233375", "Johan Rickardo", "Roxas",  StudentUtility.createYearLinkedList("BSCS"));
        Student studentObj2 = new Student("2230153", "Marvin Patrick", "Lacanilao",  StudentUtility.createYearLinkedList("BSCS"));
        Student studentObj3 = new Student("2230748", "Ramon Emmiel", "Jasmin",  StudentUtility.createYearLinkedList("BSCS"));
        Student studentObj4 = new Student("2233205", "Sebastian", "Siccuan",  StudentUtility.createYearLinkedList("BSCS"));
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
            if (curr != null && curr.getData().getIdNumber().equals(studentID)) {
                return curr.getData();
            }
            if (curr != null) {
                curr = curr.getNext();
            }
        } // end of for
        return null;
    } // end of search

    public static void addStudent(String studentID, String firstName, String lastName, LinkedList<Year> yearList){
        studentLinkedList.insert(new Student(studentID, firstName, lastName, yearList));
        System.out.println(studentLinkedList.getSize());
    }

    public static void removeStudent(String studentID){
        studentLinkedList.delete(search(studentID));
        System.out.println(studentLinkedList.getSize());
    }

    private java.util.LinkedList<Course> populateChecklist(int year, int sem) {
        java.util.LinkedList<Course> courses = new java.util.LinkedList<>();
        BufferedReader bufferedReader;
        try {
            if (year == 1) {
                if (sem == 1) {
                    bufferedReader = new BufferedReader(new FileReader("src/project2/course/BSCS/BSCS1/firstSem"));

                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                        if (values.length >= 3) {
                            String courseNumber = values[0];
                            String title = values[1];
                            int units = Integer.parseInt(values[2]);

                            courses.add(new Course(courseNumber, title, units));
                        } // end of 3rd if
                    } // end of while
                } else if (sem == 2) {
                    bufferedReader = new BufferedReader(new FileReader("src/project2/course/BSCS/BSCS1/secondSem"));

                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                        if (values.length >= 3) {
                            String courseNumber = values[0];
                            String title = values[1];
                            int units = Integer.parseInt(values[2]);

                            courses.add(new Course(courseNumber, title, units));
                        } // end of 3rd if
                    } // end of while
                } else if (sem == 3) {
                    bufferedReader = new BufferedReader(new FileReader("src/project2/course/BSCS/BSCS1/shortTerm"));

                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                        if (values.length >= 3) {
                            String courseNumber = values[0];
                            String title = values[1];
                            int units = Integer.parseInt(values[2]);

                            courses.add(new Course(courseNumber, title, units));
                        } // end of 3rd if
                    } // end of while
                } // end of 2nd if
            } // end of if
        } catch (IOException e1) {
            e1.getCause();
            e1.printStackTrace();
        } // end of try-catch
        return courses;
    } // end of populateChecklist method
} // end of class Main
