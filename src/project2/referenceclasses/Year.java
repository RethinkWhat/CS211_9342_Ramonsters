package project2.referenceclasses;

import project2.backend.*;

public class Year {
    private int yearNumber;
    private LinkedList<Semester> semesterList;

    /**
     * Constructor
     * @param yearNumber
     * @param semesterList
     */
    public Year(int yearNumber, LinkedList<Semester> semesterList) {
        this.yearNumber = yearNumber;
        this.semesterList = semesterList;
    }

    /**
     * @return yearNumber
     */
    public int getYearNumber() {
        return yearNumber;
    }

    /**
     * @param yearNumber : int
     */
    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
    }

    /**
     * @return : semesterList
     */
    public LinkedList<Semester> getSemesterList() {
        return semesterList;
    }

    /**
     * @param semesterList : LinkedList<Semester
     */
    public void setSemesterList(LinkedList<Semester> semesterList) {
        this.semesterList = semesterList;
    }

    @Override
    public String toString() {
        return "Year " + yearNumber + ", " + semesterList;
    }
}
