package project2.referenceclasses;

import project2.backend.Node;

public class Admin {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;

    public Admin(String firstName, String lastName, String userName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public Admin(String userName, String password) {
        this.userName = userName;
        this.password = password;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //@Override
    public boolean equals(Node<Admin> obj) {
        return this.password.equalsIgnoreCase(obj.data.getPassword());
    }

    @Override
    public String toString() {
        return userName + "," + password;
    }
}
