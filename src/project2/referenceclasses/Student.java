package project2.referenceclasses;

import project2.backend.LinkedList;

public class Student {
    private String idNumber;
    private String firstName;
    private String lastName;
    private LinkedList<Year> yearList;

    /**
     * Constructor
     * @param idNumber : String
     * @param firstName : String
     * @param lastName : String
     * @param yearList : LinkedList<Year>
     */
    public Student(String idNumber, String firstName, String lastName, LinkedList<Year> yearList) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearList = yearList;
    }

    /**
     * @return idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber : String
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * @return : firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName : String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName : String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return LinkedList<Year>
     */
    public LinkedList<Year> getYearList() {
        return yearList;
    }

    /**
     * @param yearList : LinkedList<Year>
     */
    public void setYearList(LinkedList<Year> yearList) {
        this.yearList = yearList;
    }
}
