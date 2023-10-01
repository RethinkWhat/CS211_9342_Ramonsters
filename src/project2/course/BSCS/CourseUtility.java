package project2.course.BSCS;

import project2.referenceclasses.Course;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 * @version 1.00
 * @apiNote Populates LinkedLists of courses of different years and semesters.
 */
public class CourseUtility {
    /**
     * Holds a linked list of 'Course' objects representing courses for the first semester of first year BSCS students.
     */
    public LinkedList<Course> year1Sem1 = populateChecklist(1,1);

    /**
     * Holds a linked list of 'Course' objects representing courses for the second semester of first year BSCS students.
     */
    public LinkedList<Course> year1Sem2 = populateChecklist(1,2);

    /**
     * Holds a linked list of 'Course' objects representing courses for the third semester (short-term) of first year BSCS students.
     */
    public LinkedList<Course> year1Sem3 = populateChecklist(1,3);

    /**
     * Holds a linked list of 'Course' objects representing courses for the first semester of second year BSCS students.
     */
    public LinkedList<Course> year2Sem1 = populateChecklist(2,1);

    /**
     * Holds a linked list of 'Course' objects representing courses for the second semester of second year BSCS students.
     */
    public LinkedList<Course> year2Sem2 = populateChecklist(2,2);

    /**
     * Holds a linked list of 'Course' objects representing courses for the third semester (short-term) of second year BSCS students.
     */
    public LinkedList<Course> year2Sem3 = populateChecklist(2,3);

    /**
     * Holds a linked list of 'Course' objects representing courses for the first semester of third year BSCS students.
     */
    public LinkedList<Course> year3Sem1 = populateChecklist(3,1);

    /**
     * Holds a linked list of 'Course' objects representing courses for the second semester of third year BSCS students.
     */
    public LinkedList<Course> year3Sem2 = populateChecklist(3,2);

    /**
     * Holds a linked list of 'Course' objects representing courses for the third semester (short-term) of third year BSCS students.
     */
    public LinkedList<Course> year3Sem3 = populateChecklist(3,3);

    /**
     * Holds a linked list of 'Course' objects representing courses for the first semester of fourth year BSCS students.
     */
    public LinkedList<Course> year4Sem1 = populateChecklist(4,1);

    /**
     * Holds a linked list of 'Course' objects representing courses for the second semester of fourth year BSCS students.
     */
    public LinkedList<Course> year4Sem2 = populateChecklist(4,2);

    /**
     * Populates a linked list of courses based on the specified academic year and semester.
     * @param year
     * @param sem
     * @return LinkedList with data of Course
     */
    private LinkedList<Course> populateChecklist(int year, int sem) {
        LinkedList<Course> courses = new LinkedList<>();

        switch (year) {
            case 1 -> {
                switch (sem) {
                    case 1 -> courses = readCourses(new File("src/project2/course/BSCS/BSCS1/firstSem"));
                    case 2 -> courses =  readCourses(new File("src/project2/course/BSCS/BSCS1/secondSem"));
                    case 3 -> courses = readCourses(new File("src/project2/course/BSCS/BSCS1/shortTerm"));
                } // end of switch-case for semester
            } // end of case for year 1
            case 2 -> {
                switch (sem) {
                    case 1 -> courses = readCourses(new File("src/project2/course/BSCS/BSCS2/firstSem"));
                    case 2 -> courses = readCourses(new File("src/project2/course/BSCS/BSCS2/secondSem"));
                    case 3 -> courses = readCourses(new File("src/project2/course/BSCS/BSCS2/shortTerm"));
                } // end of switch-case for semester
            } // end of case for year 2
            case 3 -> {
                switch (sem) {
                    case 1 -> courses = readCourses(new File("src/project2/course/BSCS/BSCS3/firstSem"));
                    case 2 -> courses = readCourses(new File("src/project2/course/BSCS/BSCS3/secondSem"));
                    case 3 -> courses = readCourses(new File("src/project2/course/BSCS/BSCS3/shortTerm"));
                } // end of switch-case for semester
            } // end of case for year 3
            case 4 -> {
                switch (sem) {
                    case 1 -> courses = readCourses(new File("src/project2/course/BSCS/BSCS4/firstSem"));
                    case 2 -> courses = readCourses(new File("src/project2/course/BSCS/BSCS4/secondSem"));
                } // end of switch-case for semester
            } // end of case for year 4
        } // end of switch-case for year
        return courses;
    } // end of populateChecklist method

    /**
     * Reads course data from the given file and creates Course objects.
     * @param file given file name
     * @return course object with course number, descriptive title, and units.
     */
    private LinkedList<Course> readCourses(File file) {
        LinkedList<Course> courses = new LinkedList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (values.length >= 3) {
                    String courseNumber = values[0];
                    String title = values[1];
                    int units = Integer.parseInt(values[2]);

                    courses.add(new Course(courseNumber, title, units));
                } // end of if
            } // end of while
        } catch (IOException e1) {
            e1.getCause();
            e1.printStackTrace();
        } // end of try-catch
        return courses;
    } // end of readCourses method
} // end of class CourseUtility
