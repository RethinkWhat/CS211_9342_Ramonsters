package project2.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author TBA
 * @version 1.00 (16 September 2023)
 * @apiNote Populates the UI components of the SLU Portal.
 * Template for Portal object.
 */
public class Portal extends JFrame {
    /**
     * An object of Resources that is used to invoke UI resources and elements.
     */
    private final Resources resources = new Resources();

    /**
     * TODO: Documentation
     */
    private JButton homeButton;

    /**
     * TODO: Documentation
     */
    private JButton scheduleButton;

    /**
     * TODO: Documentation
     */
    private JButton torButton;

    /**
     * TODO: Documentation
     */
    private JButton checklistButton;

    /**
     * TODO: Documentation
     */
    private JButton personalDetailsButton;

    /**
     * Card Layout used for the centerPanel
     */
    private final CardLayout cardLayout1 = new CardLayout(10,20);

    /**
     * Card Layout used for components inside centerPanel
     */
    private final CardLayout cardLayout2 = new CardLayout(10,20);

    /**
     * Constructs an object of Portal.
     * Contains the components of the SLU Portal.
     */
    public Portal() {
        super("SLU Portal");

        resources.loadFonts();
        setIconImage(resources.sluLogo.getImage());

        // Main Panel
        JPanel mainPanel = new JPanel(); // Holds the whole window
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // ! Header Panel
        JPanel headerPanel = populateHeader(); // Holds the header
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // ! Sidebar Panel
        JPanel sidebarPanel = populateSidebar(); // Holds the sidebar
        mainPanel.add(sidebarPanel, BorderLayout.WEST);

        // ! Center Panel
        JPanel centerPanel = new JPanel(); // Holds the container for the program output
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(resources.antiflashWhite);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // !! Center Panel Components

        // !! Card Panel
        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(cardLayout1);
        cardPanel.setBackground(resources.antiflashWhite);
        centerPanel.add(cardPanel);

        // !!! Card Panel Components

        // !!! Home Panel
        JPanel homePanel = populateHomePanel();
        cardPanel.add(homePanel, "home");

        // !!! Schedule Panel
        JPanel schedulePanel = populateSchedulePanel();
        cardPanel.add(schedulePanel, "schedule");

        // !!! TOR Panel
        JPanel torPanel = populateTorPanel();
        cardPanel.add(torPanel, "tor");

        // Action Listeners
        homeButton.addActionListener(e -> {
            cardLayout1.show(cardPanel, "home");
            homeButton.setForeground(resources.antiflashWhite);
            scheduleButton.setForeground(Color.BLACK);
            torButton.setForeground(Color.BLACK);
            checklistButton.setForeground(Color.BLACK);
            personalDetailsButton.setForeground(Color.BLACK);
        });
        scheduleButton.addActionListener(e -> {
            cardLayout1.show(cardPanel, "schedule");
            homeButton.setForeground(Color.BLACK);
            scheduleButton.setForeground(resources.antiflashWhite);
            torButton.setForeground(Color.BLACK);
            checklistButton.setForeground(Color.BLACK);
            personalDetailsButton.setForeground(Color.BLACK);
        });
        torButton.addActionListener(e -> {
            cardLayout1.show(cardPanel, "tor");
            cardLayout1.show(torPanel, "1");
            homeButton.setForeground(Color.BLACK);
            scheduleButton.setForeground(Color.BLACK);
            torButton.setForeground(resources.antiflashWhite);
            checklistButton.setForeground(Color.BLACK);
            personalDetailsButton.setForeground(Color.BLACK);
        });
        checklistButton.addActionListener(e -> {
            cardLayout1.show(cardPanel, "checklist");
            homeButton.setForeground(Color.BLACK);
            scheduleButton.setForeground(Color.BLACK);
            torButton.setForeground(Color.BLACK);
            checklistButton.setForeground(resources.antiflashWhite);
            personalDetailsButton.setForeground(Color.BLACK);
        });
        personalDetailsButton.addActionListener(e -> {
            cardLayout1.show(cardPanel, "personalDetails");
            cardLayout1.show(cardPanel, "checklist");
            homeButton.setForeground(Color.BLACK);
            scheduleButton.setForeground(Color.BLACK);
            torButton.setForeground(Color.BLACK);
            checklistButton.setForeground(Color.BLACK);
            personalDetailsButton.setForeground(resources.antiflashWhite);
        });



        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of Portal constructor

    /**
     * TODO: Documentation
     * @return
     */
    private JPanel populateHeader() {
        // Header panel
        JPanel headerPanel = new JPanel(); // Container for the header components
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBorder(resources.thinPadding);
        headerPanel.setBackground(resources.yinmnBlue);
        headerPanel.setPreferredSize(new Dimension(1200, 55));

        // ! Header Panel components

        // ! Logo Label
        ImageIcon headerLogo = resources.scaleImage(resources.sluLogo, 25, 25);

        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(headerLogo);
        logoLabel.setFont(resources.montserratBlack.deriveFont(15f));
        logoLabel.setForeground(resources.antiflashWhite);
        logoLabel.setText("SLU Admin Portal");
        headerPanel.add(logoLabel, BorderLayout.WEST);

        // ! Account Panel
        JPanel accountPanel = new JPanel(); // Holds the account details
        accountPanel.setLayout(new FlowLayout());
        accountPanel.setBackground(resources.airSuperiorityBlue);
        accountPanel.setPreferredSize(new Dimension(200,55));
        headerPanel.add(accountPanel, BorderLayout.EAST);

        // !! Account Panel Components

        // !! Account Image Label
        ImageIcon accountIcon = new ImageIcon("icons/account-icon-black.png");
        ImageIcon scaledAccountIcon = resources.scaleImage(accountIcon, 20, 20);

        JLabel accountImageLabel = new JLabel();
        accountImageLabel.setIcon(scaledAccountIcon);
        accountImageLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountPanel.add(accountImageLabel);

        // !! Account Name
        JLabel nameLabel = new JLabel();
        nameLabel.setText("Manoj Kumar Tank");
        nameLabel.setFont(resources.montserrat.deriveFont(12f));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setVerticalAlignment(SwingConstants.CENTER);
        accountPanel.add(nameLabel);

        return headerPanel;
    } // end of populateHeader method

    /**
     * TODO: Documentation
     * @return
     */
    private JPanel populateSidebar() {
        // Sidebar Panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BorderLayout());
        sidebarPanel.setBorder(resources.thinPadding);
        sidebarPanel.setBackground(resources.airSuperiorityBlue);
        sidebarPanel.setPreferredSize(new Dimension(290,700));

        // ! Sidebar Panel Components

        // ! Semester Panel
        JPanel semesterPanel = new JPanel();
        semesterPanel.setLayout(new FlowLayout());
        semesterPanel.setBackground(resources.yinmnBlue);
        semesterPanel.setPreferredSize(new Dimension(290,25));
        sidebarPanel.add(semesterPanel, BorderLayout.NORTH);

        // !! Semester Panel Components

        // !! Semester Label
        JLabel semesterLabel = new JLabel();
        semesterLabel.setText("FIRST SEMESTER, 2023-2024");
        semesterLabel.setVerticalAlignment(SwingConstants.CENTER);
        semesterLabel.setHorizontalAlignment(SwingConstants.CENTER);
        semesterLabel.setForeground(resources.antiflashWhite);
        semesterLabel.setFont(resources.montserratBold.deriveFont(12f));
        semesterPanel.add(semesterLabel);

        // ! Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new GridBagLayout());
        navPanel.setBackground(resources.airSuperiorityBlue);
        navPanel.setPreferredSize(new Dimension(290, 650));
        sidebarPanel.add(navPanel, BorderLayout.CENTER);

        // !! Navigation Panel Components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // fills the whole horizontal space
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(5,0,0,20);

        // !! Home Button
        gbc.gridy = 1;
        ImageIcon homeIcon = new ImageIcon("icons/home-icon-black.png");
        homeButton = new JButton();
        homeButton.setText("Home");
        homeButton.setIcon(homeIcon);
        homeButton.setForeground(resources.antiflashWhite);
        homeButton.setHorizontalAlignment(SwingConstants.LEFT);
        homeButton.setFont(resources.montserratBold.deriveFont(15f));
        homeButton.setBorderPainted(true);
        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        navPanel.add(homeButton, gbc);

        // !! Schedule Button
        gbc.gridy = 2;
        ImageIcon scheduleIcon = new ImageIcon("icons/schedule-icon-black.png");
        scheduleButton = new JButton();
        scheduleButton.setText("Schedule");
        scheduleButton.setIcon(scheduleIcon);
        scheduleButton.setHorizontalAlignment(SwingConstants.LEFT);
        scheduleButton.setFont(resources.montserratBold.deriveFont(15f));
        scheduleButton.setBorderPainted(false);
        scheduleButton.setOpaque(false);
        scheduleButton.setContentAreaFilled(false);
        navPanel.add(scheduleButton, gbc);

        // !! Attendance Button
        gbc.gridy = 3;
        ImageIcon torIcon = new ImageIcon("icons/tor-icon-black.png");
        torButton = new JButton();
        torButton.setText("Transcript of Records");
        torButton.setIcon(torIcon);
        torButton.setHorizontalAlignment(SwingConstants.LEFT);
        torButton.setFont(resources.montserratBold.deriveFont(15f));
        torButton.setBorderPainted(false);
        torButton.setOpaque(false);
        torButton.setContentAreaFilled(false);
        navPanel.add(torButton, gbc);

        // !! Statement of Accounts Button
        gbc.gridy = 4;
        ImageIcon checklistIcon = new ImageIcon("icons/checklist-icon-black.png");
        checklistButton = new JButton();
        checklistButton.setText("Curriculum Checklist");
        checklistButton.setIcon(checklistIcon);
        checklistButton.setHorizontalAlignment(SwingConstants.LEFT);
        checklistButton.setFont(resources.montserratBold.deriveFont(15f));
        checklistButton.setBorderPainted(false);
        checklistButton.setOpaque(false);
        checklistButton.setContentAreaFilled(false);
        navPanel.add(checklistButton, gbc);

        // !! Personal Details Button
        gbc.gridy = 5;
        ImageIcon personalIcon = new ImageIcon("icons/personal-icon-black.png");
        personalDetailsButton = new JButton();
        personalDetailsButton.setText("Personal Details");
        personalDetailsButton.setIcon(personalIcon);
        personalDetailsButton.setHorizontalAlignment(SwingConstants.LEFT);
        personalDetailsButton.setFont(resources.montserratBold.deriveFont(15f));
        personalDetailsButton.setBorderPainted(false);
        personalDetailsButton.setOpaque(false);
        personalDetailsButton.setContentAreaFilled(false);
        navPanel.add(personalDetailsButton, gbc);

        return sidebarPanel;
    } // end of populateSidebar method

    /**
     * TODO: Documentation
     * @return
     */
    private JPanel populateHomePanel() {
        // Home Panel
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new GridLayout(1, 2));
        homePanel.setBorder(resources.normalPadding);
        homePanel.setBackground(resources.antiflashWhite);
        homePanel.setPreferredSize(new Dimension(910, 700));

        // ! Home Panel Components

        // ! Bulletin Board Panel
        JPanel bulletinPanel = new JPanel();
        bulletinPanel.setLayout(new GridLayout(5,1));
        bulletinPanel.setBorder(resources.normalPadding);
        bulletinPanel.setBackground(resources.uranianBlue);
        homePanel.add(bulletinPanel);

        // !! Bulletin Board Components
        // TODO: Supporting code

        // ! Student Panel
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());
        studentPanel.setBorder(resources.normalPadding);
        studentPanel.setBackground(resources.battleshipGray);
        homePanel.add(studentPanel);

