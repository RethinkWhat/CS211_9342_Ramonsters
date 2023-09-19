package project2.referenceclasses;

import project2.backend.LinkedList;

import java.util.Objects;

public class Student {
    private String idNumber;
    private String firstName;
    private String lastName;
    private LinkedList<Year> yearList;

    public Student(String idNumber, String firstName, String lastName, LinkedList<Year> yearList) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearList = yearList;
    }


    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LinkedList<Year> getYearList() {
        return yearList;
    }

    public void setYearList(LinkedList<Year> yearList) {
        this.yearList = yearList;
    }

    @Override
    public String toString() {
        return idNumber + ", " + ", " + firstName + lastName + ", " + yearList;
    }
}
