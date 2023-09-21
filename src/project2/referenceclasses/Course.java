package project2.referenceclasses;

import project2.backend.*;

public class Course {
    private String courseNumber;
    private String descriptiveName;
    private int units;

    public Course(String courseNumber, String descriptiveName, int units) {
        this.courseNumber = courseNumber;
        this.descriptiveName = descriptiveName;
        this.units = units;
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

    public void setDescriptiveName(String descriptiveName) {
        this.descriptiveName = descriptiveName;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return courseNumber + ", " + descriptiveName + ", " + units;
    }
}