        // !! Student Panel Components
        // TODO: Supporting code

        return homePanel;
    } // end of populateHomePanel method

    /**
     * TODO: Documentation
     * @return
     */
    private JPanel populateSchedulePanel() {
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(new BorderLayout());
        schedulePanel.setBackground(resources.antiflashWhite);
        schedulePanel.setPreferredSize(new Dimension(910, 700));

        JLabel scheduleLabel = new JLabel();
        scheduleLabel.setText("Not Available.");
        scheduleLabel.setFont(resources.montserratBold.deriveFont(50f));
        scheduleLabel.setVerticalAlignment(SwingConstants.CENTER);
        scheduleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        scheduleLabel.setForeground(Color.BLACK);
        schedulePanel.add(scheduleLabel, BorderLayout.CENTER);

        JLabel contactLabel = new JLabel();
        contactLabel.setText("Contact the development team for further details.");
        contactLabel.setFont(resources.montserratBold.deriveFont(20f));
        contactLabel.setVerticalAlignment(SwingConstants.CENTER);
        contactLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contactLabel.setForeground(Color.BLACK);
        schedulePanel.add(contactLabel, BorderLayout.SOUTH);

        return schedulePanel;
    } // end of populateSchedulePanel method

    private JPanel populateTorPanel() {
        // Transcript of Records Panel
        JPanel torPanel = new JPanel();
        torPanel.setLayout(cardLayout1);
        torPanel.setBorder(resources.thinPadding);
        torPanel.setBackground(resources.antiflashWhite);
        torPanel.setPreferredSize(new Dimension(910, 700));

        // ! TOR Components
        
        // ! Container 1
        JPanel containerPanel1 = new JPanel();
        containerPanel1.setLayout(new BorderLayout());
        containerPanel1.setBackground(resources.antiflashWhite);
        containerPanel1.setPreferredSize(new Dimension(910,700));
        torPanel.add(containerPanel1, "1");
        
        // !! Container 1 Components
        
        // !! Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());
        searchPanel.setBorder(resources.normalPadding);
        searchPanel.setBackground(Color.darkGray);
        searchPanel.setPreferredSize(new Dimension(800,600));
        containerPanel1.add(searchPanel, BorderLayout.CENTER);
        
        // !!! Search Panel Components
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,20,5,20);
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // !!! Student Panel
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());
        studentPanel.setBackground(Color.darkGray);
        studentPanel.setPreferredSize(new Dimension(800, 200));
        searchPanel.add(studentPanel, gbc);

        // !!!! Student Panel Components
        ImageIcon studentIcon = new ImageIcon("icons/student-icon-black.png");

        JLabel studentLabel = new JLabel();
        studentLabel.setText("LAST NAME, FIRST NAME");
        studentLabel.setIcon(studentIcon);
        studentLabel.setFont(resources.montserratBold.deriveFont(15f));
        studentLabel.setForeground(resources.antiflashWhite);
        studentLabel.setHorizontalAlignment(SwingConstants.CENTER);
        studentLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        studentPanel.add(studentLabel, BorderLayout.CENTER);

        JLabel idLabel = new JLabel();
        idLabel.setText("2230000");
        idLabel.setFont(resources.montserratBold.deriveFont(15f));
        idLabel.setForeground(resources.antiflashWhite);
        idLabel.setHorizontalAlignment(SwingConstants.CENTER);
        idLabel.setVerticalAlignment(SwingConstants.CENTER);
        studentPanel.add(idLabel, BorderLayout.SOUTH);


        // !!! Name Text Field
        gbc.gridy = 1;

        JTextField nameTextField = new JTextField();
        nameTextField.setFont(resources.montserrat.deriveFont(15f));
        nameTextField.setText("Search by Name");
        nameTextField.setColumns(14);
        nameTextField.setForeground(Color.BLACK);
        searchPanel.add(nameTextField, gbc);

        // !!! ID Number Text Field
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridx = 0;

        JTextField idTextField = new JTextField();
        idTextField.setFont(resources.montserrat.deriveFont(15f));
        idTextField.setText("Search by SLU ID Number");
        idTextField.setColumns(14);
        idTextField.setForeground(Color.BLACK);
        searchPanel.add(idTextField, gbc);

        // !!! Course Combo Box
        gbc.gridx = 1;

        String[] courses = {"BSCS","BSIT"}; // tentative array of courses

        JComboBox<String> programComboBox = new JComboBox<>(courses);
        programComboBox.setFont(resources.montserrat.deriveFont(15f));
        programComboBox.setForeground(Color.BLACK);
        searchPanel.add(programComboBox, gbc);

        // !! Buttons Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(resources.antiflashWhite);
        buttonsPanel.setPreferredSize(new Dimension(500, 100));
        containerPanel1.add(buttonsPanel, BorderLayout.SOUTH);

        // !!! Buttons Panel Components
        gbc.insets = new Insets(5,200,5,200);
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;

        // !!! Search Button
        ImageIcon searchIcon = new ImageIcon("icons/person-search-icon-black.png");

        gbc.gridy = 0;

        JButton searchButton = new JButton();
        searchButton.setText("Search");
        searchButton.setIcon(searchIcon);
        searchButton.setFont(resources.montserrat.deriveFont(15f));
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setBackground(resources.airSuperiorityBlue);
        searchButton.setForeground(Color.BLACK);
        buttonsPanel.add(searchButton, gbc);

        // !!! Clear Button
        ImageIcon clearIcon = new ImageIcon("icons/clear_all-icon-black.png");

        gbc.gridy = 1;

        JButton clearButton = new JButton();
        clearButton.setText("Clear All");
        clearButton.setIcon(clearIcon);
        clearButton.setFont(resources.montserrat.deriveFont(15f));
        clearButton.setOpaque(true);
        clearButton.setBorderPainted(false);
        clearButton.setBackground(resources.airSuperiorityBlue);
        clearButton.setForeground(Color.BLACK);
        buttonsPanel.add(clearButton, gbc);

        // ! Container 2
        JPanel containerPanel2 = new JPanel();
        containerPanel2.setLayout(new BorderLayout());
        containerPanel2.setBackground(resources.antiflashWhite);
        containerPanel2.setPreferredSize(new Dimension(910,700));
        torPanel.add(containerPanel2, "2");

        // !! Container 2 Components


        // Action Listeners
        // FIXME: 9/17/2023
        searchButton.addActionListener(e->{
            cardLayout1.show(torPanel, "2");
        });

        return torPanel;
    } // end of populateTorPanel

    /*
    To be removed.
    Will only be used for testing.
     */
    public static void main(String[] args) {
        new Portal();
    } // end of main method
} // end of class Portal
