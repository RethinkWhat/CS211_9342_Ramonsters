package project2.referenceclasses;
import project2.backend.Node;

public class Admin {

    //attributes
    private String firstName; //Represents the firstname.
    private String lastName; //Represents the last name.
    private String userName;// Represents user name.
    private String password;// Represents password.
    private String email;//Represents email.

    public Admin(String firstName, String lastName, String userName, String password, String email) { //constructor for all attributes.
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    public Admin(String userName, String password) {// constructor for username and password.
        this.userName = userName;
        this.password = password;
    }
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

    //@Override
    public boolean equals(Node<Admin> obj) {
        return this.password.equals(obj.data.getPassword()) && this.userName.equalsIgnoreCase(obj.getData().getUserName());
    }

    @Override
    public String toString() {
        return userName + "," + password;
    }//to string method.
}// end of Admin reference class
