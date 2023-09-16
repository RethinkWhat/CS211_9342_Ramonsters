package project2.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * @author ROXAS, Johan Rickardo
 * @version 1.00 (16 September 2023)
 * Template for object LoginForm.
 * Populates the UI components of the Login Form.
 */
public class LoginForm extends JFrame {

    /**
     * An object of Resources that is used to invoke UI resources and elements.
     */
    private final Resources resources = new Resources();

    /**
     * Constructs an object of LoginForm.
     * Contains the components of the login and signup form.
     */
    public LoginForm() {
        super("SLU Portal Login Form");

        resources.loadFonts();
        setIconImage(resources.sluLogo.getImage());

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // ! Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setBackground(resources.antiflashWhite);
        headerPanel.setPreferredSize(new Dimension(700,120));
        mainPanel.add(headerPanel, BorderLayout.NORTH); // places footerPanel at the top

        // !! Header Panel components

        // !!! SLU Logo
        ImageIcon headerLogo = resources.scaleImage(resources.sluLogo, 70, 70);

        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(headerLogo);
        logoLabel.setText("SLU Portal");
        logoLabel.setFont(resources.montserratBlack.deriveFont(40f));
        logoLabel.setForeground(resources.yinmnBlue);
        headerPanel.add(logoLabel);


        // !!! Footer Panel components


        // ! Middle Panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new BorderLayout());
        middlePanel.setBackground(resources.antiflashWhite);
        middlePanel.setBorder(resources.normalPadding);
        middlePanel.setPreferredSize(new Dimension(300,530));
        mainPanel.add(middlePanel, BorderLayout.CENTER);

        // !! Middle Panel Components

        // !!! Separator
        JSeparator s1 = new JSeparator();
        s1.setForeground(Color.BLACK);
        s1.setOrientation(SwingConstants.HORIZONTAL);
        s1.setPreferredSize(new Dimension(300, 2));
        middlePanel.add(s1, BorderLayout.NORTH);

        // !!! Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.darkGray);
        formPanel.setPreferredSize(new Dimension(200, 150));
        middlePanel.add(formPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // !!!! Form Panel Components

        // !!!! User Login Label
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = 0;

        JLabel userLoginLabel = new JLabel();
        userLoginLabel.setText("User Login");
        userLoginLabel.setFont(resources.montserratBold.deriveFont(20f));
        userLoginLabel.setForeground(resources.antiflashWhite);
        formPanel.add(userLoginLabel, gbc);

        // !!!! Username Text Field
        gbc.gridy = 1;

        JTextField usernameTextField = new JTextField();
        usernameTextField.setFont(resources.montserrat.deriveFont(15f));
        usernameTextField.setText("Username");
        usernameTextField.setColumns(50);
        formPanel.add(usernameTextField, gbc);

        // !!!! Password Field
        gbc.gridy = 2;

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(resources.montserrat.deriveFont(15f));
        passwordField.setText("Password");
        passwordField.setColumns(50);
        formPanel.add(passwordField, gbc);

        // !!! Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        buttonsPanel.setBackground(resources.antiflashWhite);
        buttonsPanel.setPreferredSize(new Dimension(200, 70));
        middlePanel.add(buttonsPanel, BorderLayout.SOUTH);

        // !!!! Buttons Panel Components
        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setFont(resources.montserrat.deriveFont(15f));
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setBackground(resources.airSuperiorityBlue);
        loginButton.setForeground(Color.BLACK);
        buttonsPanel.add(loginButton);

        JButton signupButton = new JButton();
        signupButton.setText("Signup");
        signupButton.setFont(resources.montserrat.deriveFont(15f));
        signupButton.setOpaque(true);
        signupButton.setBorderPainted(false);
        signupButton.setBackground(resources.airSuperiorityBlue);
        signupButton.setForeground(Color.BLACK);
        buttonsPanel.add(signupButton);

        // ! Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBackground(resources.yinmnBlue);
        footerPanel.setPreferredSize(new Dimension(700,40));
        mainPanel.add(footerPanel, BorderLayout.SOUTH); // places footerPanel in the bottom

        // !! Footer Panel Components
        JLabel copyrightLabel = new JLabel();
        copyrightLabel.setText("© RAMONSTERS 2023");
        copyrightLabel.setFont(resources.montserrat.deriveFont(12f));
        copyrightLabel.setForeground(resources.antiflashWhite);
        copyrightLabel.setVerticalTextPosition(JLabel.CENTER);
        copyrightLabel.setHorizontalAlignment(JLabel.CENTER);
        footerPanel.add(copyrightLabel, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,500);
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
