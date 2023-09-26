package project2.frontend;

import project2.backend.LinkedList;
import project2.backend.Node;
import project2.course.BSCS.CourseUtility;
import project2.referenceclasses.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EventObject;

import project2.referenceclasses.Student;

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
     * An object of CourseUtility that is used to invoke the LinkedLists of courses used in the curriculum checklist.
     */
    private final CourseUtility courseChecklist = new CourseUtility();

    private boolean editable = false;

    private JTextField idTextField;

    /**
     * TODO: Documentation
     */
    private JButton homeButton;

    private Student studentSearched;
    /**
     * TODO: Documentation
     */
    private JButton scheduleButton;

    private JPanel cardPanel = new JPanel();

    /**
     * TODO: Documentation
     */
    private JButton torButton;

    private JButton nextTorButton;

    /**
     * TODO: Documentation
     */
    private JButton checklistButton;

    private JLabel studentCountLabel;

    /**
     * TODO: Documentation
     */
    private JButton personalDetailsButton;
    private JFrame addStudentFrame;

    private JFrame removeStudentFrame;
    /**
     * Card Layout used for the centerPanel
     */
    private final CardLayout cardLayout1 = new CardLayout(10,20);

    /**
     * Card Layout used for components inside centerPanel
     */
    private final CardLayout cardLayout2 = new CardLayout(10,20);

    private int year;

    private int sem;

    private JPanel torPanel;

    /**
     * Constructs an object of Portal.
     * Contains the components of the SLU Portal.
     */
    public Portal(Node<Admin> admin) {
        super("SLU Portal");

        resources.loadFonts();
        setIconImage(resources.sluLogo.getImage());

        // Main Panel
        JPanel mainPanel = new JPanel(); // Holds the whole window
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);

        // ! Header Panel
        JPanel headerPanel = populateHeader(admin); // Holds the header
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
        torPanel = populateTorPanel();
        cardPanel.add(torPanel, "tor");

        // !!! Checklist Panel
        JPanel checklistPanel = populateChecklistPanel();
        cardPanel.add(checklistPanel,"checklist");

        // !!! Personal Details Panel
        JPanel personalPanel = populatePersonalPanel();
        cardPanel.add(personalPanel, "personal");

        // Action Listeners
        homeButton.addActionListener(e -> {
            cardLayout1.show(cardPanel, "home");
            studentCountLabel.setText(String.valueOf(Main.studentLinkedList.getSize())); // use getter or similar method to count nodes of students
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
            studentNameLabel.setForeground(resources.antiflashWhite);
            studentIdLabel.setText("ID Number");
            studentNameLabel.setText("Last Name, First Name");
            idTextField.setText("ID Number");
            nextTorButton.setVisible(false);
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
            homeButton.setForeground(Color.BLACK);
            scheduleButton.setForeground(Color.BLACK);
            torButton.setForeground(Color.BLACK);
            checklistButton.setForeground(Color.BLACK);
            personalDetailsButton.setForeground(resources.antiflashWhite);
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,700);
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);
    } // end of Portal constructor

    /**
     * TODO: Documentation
     * @return
     */
    private JPanel populateHeader(Node<Admin> admin) {
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
        nameLabel.setText(admin.getData().getFirstName() + " " + admin.getData().getLastName());
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
        torButton.setFocusable(false);
        torButton.setFocusPainted(false);
        navPanel.add(torButton, gbc);

        // !! Statement of Curriculum Checklist Button
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

        studentCountLabel = new JLabel();
        studentCountLabel.setText(String.valueOf(Main.studentLinkedList.getSize())); // use getter or similar method to count nodes of students
        studentCountLabel.setFont(resources.montserrat.deriveFont(50f));
        studentCountLabel.setForeground(resources.antiflashWhite);
        studentCounterPanel.add(studentCountLabel, BorderLayout.CENTER);

        return homePanel;
    } // end of populateHomePanel method

    /**
     * TODO: Documentation
     * @return
     */

    private void updateStudentShown(Student studentObj) {
        if (studentObj==null) {
            studentIdLabel.setText("Student not found");
            studentNameLabel.setText("Please enter the ID number again.");
            studentNameLabel.setForeground(resources.lipstickRed);
        }
        else {
            studentNameLabel.setForeground(resources.antiflashWhite);
            studentIdLabel.setText(studentObj.getIdNumber());
            studentNameLabel.setText(studentObj.getLastName() + ", " + studentObj.getFirstName());
        }
    }

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

    public JLabel studentIdLabel = new JLabel();
    public JLabel studentNameLabel = new JLabel();
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
        studentIdLabel.setText("ID Number");
        studentIdLabel.setIcon(scaledStudentIcon);
        studentIdLabel.setFont(resources.montserratBlack.deriveFont(50f));
        studentIdLabel.setForeground(resources.antiflashWhite);
        studentIdLabel.setVerticalTextPosition(JLabel.BOTTOM);
        studentIdLabel.setHorizontalTextPosition(JLabel.CENTER);
        studentIdLabel.setHorizontalAlignment(JLabel.CENTER);
        studentIdLabel.setVerticalAlignment(JLabel.CENTER);
        studentPanel.add(studentIdLabel, BorderLayout.NORTH);

        // !!!!! Student Name
        studentNameLabel.setText("Last Name, First Name");
        studentNameLabel.setFont(resources.montserrat.deriveFont(20f));
        studentNameLabel.setForeground(resources.antiflashWhite);
        studentNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        studentNameLabel.setVerticalAlignment(SwingConstants.CENTER);
        studentPanel.add(studentNameLabel, BorderLayout.CENTER);

        // !!! ID Text Field
        idTextField = new JTextField(5);
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
        searchButton.setFocusable(false);
        searchButton.setFocusPainted(false);
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
        clearButton.setFocusable(false);
        clearButton.setFocusPainted(false);
        buttonsPanel1.add(clearButton);

        ImageIcon nextIcon = new ImageIcon("icons/arrow_forward-icon-black.png");
        nextTorButton = new JButton();
        nextTorButton.setText("Next");
        nextTorButton.setIcon(nextIcon);
        nextTorButton.setFont(resources.montserrat.deriveFont(15f));
        nextTorButton.setOpaque(true);
        nextTorButton.setBorderPainted(false);
        nextTorButton.setBackground(resources.uranianBlue);
        nextTorButton.setForeground(Color.BLACK);
        nextTorButton.setFocusable(false);
        nextTorButton.setFocusPainted(false);
        nextTorButton.setVisible(false);
        buttonsPanel1.add(nextTorButton);

        // Action Listeners
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentSearched = Main.search(idTextField.getText());
                updateStudentShown(studentSearched);
                if (!studentNameLabel.getText().equalsIgnoreCase("ID Number")
                        && !studentIdLabel.getText().equalsIgnoreCase("Last Name, First Name")) {
                    nextTorButton.setVisible(true);
                }

            }
        });



        clearButton.addActionListener(e -> {
            studentNameLabel.setForeground(resources.antiflashWhite);
            studentIdLabel.setText("ID Number");
            studentNameLabel.setText("Last Name, First Name");
            idTextField.setText("ID Number");
        });

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
        addStudentButton.setFocusable(false);
        addStudentButton.setFocusPainted(false);
        buttonsPanel2.add(addStudentButton);

        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudentFrame();
            }
        });

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
        removeStudentButton.setFocusable(false);
        removeStudentButton.setFocusPainted(false);
        buttonsPanel2.add(removeStudentButton);
        removeStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudentFrame();
            }
        });

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
        prevButton.setFocusable(false);
        prevButton.setFocusPainted(false);
        navButtonPanel.add(prevButton);

        // !!!!!! Next Button
        ImageIcon scaledNextIcon = resources.scaleImage(nextIcon, 15, 15);
        JButton nextButton = new JButton();
        nextButton.setIcon(scaledNextIcon);
        nextButton.setOpaque(false);
        nextButton.setContentAreaFilled(false);
        nextButton.setBorderPainted(false);
        nextButton.setHorizontalAlignment(SwingConstants.RIGHT);
        nextButton.setVerticalAlignment(SwingConstants.CENTER);
        nextButton.setFocusable(false);
        nextButton.setFocusPainted(false);
        navButtonPanel.add(nextButton);

        // !!!! Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,1));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(resources.thinPadding);
        bodyPanel.add(tablePanel, BorderLayout.CENTER);

        // !!!!! Table Panel Components
        JTable table = new JTable(new DefaultTableModel(
                new Object[]{"Course Number","Descriptive Title","Units","Grade"},0));
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(541);
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
        table.setFont(resources.montserrat.deriveFont(14f));

        table.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    if (year == 1) {
                        if (sem == 1) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getFirstSemSemesterList().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                        if (sem == 2) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getSecondSemSemesterList().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                        if (sem == 3) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getShortTerm().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                    }
                    if (year == 2) {
                        if (sem == 1) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getData().getFirstSemSemesterList().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                        if (sem == 2) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getData().getSecondSemSemesterList().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                        if (sem == 3) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getData().getShortTerm().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                    }
                    if (year == 3) {
                        if (sem == 1) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getData().getFirstSemSemesterList().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                        if (sem == 2) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getData().getSecondSemSemesterList().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                        if (sem == 3) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getData().getShortTerm().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                    }
                    if (year == 4) {
                        if (sem == 1) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getNext().getData().getFirstSemSemesterList().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                        if (sem == 2) {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getNext().getData().getSecondSemSemesterList().getHead();
                            for (int x = 0; x <= e.getLastRow(); x++) {
                                if (x == e.getFirstRow()) {
                                    pointer.getData().setGrade(Integer.valueOf((String) (table.getValueAt(x, 3))));
                                }
                                pointer = pointer.getNext();
                            }
                        }
                    }
                }
            }

        });
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        nextTorButton.addActionListener(e -> {
            ((DefaultTableModel) table.getModel()).setRowCount(0);
            cardLayout1.show(torPanel,"2");
            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getFirstSemSemesterList().getHead();
            while (pointer!=null) {
                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                        pointer.getData().getDescriptiveName(),
                        pointer.getData().getUnits(),
                        grade
                });
                pointer = pointer.getNext();
            } // end of for
        });


        // Action Listeners

        nextButton.addActionListener(e -> {
            if  (year == 4 && sem == 2) {
                year = 1;
                sem = 1;
            } else {
                sem++;
                if (sem > 3) {
                    sem = 1;
                    year++;
                    if (year > 4) {
                        sem = 1;
                        year = 1;
                    } // end of if
                } // end of if
            }
            String semInString = "";
            switch (sem) {
                case 1 -> semInString = "First Semester";
                case 2 -> semInString = "Second Semester";
                case 3 -> semInString = "Short Term";
            }
            yearLabel.setText("   Year " + year + ", " + semInString);
            ((DefaultTableModel) table.getModel()).setRowCount(0);

            switch (year) {
                case 1 -> {
                    switch (sem) {
                        case 1 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getFirstSemSemesterList().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getSecondSemSemesterList().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getShortTerm().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 1
                case 2 -> {
                    switch (sem) {
                        case 1 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getData().getFirstSemSemesterList().getHead();
                            while (pointer != null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        }
                        case 2 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getData().getSecondSemSemesterList().getHead();
                            while (pointer != null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getData().getShortTerm().getHead();
                            while (pointer != null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 2
                case 3 -> {
                    switch (sem) {
                        case 1 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getData().getFirstSemSemesterList().getHead();
                            while (pointer != null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of while
                        } // end of case for sem 1
                        case 2 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getData().getSecondSemSemesterList().getHead();
                            while (pointer != null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of while
                        } // end of case for sem 2
                        case 3 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getData().getShortTerm().getHead();
                            while (pointer != null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of while
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 3
                case 4 -> {
                    switch (sem) {
                        case 1 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getNext().getData().getFirstSemSemesterList().getHead();
                            while (pointer != null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of while
                        } // end of case for sem 1
                        case 2 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getNext().getData().getSecondSemSemesterList().getHead();
                            while (pointer != null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of while
                        } // end of case for sem 2
                    } // end of switch-case for sem
                } // end of case for year 4
            } // end of switch-case for year
        });


        prevButton.addActionListener(e -> {
            if (year == 1 & sem ==1) {
                year = 4;
                sem = 2;
            } else {
                sem--;
                if (sem < 1) {
                    sem = 3;
                    year--;
                    if (year < 1) {
                        sem = 3;
                        year = 4;
                    } // end of if
                } // end of if
            }

            String semInString = "";
            switch (sem) {
                case 1 -> semInString = "First Semester";
                case 2 -> semInString = "Second Semester";
                case 3 -> semInString = "Short Term";
            }
            yearLabel.setText("   Year " + year + ", " + semInString);

            ((DefaultTableModel) table.getModel()).setRowCount(0);
            switch (year) {
                case 1 -> {
                    switch (sem) {
                        case 1 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getFirstSemSemesterList().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getSecondSemSemesterList().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getShortTerm().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 1
                case 2 -> {
                    switch (sem) {
                        case 1 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getData().getFirstSemSemesterList().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getSecondSemSemesterList().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getData().getShortTerm().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 2
                case 3 -> {
                    switch (sem) {
                        case 1 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getData().getFirstSemSemesterList().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getData().getSecondSemSemesterList().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getData().getShortTerm().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 3
                case 4 -> {
                    switch (sem) {
                        case 1 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getNext().getData().getFirstSemSemesterList().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            Node<Course> pointer = studentSearched.getYearList().getHead().getNext().getNext().getNext().getData().getSecondSemSemesterList().getHead();
                            while (pointer!=null) {
                                String grade = (pointer.getData().getGrade() != 0) ? String.valueOf(pointer.getData().getGrade()) : "NT";
                                model.addRow(new Object[]{pointer.getData().getCourseNumber(),
                                        pointer.getData().getDescriptiveName(),
                                        pointer.getData().getUnits(),
                                        grade
                                });
                                pointer = pointer.getNext();
                            } // end of for
                        } // end of case for sem 2
                    } // end of switch-case for sem
                } // end of case for year 4
            } // end of switch-case for year
        });

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
        editButton.setFocusable(false);
        editButton.setFocusPainted(false);
        crudButtons.add(editButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (editable) {
                    table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
                        @Override
                        public boolean isCellEditable(EventObject e) {
                            return false;
                        }
                    });

                    editButton.setText("Edit");
                    editable = false;
                } else {
                    table.setDefaultEditor(Object.class, new DefaultCellEditor(new JTextField()) {
                        @Override
                        public boolean isCellEditable(EventObject e) {
                            return true;
                        }
                    });
                    editButton.setText("Done");
                    editable = true;
                }
            }

                                         ;
                                     });


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
        deleteButton.setFocusable(false);
        deleteButton.setFocusPainted(false);
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
        exportButton.setFocusable(false);
        exportButton.setFocusPainted(false);
        crudButtons.add(exportButton);

        return torPanel;
    } // end of populateTorPanel

    /**
     * @Author: LACANILAO
     * TODO: add handling exceptions
     * TODO: add remove student frame
     *
     */
    private void addStudentFrame() {
        addStudentFrame = new JFrame("Add Student");
        ImageIcon sluStudLogo = resources.scaleImage(resources.sluLogo, 25, 25);
        addStudentFrame.setIconImage(sluStudLogo.getImage());
        addStudentFrame.setSize(600, 500);
        addStudentFrame.setLocationRelativeTo(null);
        addStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel frameContent = new JPanel();
        frameContent.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(500, 100));
        topPanel.setBackground(resources.antiflashWhite);


        ImageIcon logoIcon = new ImageIcon("icons/add-student-icon-black.png");
        ImageIcon scaledLogo = resources.scaleImage(logoIcon, 200, 200);
        JLabel logoLabel = new JLabel(scaledLogo);

        topPanel.setLayout(new BorderLayout());
        topPanel.add(logoLabel, BorderLayout.CENTER);

        frameContent.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(500, 300));
        bottomPanel.setBackground(Color.darkGray);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.darkGray);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel inputNeededLabel = new JLabel("Input needed details");
        inputNeededLabel.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
        inputNeededLabel.setForeground(Color.WHITE);
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        inputNeededLabel.setHorizontalAlignment(JLabel.CENTER);
        inputPanel.add(inputNeededLabel, gbc);

        JTextField idTextField = new JTextField(20);
        idTextField.setFont(new Font("Montserrat", Font.PLAIN, 24));
        JLabel idLabel = new JLabel("ID Number:");
        idLabel.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
        idLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        inputPanel.add(idLabel, gbc);
        gbc.gridy = 3;
        inputPanel.add(idTextField, gbc);

        // Add input validation for ID number
        JLabel idErrorLabel = new JLabel("ID number must be exactly 7 characters long");
        idErrorLabel.setFont(new Font("Montserrat", Font.BOLD, 16));
        idErrorLabel.setForeground(Color.RED);
        gbc.gridy = 8;
        inputPanel.add(idErrorLabel, gbc);
        idErrorLabel.setVisible(false);

        DocumentFilter idDocumentFilter = new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) <= 7) {
                    super.replace(fb, offset, length, text, attrs);
                    idErrorLabel.setVisible(false);
                } else {
                    idErrorLabel.setVisible(true);
                }
            }
        };

        ((AbstractDocument) idTextField.getDocument()).setDocumentFilter(idDocumentFilter);

        JTextField firstNameTextField = new JTextField(20);
        firstNameTextField.setFont(new Font("Montserrat", Font.PLAIN, 24));
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
        firstNameLabel.setForeground(Color.WHITE);
        gbc.gridy = 4;
        inputPanel.add(firstNameLabel, gbc);
        gbc.gridy = 5;
        inputPanel.add(firstNameTextField, gbc);

        JTextField lastNameTextField = new JTextField(20);
        lastNameTextField.setFont(new Font("Montserrat", Font.PLAIN, 24));
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
        lastNameLabel.setForeground(Color.WHITE);
        gbc.gridy = 6;
        inputPanel.add(lastNameLabel, gbc);
        gbc.gridy = 7;
        inputPanel.add(lastNameTextField, gbc);

        bottomPanel.add(inputPanel);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("ADD");
        JButton cancelButton = new JButton("CANCEL");

        addButton.setFont(new Font("Montserrat Bold", Font.BOLD, 24));
        addButton.setBackground(resources.uranianBlue);
        addButton.setForeground(Color.BLACK);
        addButton.setBorderPainted(false);

        cancelButton.setFont(new Font("Montserrat Bold", Font.BOLD, 24));
        cancelButton.setBackground(resources.uranianBlue);
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setBorderPainted(false);

        buttonPanel.setBackground(resources.yinmnBlue);
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        frameContent.add(bottomPanel, BorderLayout.CENTER);
        frameContent.add(buttonPanel, BorderLayout.SOUTH);


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idTextField.getText(); // can modify, just used for testing
                String firstNameText = firstNameTextField.getText();
                String lastNameText = lastNameTextField.getText();

                boolean validIdLength = idText.length() == 7;

                if (idText.isEmpty() || firstNameText.isEmpty() || lastNameText.isEmpty() || !validIdLength) {
                    inputNeededLabel.setForeground(Color.RED);
                    inputNeededLabel.setText("Make sure to check and input all needed details.");
                    idErrorLabel.setVisible(!validIdLength);
                } else {
                    // Handle the Add button click (add the student)
                    // Add student to data
                    addStudentFrame.dispose(); // Close the frame when done
                }

                Main.addStudent(idText, firstNameText, lastNameText, Main.computerScience);

            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame confirmDialogFrame = new JFrame("Confirm Cancel");
                ImageIcon confirmCancelLogo = resources.scaleImage(resources.sluLogo, 25, 25);
                addStudentFrame.setIconImage(confirmCancelLogo.getImage());
                confirmDialogFrame.setSize(400, 150);
                confirmDialogFrame.setLocationRelativeTo(addStudentFrame);
                confirmDialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel confirmPanel = new JPanel();
                confirmPanel.setLayout(new BorderLayout());
                confirmPanel.setBackground(Color.darkGray);

                JLabel confirmLabel = new JLabel("Are you sure you want to cancel?");
                confirmLabel.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
                confirmLabel.setForeground(Color.WHITE);
                confirmLabel.setHorizontalAlignment(JLabel.CENTER);
                confirmPanel.add(confirmLabel, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setBackground(resources.yinmnBlue);
                JButton yesButton = new JButton("Yes");
                JButton noButton = new JButton("No");

                yesButton.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
                yesButton.setForeground(Color.BLACK);
                yesButton.setBackground(resources.uranianBlue);
                yesButton.setBorderPainted(false);

                noButton.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
                noButton.setForeground(Color.BLACK);
                noButton.setBackground(resources.uranianBlue);
                noButton.setBorderPainted(false);

                buttonPanel.add(yesButton);
                buttonPanel.add(noButton);

                yesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmDialogFrame.dispose();
                        addStudentFrame.dispose();
                    }
                });
                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmDialogFrame.dispose();
                    }
                });

                confirmDialogFrame.add(confirmPanel, BorderLayout.CENTER);
                confirmDialogFrame.add(buttonPanel, BorderLayout.SOUTH);
                confirmDialogFrame.setVisible(true);
            }
        });

        addStudentFrame.setContentPane(frameContent);
        addStudentFrame.setVisible(true);
    } // end of addStudentFrame

    private void removeStudentFrame() {
        removeStudentFrame = new JFrame("Remove Student");
        ImageIcon sluStudLogo = resources.scaleImage(resources.sluLogo, 25, 25);
        removeStudentFrame.setIconImage(sluStudLogo.getImage());
        removeStudentFrame.setSize(600, 500);
        removeStudentFrame.setLocationRelativeTo(null);
        removeStudentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel frameContent = new JPanel();
        frameContent.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(500, 100));
        topPanel.setBackground(Color.WHITE);

        ImageIcon removelogoIcon = new ImageIcon("icons/remove-student-icon-black.png");
        ImageIcon removeScaledLogo = resources.scaleImage(removelogoIcon, 120, 120);
        JLabel logoLabel = new JLabel(removeScaledLogo);
        topPanel.add(logoLabel, BorderLayout.CENTER);

        frameContent.add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(500, 400));
        bottomPanel.setBackground(Color.darkGray);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        inputPanel.setBackground(Color.darkGray);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 10, 5);

        JLabel inputNeededLabel = new JLabel("Input ID Number to Remove Student");
        inputNeededLabel.setFont(new Font("Montserrat Bold", Font.BOLD, 25));
        inputNeededLabel.setForeground(Color.WHITE);
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        inputNeededLabel.setHorizontalAlignment(JLabel.CENTER);
        inputPanel.add(inputNeededLabel, gbc);

        JTextField idTextField = new JTextField(15);
        idTextField.setFont(new Font("Montserrat", Font.PLAIN, 30));
        JLabel idLabel = new JLabel("ID Number:");
        idLabel.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
        idLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        inputPanel.add(idLabel, gbc);
        gbc.gridy = 2;
        inputPanel.add(idTextField, gbc);

        JLabel confirmIdLabel = new JLabel("Confirm ID Number:");
        confirmIdLabel.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
        confirmIdLabel.setForeground(Color.WHITE);
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        inputPanel.add(confirmIdLabel, gbc);

        JTextField confirmIdTextField = new JTextField(15);
        confirmIdTextField.setFont(new Font("Montserrat", Font.PLAIN, 30));
        gbc.gridy = 4;
        inputPanel.add(confirmIdTextField, gbc);

        JLabel errorLabel = new JLabel("The ID number and the confirmation ID number must match");
        errorLabel.setFont(new Font("Montserrat", Font.BOLD, 16));
        errorLabel.setForeground(Color.RED);
        gbc.gridy = 5;
        inputPanel.add(errorLabel, gbc);
        errorLabel.setVisible(false);

        DocumentFilter documentFilter = new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) <= 7) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        };

        ((AbstractDocument) idTextField.getDocument()).setDocumentFilter(documentFilter);
        ((AbstractDocument) confirmIdTextField.getDocument()).setDocumentFilter(documentFilter);

        bottomPanel.add(inputPanel);

        JPanel buttonPanel = new JPanel();
        JButton removeButton = new JButton("REMOVE");
        JButton cancelButton = new JButton("CANCEL");

        removeButton.setFont(new Font("Montserrat Bold", Font.BOLD, 24));
        removeButton.setBackground(resources.uranianBlue);
        removeButton.setForeground(Color.BLACK);
        removeButton.setBorderPainted(false);

        cancelButton.setFont(new Font("Montserrat Bold", Font.BOLD, 24));
        cancelButton.setBackground(resources.uranianBlue);
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setBorderPainted(false);

        buttonPanel.setBackground(resources.yinmnBlue);
        buttonPanel.add(removeButton);
        buttonPanel.add(cancelButton);

        frameContent.add(bottomPanel, BorderLayout.CENTER);
        frameContent.add(buttonPanel, BorderLayout.SOUTH);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idText = idTextField.getText();
                String confirmIdText = confirmIdTextField.getText();

                boolean idsMatch = idText.equals(confirmIdText);
                boolean validIdLength = idText.length() == 7;

                if (!idsMatch || !validIdLength) {
                    idLabel.setForeground(Color.RED);
                    confirmIdLabel.setForeground(Color.RED);
                    errorLabel.setVisible(true);
                } else {
                    idLabel.setForeground(Color.WHITE);
                    confirmIdLabel.setForeground(Color.WHITE);
                    errorLabel.setVisible(false);

                    removeStudentFrame.dispose();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame confirmDialogFrame = new JFrame("Confirm Cancel");
                ImageIcon confirmCancelLogo = resources.scaleImage(resources.sluLogo, 25, 25);
                addStudentFrame.setIconImage(confirmCancelLogo.getImage());
                confirmDialogFrame.setSize(400, 150);
                confirmDialogFrame.setLocationRelativeTo(addStudentFrame);
                confirmDialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel confirmPanel = new JPanel();
                confirmPanel.setLayout(new BorderLayout());
                confirmPanel.setBackground(Color.darkGray);

                JLabel confirmLabel = new JLabel("Are you sure you want to cancel?");
                confirmLabel.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
                confirmLabel.setForeground(Color.WHITE);
                confirmLabel.setHorizontalAlignment(JLabel.CENTER);
                confirmPanel.add(confirmLabel, BorderLayout.CENTER);

                JPanel buttonPanel = new JPanel();
                buttonPanel.setBackground(resources.yinmnBlue);
                JButton yesButton = new JButton("Yes");
                JButton noButton = new JButton("No");

                yesButton.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
                yesButton.setForeground(Color.BLACK);
                yesButton.setBackground(resources.uranianBlue);
                yesButton.setBorderPainted(false);

                noButton.setFont(new Font("Montserrat Bold", Font.BOLD, 16));
                noButton.setForeground(Color.BLACK);
                noButton.setBackground(resources.uranianBlue);
                noButton.setBorderPainted(false);

                buttonPanel.add(yesButton);
                buttonPanel.add(noButton);

                yesButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmDialogFrame.dispose();
                        addStudentFrame.dispose();
                    }
                });

                noButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        confirmDialogFrame.dispose();
                    }
                });

                confirmDialogFrame.add(confirmPanel, BorderLayout.CENTER);
                confirmDialogFrame.add(buttonPanel, BorderLayout.SOUTH);
                confirmDialogFrame.setVisible(true);
            }
        });

        removeStudentFrame.setContentPane(frameContent);
        removeStudentFrame.setVisible(true);
    }
    /**
     * TODO: Documentation
     * @return
     */
    private JPanel populateChecklistPanel() {
        year = 1;
        sem = 1;

        // Checklist Panel
        JPanel checklistPanel = new JPanel();
        checklistPanel.setLayout(new BorderLayout());
        checklistPanel.setBackground(resources.antiflashWhite);
        checklistPanel.setPreferredSize(new Dimension(910,700));

        // ! Checklist Panel Components

        // !!! Heading Panel
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setBorder(resources.thinPadding);
        headingPanel.setBackground(resources.yinmnBlue);
        headingPanel.setPreferredSize(new Dimension(910,40));
        checklistPanel.add(headingPanel, BorderLayout.NORTH);

        // !!!! Heading Panel Components

        // !!!! Curriculum Checklist Label
        JLabel checklistLabel = new JLabel();
        checklistLabel.setText("Curriculum Checklist");
        checklistLabel.setIcon(torButton.getIcon());
        checklistLabel.setFont(resources.montserratBold.deriveFont(11f));
        checklistLabel.setForeground(resources.antiflashWhite);
        checklistLabel.setHorizontalAlignment(SwingConstants.LEFT);
        headingPanel.add(checklistLabel, BorderLayout.WEST);

        // !!! Body Panel
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.setBorder(resources.thinPadding);
        bodyPanel.setBackground(Color.WHITE);
        checklistPanel.add(bodyPanel, BorderLayout.CENTER);

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
        yearLabel.setText("   Year " + year + ", " + "First Semester");
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
        prevButton.setFocusable(false);
        prevButton.setFocusPainted(false);
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
        nextButton.setFocusable(false);
        nextButton.setFocusPainted(false);
        navButtonPanel.add(nextButton);

        // !!!! Table Panel
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1,1));
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(resources.thinPadding);
        bodyPanel.add(tablePanel, BorderLayout.CENTER);

        // !!!!! Table Panel Components
        JTable table = new JTable(new DefaultTableModel(
                new Object[]{"Course Number","Descriptive Title","Units"},0));
        table.getColumnModel().getColumn(0).setPreferredWidth(130);
        table.getColumnModel().getColumn(1).setPreferredWidth(621);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setFont(resources.montserratBold.deriveFont(12f));
        table.setAutoResizeMode(0);
        table.setDragEnabled(false);
        table.setOpaque(false);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(resources.montserrat.deriveFont(14f));

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        for (Course course : courseChecklist.year1Sem1) {
            model.addRow(new Object[]{course.getCourseNumber(),
                    course.getDescriptiveName(),
                    course.getUnits()});
        } // end of for

        // Action Listeners

        nextButton.addActionListener(e -> {
            if  (year == 4 && sem == 2) {
                year = 1;
                sem = 1;
            } else {
                sem++;
                if (sem > 3) {
                    sem = 1;
                    year++;
                    if (year > 4) {
                        sem = 1;
                        year = 1;
                    } // end of if
                } // end of if
            }
            String semInString = "";
            switch (sem) {
                case 1 -> semInString = "First Semester";
                case 2 -> semInString = "Second Semester";
                case 3 -> semInString = "Short Term";
            }
            yearLabel.setText("   Year " + year + ", " + semInString);

            ((DefaultTableModel) table.getModel()).setRowCount(0);
            switch (year) {
                case 1 -> {
                    switch (sem) {
                        case 1 -> {
                            for (Course course : courseChecklist.year1Sem1) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            for (Course course : courseChecklist.year1Sem2) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            for (Course course : courseChecklist.year1Sem3) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 1
                case 2 -> {
                    switch (sem) {
                        case 1 -> {
                            for (Course course : courseChecklist.year2Sem1) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            for (Course course : courseChecklist.year2Sem2) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            for (Course course : courseChecklist.year2Sem3) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 2
                case 3 -> {
                    switch (sem) {
                        case 1 -> {
                            for (Course course : courseChecklist.year3Sem1) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            for (Course course : courseChecklist.year3Sem2) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            for (Course course : courseChecklist.year3Sem3) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 3
                case 4 -> {
                    switch (sem) {
                        case 1 -> {
                            for (Course course : courseChecklist.year4Sem1) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            for (Course course : courseChecklist.year4Sem2) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 2
                    } // end of switch-case for sem
                } // end of case for year 4
            } // end of switch-case for year
        });

        prevButton.addActionListener(e -> {
            if (year == 1 & sem ==1) {
                year = 4;
                sem = 2;
            } else {
                sem--;
                if (sem < 1) {
                    sem = 3;
                    year--;
                    if (year < 1) {
                        sem = 3;
                        year = 4;
                    } // end of if
                } // end of if
            }

            String semInString = "";
            switch (sem) {
                case 1 -> semInString = "First Semester";
                case 2 -> semInString = "Second Semester";
                case 3 -> semInString = "Short Term";
            }
            yearLabel.setText("   Year " + year + ", " + semInString);

            ((DefaultTableModel) table.getModel()).setRowCount(0);
            switch (year) {
                case 1 -> {
                    switch (sem) {
                        case 1 -> {
                            for (Course course : courseChecklist.year1Sem1) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            for (Course course : courseChecklist.year1Sem2) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            for (Course course : courseChecklist.year1Sem3) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 1
                case 2 -> {
                    switch (sem) {
                        case 1 -> {
                            for (Course course : courseChecklist.year2Sem1) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            for (Course course : courseChecklist.year2Sem2) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            for (Course course : courseChecklist.year2Sem3) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 2
                case 3 -> {
                    switch (sem) {
                        case 1 -> {
                            for (Course course : courseChecklist.year3Sem1) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            for (Course course : courseChecklist.year3Sem2) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 2
                        case 3 -> {
                            for (Course course : courseChecklist.year3Sem3) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 3
                    } // end of switch-case for sem
                } // end of case for year 3
                case 4 -> {
                    switch (sem) {
                        case 1 -> {
                            for (Course course : courseChecklist.year4Sem1) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 1
                        case 2 -> {
                            for (Course course : courseChecklist.year4Sem2) {
                                model.addRow(new Object[]{course.getCourseNumber(),
                                        course.getDescriptiveName(),
                                        course.getUnits()});
                            } // end of for
                        } // end of case for sem 2
                    } // end of switch-case for sem
                } // end of case for year 4
            } // end of switch-case for year
        });

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
        editButton.setFocusable(false);
        editButton.setFocusPainted(false);
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
        deleteButton.setFocusable(false);
        deleteButton.setFocusPainted(false);
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
        exportButton.setFocusable(false);
        exportButton.setFocusPainted(false);
        crudButtons.add(exportButton);

        return checklistPanel;
    } // end of populateChecklistPanel method

    private JPanel populatePersonalPanel() {
        JPanel personalPanel = new JPanel();
        personalPanel.setLayout(new BorderLayout());
        personalPanel.setBackground(resources.uranianBlue);
        personalPanel.setPreferredSize(new Dimension(910,700));
        return personalPanel;
    }

    /**
     * TODO: Documentation
     */

} // end of class Portal
