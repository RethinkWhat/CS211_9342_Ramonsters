package project2.referenceclasses;

import project2.backend.*;

public class Course {
    private String courseNumber;
    private String descriptiveName;
    private int units;
    private int grade;

    public Course(String courseNumber, String descriptiveName, int units) {
        this.courseNumber = courseNumber;
        this.descriptiveName = descriptiveName;
        this.units = units;
        this.grade = 0;
    }

    public Course(String courseNumber, String descriptiveName, int units, int grade) {
        this.courseNumber = courseNumber;
        this.descriptiveName = descriptiveName;
        this.units = units;
        this.grade = grade;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getDescriptiveName() {
        return descriptiveName;
    }


    public int getGrade() {
        return grade;
    }

    public void setDescriptiveName(String descriptiveName) {
        this.descriptiveName = descriptiveName;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return courseNumber + ", " + descriptiveName + ", " + units;
    }
}



