package project2.frontend;

import javax.swing.*;
import java.awt.*;

/**
 * @author ROXAS, Johan Rickardo
 * @version 1.00 (16 September 2023)
 */
public class LoginForm extends JFrame {



    /**
     * Constructs an object of LoginForm.
     * Contains the components of the login and signup form.
     */
    public LoginForm() {
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setBackground();
        headerPanel.add(mainPanel);

        JPanel footerPanel = new JPanel();

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of loginForm constructor
} // end of class LoginForm
