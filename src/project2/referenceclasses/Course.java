package project2.referenceclasses;

import project2.backend.*;

public class Course {
    private String descriptiveTitle;
    private String courseNumber;
    private int units;
    private int grades;

    /**
     * Constructor for when the grades of the course exist
     * @param descriptiveTitle : String
     * @param courseNumber : String
     * @param units : int
     * @param grades : int
     */
    public Course(String descriptiveTitle, String courseNumber, int units, int grades) {
        this.descriptiveTitle = descriptiveTitle;
        this.courseNumber = courseNumber;
        this.units = units;
        this.grades = grades;
    }

    /**
     * Constructor for when the course has not been taken yet
     * @param descriptiveTitle
     * @param courseNumber
     * @param units
     */
    public Course(String descriptiveTitle, String courseNumber, int units) {
        this.descriptiveTitle = descriptiveTitle;
        this.courseNumber = courseNumber;
        this.units = units;
    }

    /**
     * Get the descriptive title of Course obj
     * @return descriptiveTitle
     */
    public String getDescriptiveTitle() {
        return descriptiveTitle;
    }

    /**
     * Set the descriptive title of Course obj
     * @param descriptiveTitle : String
     */
    public void setDescriptiveTitle(String descriptiveTitle) {
        this.descriptiveTitle = descriptiveTitle;
    }

    /**
     * Get the course number of Course obj
     * @return courseNumber
     */
    public String getCourseNumber() {
        return courseNumber;
    }

    /**
     * Set the course number of Course obj
     * @param courseNumber : String
     */
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    /**
     * Get the units of Course obj
     * @return units
     */
    public int getUnits() {
        return units;
    }

    /**
     * Set the units of Course obj
     * @param units : int
     */
    public void setUnits(int units) {
        this.units = units;
    }

    /**
     * Get the grades of Course obj
     * @return grades
     */
    public int getGrades() {
        return grades;
    }

    /**
     * Set the grades of Course obj
     * @param grades : int
     */
    public void setGrades(int grades) {
        this.grades = grades;
    }
}

