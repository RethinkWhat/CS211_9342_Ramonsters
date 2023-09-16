package project2.frontend;

import javax.swing.*;
import java.awt.*;
/**
 * @author ROXAS, Johan Rickardo
 * @version 1.00 (16 September 2023)
 */
public class LoginForm extends JFrame {

    /**
     * An object of Resources that are used to invoke UI resources.
     */
    private final Resources resources = new Resources();

    /**
    Thin Montserrat font
     */
    Font montserratThin;

    /**
     * Regular Montserrat font
     */
    Font montserrat;

    /**
     * Bold Montserrat font
     */
    Font montserratBold;

    /**
     * Black Montserrat font
     */
    Font montserratBlack;


    /**
     * Constructs an object of LoginForm.
     * Contains the components of the login and signup form.
     */
    public LoginForm() {
        super("SLU Portal Login Form");

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // !Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setBackground(Color.red);
        headerPanel.setPreferredSize(new Dimension(700,120));
        mainPanel.add(headerPanel, BorderLayout.NORTH); // places footerPanel at the top

        // !!Header Panel components
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());

        // !!!Footer Panel components


        // !Middle Panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        middlePanel.setBackground(Color.gray);
        mainPanel.add(middlePanel, BorderLayout.CENTER);

        // !Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout());
        footerPanel.setBackground(Color.blue);
        footerPanel.setPreferredSize(new Dimension(700,50));
        mainPanel.add(footerPanel, BorderLayout.SOUTH); // places footerPanel in the bottom

        // !Footer Panel Components


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of loginForm constructor

    /*
    To be removed.
    Used only to test and debug the login form
     */
    public static void main(String[] args) {
        new LoginForm();
    } // end of main method


} // end of class LoginForm
