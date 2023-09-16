package project2.referenceclasses;

import project2.backend.*;

public class Semester {
    private int semesterNo; // First Sem = 1, Second Sem = 2, Short Term = 3
    private LinkedList<Course> courseList;

    /**
     * Constructor
     * @param semesterNo
     * @param courseList
     */
    public Semester(int semesterNo, LinkedList<Course> courseList) {
        this.semesterNo = semesterNo;
        this.courseList = courseList;
    }

    /**
     * @return SemesterNo
     */
    public int getSemesterNo() {
        return semesterNo;
    }

    /**
     * Method to get semester
     * @return semesterName
     */
    public String getSemester() {
        if (semesterNo == 1)
                return "first semester";
        if (semesterNo == 2)
            return "second semester";
        else
            return "short term";
    }

    /**
     * Method to get course list
     * @return courseList
     */
    public LinkedList<Course> getCourseList() {
        return courseList;
    }

    /**
     * Method to set the semesterNo
     * @param semesterNo : int
     */
    public void setSemesterNo(int semesterNo) {
        this.semesterNo = semesterNo;
    }

    /**
     * Method to set the courseList
     * @param courseList : LinkedList<Course>
     */
    public void setCourseList(LinkedList<Course> courseList) {
        this.courseList = courseList;
    }
    @Override
    public String toString() {
        return getSemester() + ", " + courseList;
    }
}
