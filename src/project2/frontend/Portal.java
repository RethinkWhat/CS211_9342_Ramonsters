package project2.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.EventObject;

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
        homeButton.setBorderPainted(false);
        homeButton.setOpaque(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setFocusable(false);
        homeButton.setFocusPainted(false);
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
        scheduleButton.setFocusable(false);
        scheduleButton.setFocusPainted(false);
        navPanel.add(scheduleButton, gbc);

        // !! Transcript of Records Button
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
        torButton.setFocusable(false);
        torButton.setFocusPainted(false);
        navPanel.add(torButton, gbc);

        // !! Curriculum Checklist Button
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
        checklistButton.setFocusable(false);
        checklistButton.setFocusPainted(false);
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
        personalDetailsButton.setFocusable(false);
        personalDetailsButton.setFocusPainted(false);
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
        homePanel.setLayout(new GridLayout(1, 2,15,0));
        homePanel.setBorder(resources.normalPadding);
        homePanel.setBackground(resources.antiflashWhite);
        homePanel.setPreferredSize(new Dimension(910, 700));

        // ! Home Panel Components

        // ! Bulletin Board Panel
        JPanel bulletinPanel = new JPanel();
        bulletinPanel.setLayout(new BorderLayout());
        bulletinPanel.setBackground(resources.uranianBlue);
        homePanel.add(bulletinPanel);

        // !! Bulletin Board Components

        // !! Heading Panel 1
        JPanel headingPanel1 = new JPanel();
        headingPanel1.setLayout(new BorderLayout());
        headingPanel1.setBackground(resources.yinmnBlue);
        headingPanel1.setBorder(resources.thinPadding);
        headingPanel1.setPreferredSize(new Dimension(910,40));
        bulletinPanel.add(headingPanel1, BorderLayout.NORTH);

        // !!! Heading Panel Components
        ImageIcon headingIcon = new ImageIcon("icons/news-icon-black.png");

        JLabel headingLabel = new JLabel();
        headingLabel.setText("News, Events, and Announcements");
        headingLabel.setIcon(headingIcon);
        headingLabel.setFont(resources.montserratBold.deriveFont(12f));
        headingLabel.setForeground(resources.antiflashWhite);
        headingPanel1.add(headingLabel, BorderLayout.WEST);

        // !! Bulletin Pins Panel
        JPanel bulletinPinsPanel = new JPanel();
        bulletinPinsPanel.setLayout(new GridLayout(5,1));
        bulletinPinsPanel.setBackground(resources.antiflashWhite);
        bulletinPinsPanel.setBorder(new EmptyBorder(0,5,0,5));
        bulletinPinsPanel.setPreferredSize(new Dimension(405, 700));
        bulletinPanel.add(bulletinPinsPanel, BorderLayout.CENTER);

        // !!! Bulletin Pins Components

        // !!!! Pin Panel 1
        JPanel pinPanel1 = new JPanel();
        pinPanel1.setLayout(new BorderLayout());
        pinPanel1.setBackground(resources.uranianBlue);
        pinPanel1.setBorder(resources.normalPadding);
        bulletinPinsPanel.add(pinPanel1);

        // !!!!! Pin Panel 1 Components
        JLabel remindersLabel1 = new JLabel();
        remindersLabel1.setText("Reminders for the Recollection");
        remindersLabel1.setFont(resources.montserrat.deriveFont(14f));
        remindersLabel1.setForeground(Color.BLACK);
        pinPanel1.add(remindersLabel1, BorderLayout.NORTH);

        JLabel linkLabel1 = new JLabel();
        linkLabel1.setText("- view link");
        linkLabel1.setFont(resources.montserrat.deriveFont(12f));
        linkLabel1.setForeground(resources.lipstickRed);
        pinPanel1.add(linkLabel1, BorderLayout.SOUTH);

        // !!!! Pin Panel 2
        JPanel pinPanel2 = new JPanel();
        pinPanel2.setLayout(new BorderLayout());
        pinPanel2.setBackground(resources.airSuperiorityBlue);
        pinPanel2.setBorder(resources.normalPadding);
        bulletinPinsPanel.add(pinPanel2);

        // !!!!! Pin Panel 2 Components
        JLabel remindersLabel2 = new JLabel();
        remindersLabel2.setText("Navigating SLU");
        remindersLabel2.setFont(resources.montserratBold.deriveFont(16f));
        remindersLabel2.setForeground(Color.BLACK);
        pinPanel2.add(remindersLabel2, BorderLayout.NORTH);

        JLabel linkLabel2 = new JLabel();
        linkLabel2.setText("- view link");
        linkLabel2.setFont(resources.montserrat.deriveFont(12f));
        linkLabel2.setForeground(resources.lipstickRed);
        pinPanel2.add(linkLabel2, BorderLayout.SOUTH);

        // !!!! Pin Panel 3
        JPanel pinPanel3 = new JPanel();
        pinPanel3.setLayout(new BorderLayout());
        pinPanel3.setBackground(resources.uranianBlue);
        pinPanel3.setBorder(resources.normalPadding);
        bulletinPinsPanel.add(pinPanel3);

        // !!!!! Pin Panel 3 Components
        JLabel remindersLabel3 = new JLabel();
        remindersLabel3.setText("Link to SLU Pertinent Information");
        remindersLabel3.setFont(resources.montserrat.deriveFont(14f));
        remindersLabel3.setForeground(Color.BLACK);
        pinPanel3.add(remindersLabel3, BorderLayout.NORTH);

        JLabel linkLabel3 = new JLabel();
        linkLabel3.setText("- view link");
        linkLabel3.setFont(resources.montserrat.deriveFont(12f));
        linkLabel3.setForeground(resources.lipstickRed);
        pinPanel3.add(linkLabel3, BorderLayout.SOUTH);

        // !!!! Pin Panel 4
        JPanel pinPanel4 = new JPanel();
        pinPanel4.setLayout(new BorderLayout());
        pinPanel4.setBackground(resources.airSuperiorityBlue);
        pinPanel4.setBorder(resources.normalPadding);
        bulletinPinsPanel.add(pinPanel4);

        // !!!!! Pin Panel 4 Components
        JLabel remindersLabel4 = new JLabel();
        remindersLabel4.setText("SLU OSA Absence Slip");
        remindersLabel4.setFont(resources.montserrat.deriveFont(14f));
        remindersLabel4.setForeground(Color.BLACK);
        pinPanel4.add(remindersLabel4, BorderLayout.NORTH);

        JLabel linkLabel4 = new JLabel();
        linkLabel4.setText("- view link");
        linkLabel4.setFont(resources.montserrat.deriveFont(12f));
        linkLabel4.setForeground(resources.lipstickRed);
        pinPanel4.add(linkLabel4, BorderLayout.SOUTH);

        // !!!! Pin Panel 5
        JPanel pinPanel5 = new JPanel();
        pinPanel5.setLayout(new BorderLayout());
        pinPanel5.setBackground(resources.uranianBlue);
        pinPanel5.setBorder(resources.normalPadding);
        bulletinPinsPanel.add(pinPanel5);

        // !!!!! Pin Panel 5 Components
        JLabel remindersLabel5 = new JLabel();
        remindersLabel5.setText("<html><u>ACADEMIC CALENDAR 2023-2024</u>");
        remindersLabel5.setFont(resources.montserrat.deriveFont(14f));
        remindersLabel5.setForeground(Color.BLACK);
        pinPanel5.add(remindersLabel5, BorderLayout.NORTH);

        JLabel linkLabel5 = new JLabel();
        linkLabel5.setText("- view link");
        linkLabel5.setFont(resources.montserrat.deriveFont(12f));
        linkLabel5.setForeground(resources.lipstickRed);
        linkLabel5.add(linkLabel3, BorderLayout.SOUTH);
        pinPanel5.add(linkLabel5, BorderLayout.SOUTH);

        // ! Student Panel
        JPanel studentPanel = new JPanel();
        studentPanel.setLayout(new BorderLayout());
        studentPanel.setBackground(resources.antiflashWhite);
        homePanel.add(studentPanel);

        // !! Student Components

        // !! Heading Panel 2
        JPanel headingPanel2 = new JPanel();
        headingPanel2.setLayout(new BorderLayout());
        headingPanel2.setBackground(resources.yinmnBlue);
        headingPanel2.setBorder(resources.thinPadding);
        headingPanel2.setPreferredSize(new Dimension(910,40));
        studentPanel.add(headingPanel2, BorderLayout.NORTH);
        // !!! Heading Panel 2 Components
        ImageIcon studentIcon = new ImageIcon("icons/students-icon-black.png");

        JLabel studentsLabel = new JLabel();
        studentsLabel.setText("Students Dashboard");
        studentsLabel.setIcon(studentIcon);
        studentsLabel.setFont(resources.montserratBold.deriveFont(12f));
        studentsLabel.setForeground(resources.antiflashWhite);
        headingPanel2.add(studentsLabel, BorderLayout.WEST);

        // !! Student Pin Panel
        JPanel studentPinPanel = new JPanel();
        studentPinPanel.setLayout(new GridLayout(3,1));
        studentPinPanel.setBackground(resources.antiflashWhite);
        studentPinPanel.setBorder(new EmptyBorder(0,5,0,5));
        studentPinPanel.setPreferredSize(new Dimension(405,700));
        studentPanel.add(studentPinPanel, BorderLayout.CENTER);

        // !!! Student Pin Components

        // !!!! Student Counter Panel
        JPanel studentCounterPanel = new JPanel();
        studentCounterPanel.setLayout(new BorderLayout());
        studentCounterPanel.setBackground(resources.airSuperiorityBlue);
        studentCounterPanel.setBorder(resources.normalPadding);
        studentPinPanel.add(studentCounterPanel);

        // !!!!! Student Counter Components
        JLabel studentHeader = new JLabel();
        studentHeader.setText("Number of Students:");
        studentHeader.setFont(resources.montserratBlack.deriveFont(20f));
        studentHeader.setForeground(resources.antiflashWhite);
        studentCounterPanel.add(studentHeader, BorderLayout.NORTH);

        JLabel studentCountLabel = new JLabel();
        studentCountLabel.setText("69420"); // use getter or similar method to count nodes of students
        studentCountLabel.setFont(resources.montserrat.deriveFont(50f));
        studentCountLabel.setForeground(resources.antiflashWhite);
        studentCounterPanel.add(studentCountLabel, BorderLayout.CENTER);

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
        containerPanel1.setBorder(resources.normalPadding);
        containerPanel1.setBackground(resources.antiflashWhite);
        containerPanel1.setPreferredSize(new Dimension(910,700));
        torPanel.add(containerPanel1, "1");

        // !! Container 1 Components

        // !! Search Panel
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setBorder(resources.normalPadding);
        searchPanel.setBackground(Color.darkGray);
        searchPanel.setPreferredSize(new Dimension(800,400));
        containerPanel1.add(searchPanel, BorderLayout.CENTER);

        // !!! Search Panel Components

        // !!!! Student Panel
        JPanel studentPanel = new JPanel();
        studentPanel.setBackground(Color.darkGray);
        studentPanel.setLayout(new BorderLayout());
        studentPanel.setBorder(resources.normalPadding);
        studentPanel.setPreferredSize(new Dimension(800,200));
        searchPanel.add(studentPanel, BorderLayout.NORTH);

        // !!!!! Student Icon Label
        ImageIcon studentIcon = new ImageIcon("icons/student-icon-black.png");
        ImageIcon scaledStudentIcon = resources.scaleImage(studentIcon, 100, 100);

        // !!!!! Student ID Label
        JLabel studentIdLabel = new JLabel();
        studentIdLabel.setText("2233375");
        studentIdLabel.setIcon(scaledStudentIcon);
        studentIdLabel.setFont(resources.montserratBlack.deriveFont(50f));
        studentIdLabel.setForeground(resources.antiflashWhite);
        studentIdLabel.setVerticalTextPosition(JLabel.BOTTOM);
        studentIdLabel.setHorizontalTextPosition(JLabel.CENTER);
        studentIdLabel.setHorizontalAlignment(JLabel.CENTER);
        studentIdLabel.setVerticalAlignment(JLabel.CENTER);
        studentPanel.add(studentIdLabel, BorderLayout.NORTH);

        // !!!!! Student Name
        JLabel studentNameLabel = new JLabel();
        studentNameLabel.setText("Jasmin, Marvin Rithik John" + " - " + "BSCS");
        studentNameLabel.setFont(resources.montserrat.deriveFont(20f));
        studentNameLabel.setForeground(resources.antiflashWhite);
        studentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        studentNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        studentPanel.add(studentNameLabel, BorderLayout.CENTER);

        // !!! ID Text Field
        JTextField idTextField = new JTextField(5);
        idTextField.setText("ID Number");
        idTextField.setFont(resources.montserrat.deriveFont(12f));
        idTextField.setPreferredSize(new Dimension(10,10));
        searchPanel.add(idTextField, BorderLayout.CENTER);

        idTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idTextField.getText().equals("ID Number")) {
                    idTextField.setText("");
                    idTextField.setForeground(Color.BLACK);
                } // end of if
            } // end of focusedGained method
            @Override
            public void focusLost(FocusEvent e) {
                if (idTextField.getText().isBlank()) {
                    idTextField.setText("ID Number");
                    idTextField.setForeground(Color.BLACK);
                } // end of if
            } // end of focusLost method
        }); // end of idTextField method

        // !! Buttons Panel 1
        JPanel buttonsPanel1 = new JPanel();
        buttonsPanel1.setLayout(new FlowLayout());
        buttonsPanel1.setBackground(Color.darkGray);
        buttonsPanel1.setPreferredSize(new Dimension(500, 100));
        searchPanel.add(buttonsPanel1, BorderLayout.SOUTH);

        // !!! Buttons Panel 1 Components

        // !!! Search Button
        ImageIcon searchIcon = new ImageIcon("icons/person-search-icon-black.png");

        JButton searchButton = new JButton();
        searchButton.setText("Search");
        searchButton.setIcon(searchIcon);
        searchButton.setFont(resources.montserrat.deriveFont(15f));
        searchButton.setOpaque(true);
        searchButton.setBorderPainted(false);
        searchButton.setBackground(resources.uranianBlue);
        searchButton.setForeground(Color.BLACK);
        buttonsPanel1.add(searchButton);

        // !!! Clear Button
        ImageIcon clearIcon = new ImageIcon("icons/clear_all-icon-black.png");

        JButton clearButton = new JButton();
        clearButton.setText("Clear");
        clearButton.setIcon(clearIcon);
        clearButton.setFont(resources.montserrat.deriveFont(15f));
        clearButton.setOpaque(true);
        clearButton.setBorderPainted(false);
        clearButton.setBackground(resources.uranianBlue);
        clearButton.setForeground(Color.BLACK);
        buttonsPanel1.add(clearButton);

        // !! Buttons Panel 2
        JPanel buttonsPanel2 = new JPanel();
        buttonsPanel2.setLayout(new FlowLayout());
        buttonsPanel2.setBackground(resources.antiflashWhite);
        buttonsPanel2.setPreferredSize(new Dimension(500,100));
        containerPanel1.add(buttonsPanel2, BorderLayout.SOUTH);

        // !!! Buttons Panel 2 Components

        // !!! Add Student Button
        ImageIcon addStudentIcon = new ImageIcon("icons/add-student-icon-black.png");
        JButton addStudentButton = new JButton();
        addStudentButton.setText("Add Student");
        addStudentButton.setIcon(addStudentIcon);
        addStudentButton.setFont(resources.montserratBold.deriveFont(20f));
        addStudentButton.setOpaque(true);
        addStudentButton.setBorderPainted(false);
        addStudentButton.setBackground(resources.airSuperiorityBlue);
        addStudentButton.setForeground(Color.BLACK);
        buttonsPanel2.add(addStudentButton);

        // !!! Remove Student Button
        ImageIcon removeStudentIcon = new ImageIcon("icons/remove-student-icon-black.png");
        JButton removeStudentButton = new JButton();
        removeStudentButton.setText("Remove Student");
        removeStudentButton.setIcon(removeStudentIcon);
        removeStudentButton.setFont(resources.montserratBold.deriveFont(20f));
        removeStudentButton.setOpaque(true);
        removeStudentButton.setBorderPainted(false);
        removeStudentButton.setBackground(resources.airSuperiorityBlue);
        removeStudentButton.setForeground(Color.BLACK);
        buttonsPanel2.add(removeStudentButton);

        // ! Container 2
        JPanel containerPanel2 = new JPanel();
        containerPanel2.setLayout(new BorderLayout());
        containerPanel2.setBackground(resources.antiflashWhite);
        containerPanel2.setPreferredSize(new Dimension(910,700));
        torPanel.add(containerPanel2, "2");

        // !! Container 2 Components

        // !!! Heading Panel
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setBorder(resources.thinPadding);
        headingPanel.setBackground(resources.yinmnBlue);
        headingPanel.setPreferredSize(new Dimension(910,40));
        containerPanel2.add(headingPanel, BorderLayout.NORTH);

        // !!!! Heading Panel Components

        // !!!! Transcript of Records Label
        JLabel torLabel = new JLabel();
        torLabel.setText("Transcript of Records");
        torLabel.setIcon(torButton.getIcon());
        torLabel.setFont(resources.montserratBold.deriveFont(11f));
        torLabel.setForeground(resources.antiflashWhite);
        torLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headingPanel.add(torLabel, BorderLayout.WEST);

        // !!! Body Panel
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setBorder(resources.thinPadding);
        bodyPanel.setBackground(Color.WHITE);
        containerPanel2.add(bodyPanel, BorderLayout.CENTER);

        // !!!! Body Panel Components

        // !!!! Top Panel
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(910, 35));
        bodyPanel.add(topPanel, BorderLayout.NORTH);

        // !!!!! Top Panel Components

        // !!!!! Year Label
        ImageIcon pinIcon = new ImageIcon("icons/pin-icon-black.png");
        JLabel yearLabel = new JLabel();
        yearLabel.setText("   Year 1");
        yearLabel.setIcon(pinIcon);
        yearLabel.setFont(resources.montserrat.deriveFont(13f));
        yearLabel.setForeground(Color.BLACK);
        yearLabel.setHorizontalAlignment(SwingConstants.LEFT);
        yearLabel.setVerticalAlignment(SwingConstants.CENTER);
        topPanel.add(yearLabel, BorderLayout.WEST);

        // !!!!! Nav Button Panel
        JPanel navButtonPanel = new JPanel();
        navButtonPanel.setLayout(new FlowLayout());
        navButtonPanel.setBackground(Color.WHITE);
        navButtonPanel.setPreferredSize(new Dimension(110, 20));
        topPanel.add(navButtonPanel, BorderLayout.EAST);

        // !!!!!! Nav Button Components

        // !!!!!! Previous Button
        ImageIcon prevIcon = new ImageIcon("icons/arrow_back-icon-black.png");
        ImageIcon scaledPrevIcon = resources.scaleImage(prevIcon, 15, 15);
        JButton prevButton = new JButton();
        prevButton.setIcon(scaledPrevIcon);
        prevButton.setFont(resources.montserrat.deriveFont(11f));
        prevButton.setOpaque(false);
        prevButton.setContentAreaFilled(false);
        prevButton.setBorderPainted(false);
        prevButton.setHorizontalAlignment(SwingConstants.RIGHT);
        prevButton.setVerticalAlignment(SwingConstants.CENTER);
        navButtonPanel.add(prevButton);

        // !!!!!! Next Button
        ImageIcon nextIcon = new ImageIcon("icons/arrow_forward-icon-black.png");
        ImageIcon scaledNextIcon = resources.scaleImage(nextIcon, 15, 15);
        JButton nextButton = new JButton();
        nextButton.setIcon(scaledNextIcon);
        nextButton.setOpaque(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);
        nextButton.setHorizontalAlignment(SwingConstants.RIGHT);
        nextButton.setVerticalAlignment(SwingConstants.CENTER);
        navButtonPanel.add(nextButton);

        // !!!! Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,1));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(resources.thinPadding);
        bodyPanel.add(tablePanel, BorderLayout.CENTER);

        // !!!!! Table Panel Components
        String[] columnNames = {"Course Number" , "Descriptive Title" , "Grade" , "Units"};
        String [][] data = {
                {"CS211", "Data Structures" , "99" , "3"}
        };

        JTable table = new JTable(new DefaultTableModel(
                new Object[]{"Course Number","Descriptive Title","Units","Grade"},0));
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(501);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(resources.montserratBold.deriveFont(12f));
        table.setAutoResizeMode(0);
        table.setDragEnabled(false);
        table.setOpaque(false);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(resources.montserrat.deriveFont(10f));

        table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
            @Override
            public boolean isCellEditable(EventObject e) {
                return false;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setOpaque(false);
        tablePanel.add(scrollPane);


        // !!!!! CRUD Button Panel
        JPanel crudButtons = new JPanel();
        crudButtons.setLayout(new FlowLayout());
        crudButtons.setBackground(Color.WHITE);
        crudButtons.setPreferredSize(new Dimension(910, 40));
        bodyPanel.add(crudButtons, BorderLayout.SOUTH);

        // !!!!!! CRUD Button Components

        // !!!!!! Add Button

        // !!!!!! Edit Button
        ImageIcon editIcon = new ImageIcon("icons/edit-icon-black.png");
        JButton editButton = new JButton();
        editButton.setText("Edit");
        editButton.setIcon(editIcon);
        editButton.setFont(resources.montserrat.deriveFont(11f));
        editButton.setOpaque(true);
        editButton.setBorderPainted(false);
        editButton.setBackground(resources.uranianBlue);
        editButton.setForeground(Color.BLACK);
        crudButtons.add(editButton);

        // !!!!!! Delete Button
        ImageIcon deleteIcon = new ImageIcon("icons/delete-icon-black.png");
        JButton deleteButton = new JButton();
        deleteButton.setText("Delete");
        deleteButton.setIcon(deleteIcon);
        deleteButton.setFont(resources.montserrat.deriveFont(11f));
        deleteButton.setOpaque(true);
        deleteButton.setBorderPainted(false);
        deleteButton.setBackground(resources.uranianBlue);
        deleteButton.setForeground(Color.BLACK);
        crudButtons.add(deleteButton);

        // !!!!!! Export Button
        ImageIcon exportIcon = new ImageIcon("icons/export-icon-black.png");
        JButton exportButton = new JButton();
        exportButton.setText("Export as CSV");
        exportButton.setIcon(exportIcon);
        exportButton.setFont(resources.montserrat.deriveFont(11f));
        exportButton.setOpaque(true);
        exportButton.setBorderPainted(false);
        exportButton.setBackground(resources.uranianBlue);
        exportButton.setForeground(Color.BLACK);
        crudButtons.add(exportButton);

        // Action Listeners
        // FIXME: 9/17/2023
        searchButton.addActionListener(e->{
            cardLayout1.show(torPanel, "2");
        });

        return torPanel;
    } // end of populateTorPanel

    /**
     * TODO: Documentation
     * @return
     */
    private JPanel populateChecklistPanel() {
        // Checklist Panel
        JPanel checklistPanel = new JPanel();
        checklistPanel.setLayout(new BorderLayout());
        checklistPanel.setBackground(resources.antiflashWhite);
        checklistPanel.setPreferredSize(new Dimension(910,700));

        // ! Checklist Panel Components
        // TODO: Supporting Code

        return checklistPanel;
    } // end of populateChecklistPanel method

    /*
    To be removed.
    Will only be used for testing.
     */
    public static void main(String[] args) {
        new Portal();
    } // end of main method
} // end of class Portal
