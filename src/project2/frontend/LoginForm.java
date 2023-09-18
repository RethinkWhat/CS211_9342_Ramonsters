package project2.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * @author ROXAS, Johan Rickardo
 * @version 2.00 (16 September 2023)
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
        middlePanel.setPreferredSize(new Dimension(300,530));
        mainPanel.add(middlePanel, BorderLayout.CENTER);

        // !! Middle Panel Components

        // !!! Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.darkGray);
        formPanel.setBorder(new EmptyBorder(20,60,20,60));
        formPanel.setPreferredSize(new Dimension(200, 150));
        middlePanel.add(formPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;


        // !!!! Form Panel Components

        // !!!! User Login Label
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridy = 0;

        JLabel userLoginLabel = new JLabel();
        userLoginLabel.setText("User Login");
        userLoginLabel.setFont(resources.montserratBold.deriveFont(20f));
        userLoginLabel.setForeground(resources.antiflashWhite);
        userLoginLabel.setHorizontalAlignment(JLabel.CENTER);
        formPanel.add(userLoginLabel, gbc);

        // !!!! Field Panel
        gbc.gridy = 1;
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        fieldPanel.setBorder(resources.thinPadding);
        fieldPanel.setBackground(Color.darkGray);
        formPanel.add(fieldPanel, gbc);

        // !!!!! Field Panel Components
        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weightx = 0;
        gbc.weighty = 0;

        // !!!!!! Username Label
        gbc.gridx = 0;
        gbc.gridy = 0;

        ImageIcon accountIcon = new ImageIcon("icons/account-icon-black.png");
        JLabel usernameLabel = new JLabel();
        usernameLabel.setIcon(accountIcon);
        fieldPanel.add(usernameLabel, gbc);

        // !!!!! Username Text Field
        gbc.gridx = 1;

        JTextField usernameTextField = new JTextField();
        usernameTextField.setFont(resources.montserrat.deriveFont(15f));
        usernameTextField.setText("Username");
        usernameTextField.setColumns(14);
        usernameTextField.setForeground(Color.BLACK);
        fieldPanel.add(usernameTextField, gbc);

        usernameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameTextField.getText().equals("Username")) {
                    usernameTextField.setText("");
                    usernameTextField.setForeground(Color.black);
                } // end of if
            } // end of focusGained method

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameTextField.getText().isBlank()) {
                    usernameTextField.setText("Username");
                    usernameTextField.setForeground(Color.BLACK);
                } // end of if
            } // end of focusLost method
        }); // end of Focus Listener for usernameTextField

        // !!!!! Error Label
        gbc.gridy = 2;

        JLabel errorLabel = new JLabel();
        errorLabel.setForeground(Color.RED);
        errorLabel.setFont(resources.montserrat.deriveFont(12f));
        errorLabel.setVisible(false);
        fieldPanel.add(errorLabel, gbc);

        // !!! Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(resources.antiflashWhite);
        buttonsPanel.setPreferredSize(new Dimension(200, 70));
        buttonsPanel.setBorder(resources.thinPadding);
        middlePanel.add(buttonsPanel, BorderLayout.SOUTH);

        // !!!! Buttons Panel Components
        ImageIcon loginIcon = new ImageIcon("icons/login-icon-black.png");
        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setIcon(loginIcon);
        loginButton.setFont(resources.montserrat.deriveFont(15f));
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setBackground(resources.airSuperiorityBlue);
        loginButton.setForeground(Color.BLACK);
        loginButton.requestFocus();

        // !!!!! Password Label
        gbc.gridx = 0;
        gbc.gridy = 1;

        ImageIcon passwordIcon = new ImageIcon("icons/passkey-icon-black.png");
        JLabel passwordLabel = new JLabel();
        passwordLabel.setIcon(passwordIcon);
        fieldPanel.add(passwordLabel, gbc);

        // !!!!! Password Field
        gbc.gridx = 1;

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(resources.montserrat.deriveFont(15f));
        passwordField.setText("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.setColumns(14);
        fieldPanel.add(passwordField, gbc);
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isValid = new LoginFormUtility().validate(usernameTextField.getText(), passwordField.getPassword());
                if (!isValid) {
                    errorLabel.setText("Wrong Password. Try again.");
                    errorLabel.setVisible(true);
                } else {
                    errorLabel.setVisible(false);
                }
                loginButton.setBackground(Color.LIGHT_GRAY);
                Timer timer = new Timer(300, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loginButton.setBackground(resources.airSuperiorityBlue);
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).equals("Password")
                        || String.valueOf(passwordField.getPassword()).equalsIgnoreCase("")) {
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
                } // end of if
            } // end of focusGained method

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setText("Password");
                    passwordField.setEchoChar((char) 0);
                } // end of if
            } // end of focusLost method
        }); // end of Focus Listener for passwordField

        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        gbcButton.insets = new Insets(5, 5, 5, 5); // Add some padding
        gbcButton.fill = GridBagConstraints.CENTER; // Center the button
        buttonsPanel.add(loginButton, gbcButton);

        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                setCursor(resources.handCursor);
                loginButton.setBackground(resources.uranianBlue);
            } // end of mouseEntered method

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                boolean isValid = new LoginFormUtility().validate(usernameTextField.getText(), passwordField.getPassword());
                if (!isValid) {
                    errorLabel.setText("Wrong Password. Try again.");
                    errorLabel.setVisible(true);
                } else {
                    errorLabel.setVisible(false);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(resources.defaultCursor);
                loginButton.setFont(resources.montserrat.deriveFont(15f));
                loginButton.setBackground(resources.airSuperiorityBlue);
            } // end of mouseExited method
        }); // end MouseListener for loginButton

        // ! Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BorderLayout());
        footerPanel.setBackground(resources.yinmnBlue);
        footerPanel.setPreferredSize(new Dimension(700,30));
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
        setSize(500,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of loginForm constructor

} // end of class LoginForm
