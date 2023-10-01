package project2.frontend;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author ROXAS, Johan Rickardo
 * @version 1.00 (16 September 2023)
 * Template for object Resources.
 * Resources for the UI.
 */
public class Resources {
    /**
     * Empty border that acts as a padding for JPanel components.
     * Normal padding
     */
    EmptyBorder normalPadding = new EmptyBorder(20,30,20,30);
    /**
     * Empty border that acts as a padding for JPanel components.
     * Thin padding
     */
    EmptyBorder thinPadding = new EmptyBorder(10,10,10,10);

    /**
     * Cursor used for buttons
     */
    Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);

    /**
     * Default cursor
     */
    Cursor defaultCursor = new Cursor(Cursor.DEFAULT_CURSOR);

    /**
     * Cursor used for texts
     */
    Cursor textCursor = new Cursor(Cursor.TEXT_CURSOR);

    /**
     * Thin Montserrat font
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
     * Antiflash White.
     * Primary color for the UI.
     */
    final Color antiflashWhite = new Color(231,236,239);

    /**
     * Yinmn Blue.
     * Primary color for the UI.
     */
    final Color yinmnBlue = new Color(39,76,119);

    /**
     * Air Superiority Blue.
     * Secondary color for the UI.
     */
    final Color airSuperiorityBlue = new Color(96,150,186);

    /**
     * Uranian Blue.
     * Secondary color for the UI.
     */
    final Color uranianBlue = new Color(163,206,241);

    /**
     * Battleship Gray.
     * Secondary color for the UI.
     */
    final Color battleshipGray = new Color(139,140,137);

    /**
     * Lipstick Red.
     * Color used for links.
     */
    final Color lipstickRed = new Color(230, 57, 70);

    /**
     * Official logo and seal of Saint Louis University
     */
    ImageIcon sluLogo = new ImageIcon("img/slu-logo.png");

    void loadFonts() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // Montserrat Thin
            montserratThin = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Montserrat/static/Montserrat-Thin.ttf")).deriveFont(20f);
            ge.registerFont(montserratThin);

            // Montserrat Regular
            montserrat = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Montserrat/static/Montserrat-Regular.ttf")).deriveFont(20f);
            ge.registerFont(montserrat);

            // Montserrat Bold
            montserratBold = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Montserrat/static/Montserrat-Bold.ttf")).deriveFont(20f);
            ge.registerFont(montserratBold);

            // Montserrat Black
            montserratBlack = Font.createFont(Font.TRUETYPE_FONT,
                    new File("fonts/Montserrat/static/Montserrat-Black.ttf")).deriveFont(20f);
            ge.registerFont(montserratBlack);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (FontFormatException fontError) {
            System.out.println(fontError.getMessage());
            fontError.printStackTrace();
        } // end of try-catch
    } // end of loadFonts method

    /**
     * Resizes an image according to desired dimensions
     * @param sourceImage given image to be resized
     * @param width desired width in pixels
     * @param height desired height in pixels
     * @return scaled ImageIcon
     */
    ImageIcon scaleImage(ImageIcon sourceImage, int width, int height) {
        int newWidth = sourceImage.getIconWidth();
        int newHeight = sourceImage.getIconHeight();

        if (sourceImage.getIconWidth() > width) {
            newWidth = width;
            newHeight = (newWidth * sourceImage.getIconHeight()) / sourceImage.getIconWidth();
        } else if (newHeight > height) {
            newHeight = height;
            newWidth = (sourceImage.getIconWidth() * newHeight) / sourceImage.getIconHeight();
        } // end of if-else
        return new ImageIcon(sourceImage.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH));
    } // end of scaleImage method
} // end of class Resources
