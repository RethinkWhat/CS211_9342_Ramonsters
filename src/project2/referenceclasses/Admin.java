package project2.referenceclasses;
import project2.backend.Node;

/**
 * Allows for the creation of admin users
 */
public class Admin {
    /**
     * Private attributes for the admin methods to utilize
     */
    //attributes
    private String firstName; //Represents the firstname.
    private String lastName; //Represents the last name.
    private String userName;// Represents user name.
    private String password;// Represents password.
    private String email;//Represents email.

    /**
     * Initializes the Admin class attributes when creating an object
     */

    public Admin(String firstName, String lastName, String userName, String password, String email) { //constructor for all attributes.
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    /**
     * Initializes userName and password to allow for a simplified creation of admin object
     */
    public Admin(String userName, String password) {// constructor for username and password.
        this.userName = userName;
        this.password = password;
    }

    /**
     * Getter methods retrieve the private attribute values of the Admin class
     * Setter methods modify the value of the private attributes
     */
    public String getFirstName() {
        return firstName;
    }//getter for first name

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }// setter for first name

    public String getLastName() {
        return lastName;
    }//getter for last name.

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }// setter for last name

    public String getUserName() {
        return userName;
    }// getter for user name

    public void setUserName(String userName) {
        this.userName = userName;
    }// setter for user name

    public String getPassword() {
        return password;
    }//getter for password.

    public void setPassword(String password) {
        this.password = password;
    }//setter for password.

    public String getEmail() {
        return email;
    }//getter for email

    public void setEmail(String email) {
        this.email = email;
    }// setter for email
    /**
     * Checks if the inputted details of the admin object matches the object in Node
     * @return true, otherwise false
     */
    //@Override
    public boolean equals(Node<Admin> obj) {
        return this.password.equals(obj.data.getPassword()) && this.userName.equalsIgnoreCase(obj.getData().getUserName());
    }

    /**
     * @return a formatted string of Admin object
     */
    @Override
    public String toString() {
        return userName + "," + password;
    }//to string method.
}// end of Admin reference class
