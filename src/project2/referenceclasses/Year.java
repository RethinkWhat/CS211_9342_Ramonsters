package project2.referenceclasses;

import project2.backend.*;

public class Year {
    private int yearNumber;
    private LinkedList<Course> firstSemSemesterList;
    private LinkedList<Course> secondSemSemesterList;
    private LinkedList<Course> shortTerm;
    public Year(int yearNumber, LinkedList<Course> firstSemSemesterList, LinkedList<Course> secondSemSemesterList, LinkedList<Course> shortTerm) {
        this.yearNumber = yearNumber;
        this.firstSemSemesterList = firstSemSemesterList;
        this.secondSemSemesterList = secondSemSemesterList;
        this.shortTerm = shortTerm;
    }

    public Year(int yearNumber, LinkedList<Course> firstSemSemesterList, LinkedList<Course> secondSemSemesterList) {
        this.yearNumber = yearNumber;
        this.firstSemSemesterList = firstSemSemesterList;
        this.secondSemSemesterList = secondSemSemesterList;
    }

    public int getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(int yearNumber) {
        this.yearNumber = yearNumber;
    }

    public LinkedList<Course> getFirstSemSemesterList() {
        return firstSemSemesterList;
    }

    public void setFirstSemSemesterList(LinkedList<Course> firstSemSemesterList) {
        this.firstSemSemesterList = firstSemSemesterList;
    }

    public LinkedList<Course> getSecondSemSemesterList() {
        return secondSemSemesterList;
    }

    public void setSecondSemSemesterList(LinkedList<Course> secondSemSemesterList) {
        this.secondSemSemesterList = secondSemSemesterList;
    }

    public LinkedList<Course> getShortTerm() {
        return shortTerm;
    }

    public void setShortTerm(LinkedList<Course> shortTerm) {
        this.shortTerm = shortTerm;
    }

    @Override
    public String toString() {
        return "year " + yearNumber + ", " +
                firstSemSemesterList + ", " +
                secondSemSemesterList + ", " +
                shortTerm;
    }
}
