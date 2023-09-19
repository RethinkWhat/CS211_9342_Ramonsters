package project2.frontend;

import project2.backend.LinkedList;
import project2.referenceclasses.Course;
import project2.referenceclasses.Student;
import project2.referenceclasses.Year;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class StudentUtility {

    static Scanner fileReader;
    public static LinkedList<Course> createCourseList(File fileLocation) {
        LinkedList<Course> courseLinkedList = new LinkedList<Course>();
        Course courseObj;
        try {
            fileReader = new Scanner(fileLocation);
            while (fileReader.hasNext()) {
                String lineRead[] = fileReader.nextLine().split(",");
                courseObj = new Course(lineRead[0], lineRead[1], Integer.parseInt(lineRead[2]));
                courseLinkedList.insert(courseObj);
            }
        } catch(FileNotFoundException ex) {
            System.out.println("The file for the list of courses for the semester is missing.");
        }
        fileReader.close();
        return courseLinkedList;
    }

    public static File getFile(String course, int year, String sem) {
        return new File("src/project2/course/" + course + "/" + course + year + "/" + sem);
    }

    public static LinkedList<Year> createYearLinkedList(String course) {
        LinkedList<Course> firstYearFirstSem = StudentUtility.createCourseList(StudentUtility.getFile(course,1,"firstSem"));
        LinkedList<Course> firstYearSecondSem = StudentUtility.createCourseList(StudentUtility.getFile(course,1,"secondSem"));
        LinkedList<Course> firstYearShortTerm = StudentUtility.createCourseList(StudentUtility.getFile(course,1,"shortTerm"));
        LinkedList<Course> secondYearFirstSem =StudentUtility.createCourseList(StudentUtility.getFile(course,2,"firstSem"));
        LinkedList<Course> secondYearSecondSem = StudentUtility.createCourseList(StudentUtility.getFile(course,2,"secondSem"));
        LinkedList<Course> secondYearShortTerm = StudentUtility.createCourseList(StudentUtility.getFile(course,2,"shortTerm"));
        LinkedList<Course> thirdYearFirstTerm = StudentUtility.createCourseList(StudentUtility.getFile(course,3,"firstSem"));
        LinkedList<Course> thirdYearSecondTerm =StudentUtility.createCourseList(StudentUtility.getFile(course,3,"secondSem"));
        LinkedList<Course> thirdYearShortTerm =StudentUtility.createCourseList(StudentUtility.getFile(course,3,"shortTerm"));
        LinkedList<Course> fourthYearFirstSem = StudentUtility.createCourseList(StudentUtility.getFile(course,4,"firstSem"));
        LinkedList<Course> fourthYearSecondSem = StudentUtility.createCourseList(StudentUtility.getFile(course,4,"secondSem"));

        Year firstYear = new Year(1,firstYearFirstSem, firstYearSecondSem, firstYearShortTerm);
        Year secondYear = new Year(2,secondYearFirstSem, secondYearSecondSem, secondYearShortTerm);
        Year thirdYear = new Year(3, thirdYearFirstTerm, thirdYearSecondTerm, thirdYearShortTerm );
        Year fourthYear = new Year(4,fourthYearFirstSem, fourthYearSecondSem);
        LinkedList<Year> yearLinkedList = new LinkedList<>();
        yearLinkedList.insert(firstYear);
        yearLinkedList.insert(secondYear);
        yearLinkedList.insert(thirdYear);
        yearLinkedList.insert(fourthYear);

        return yearLinkedList;
    }

}
