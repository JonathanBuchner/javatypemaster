/**
 * Display.java
 * 
 * This class will handle the display of the program.  It will create the main window.
 * 
 * @author Jonathan Buchner Nov 2023.
 * 
 * Note: This class was heavily influence by the week 9 lecture.  I also looked up
 * a way to do padding and a recommendation was to use a border.  I also asked
 * chatgpt what was wrong with my code as it was not aligning correctly and
 * it informed me that I needed to add GridBagConstraints instead of
 * BorderLayout.CENTER.
 */

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Display {
    private JFrame frame;
    private ChallengeDisplay challengeDisplay;
    
    /**
     * Default constructor.
     */
    public Display() {
        frame = new JFrame();
    }

    /**
     * Introduce the game.
     *
     * @return agreement
     */
    public int introduceGame() {
        String message = "Welcome to Master Typer.\n\n" +
            "You will be presented with a java typing challenges.  Select a challenge and try to get the best score!\n\n" +
            "Master typer stores your typing results locally on this computer.\n\n" +
            "Do you agree to play?";

        return JOptionPane.showConfirmDialog(null, message, "Master Typer", JOptionPane.YES_NO_OPTION);
    }

    /**
     * Ask for the users first name.
     * 
     * @return The users first name.
     */
    public String getFirstName() {
        return JOptionPane.showInputDialog (null, "What's your first name?");
    }

    /**
     * Ask for the users last name.
     * 
     * @return The users last name.
     */
    public String getLastName() {
        return JOptionPane.showInputDialog (null, "What's your last name?");
    }

    /**
     * Warn the user that a failure to supply a name will not allow them to play
     * the game.
     */
    public void informMustAgree() {
        JOptionPane.showMessageDialog(null, "You must agree to play the game to play");
    }

    /**
     * Warn the user that a failure to supply a name will not allow them to play.
     */
    public void informMustSupplyName() {
        JOptionPane.showMessageDialog(null, "You must supply a first and last name to play");
    }

    /**
     * This method will create the main window.
     */
    public void initialize(ArrayList<Challenge> challenges, String firstName, String lastName) {    
        frame.setTitle("Master Typer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(900, 700));
        frame.setLayout (new BorderLayout());

        // Set english strings.
        String title = "Master Typer";
        String tagTop = "Become a master typer and master computer science!";
        String challengeTitle = "Challenges";
        String warning = "Master typer stores your typing results locally on this computer. An updated version of this program may delete all previous data and ask for permission to collect future typing data for comparing student typing speeds to class performance.";
        String credit = "Created by prospective graduate student Jonathan Buchner during fall term 2023.";

        // Get panels
        JPanel header = createHeaderPanel(title, tagTop);
        JPanel center = createCenterPanel(challengeTitle, challenges, firstName, lastName);
        JPanel footer = createFooterPanel(warning, credit);
        
        // Add panels to the display.
        frame.add(header, BorderLayout.NORTH);
        frame.add(center, BorderLayout.CENTER);
        frame.add(footer, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
     * This method will create the header panel to display at the top of the page.
     * 
     * @param title The title of the program.
     * @param tagline The tagline of the program.
     * @return The header panel.
     */
    private JPanel createHeaderPanel(String title, String topTag) {
        JPanel header = new JPanel(new BorderLayout());

        // Create the left image panel.
        String lab = "lab.jpeg";
        JButton left = getHeaderImage(lab);

        // Create the right image panel.
        String sign = "sign.jpeg";
        JButton right = getHeaderImage(sign);

        // Create the middle header panel.
        JPanel middle = getCenterHeaderPanel(title, topTag);
        
        // Add the panels to the header.
        header.add(left, BorderLayout.WEST);
        header.add(middle, BorderLayout.CENTER);
        header.add(right, BorderLayout.EAST);

        return header;
    }

    /**
     * This method will create a header image.
     * 
     * @param image The image to display.
     * @return The header image.
     */
    private JButton getHeaderImage(String image)
    {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(getClass().getResource(image)));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusable(false);
        button.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setSize(100, 100);
        
        return button;
    }

    /**
     * This method will create the center header panel to display at the center of the header.
     * 
     * @return The center header panel.
     */
    private JPanel getCenterHeaderPanel(String title, String tagTop)
    {
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);
        // I looked up a way to do padding and a recommendation was to use a border.
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        centerPanel.setBorder(padding);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 20, 10, 20);
        
        // Title panel.
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Verdana", Font.BOLD, 32));
        titleLabel.setForeground(new Color(40, 40, 40));

        // Tagline panel top.
        JLabel taglineTopLabel = new JLabel(tagTop);
        taglineTopLabel.setFont(new Font("Verdana", Font.ROMAN_BASELINE, 12));
        taglineTopLabel.setForeground(new Color(80, 80, 80));

        centerPanel.add(titleLabel, gbc);
        centerPanel.add(taglineTopLabel);

        return centerPanel;
    }

    /**
     * This method will create the center panel to display at the center of the page.
     * 
     * @return The center panel.
     */
    private JPanel createCenterPanel(String challengeTitle, ArrayList<Challenge> challenges, String firstName, String lastName) {
        // Create the center panel.
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(Color.WHITE);
        Border padding = BorderFactory.createEmptyBorder(20, 40, 20, 40);
        center.setBorder(padding);

        // Create the grid bag constraints.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 20, 10, 20);

        // Indicate player:
        JLabel playerLabel = new JLabel("Player: " + firstName + " " + lastName);
        playerLabel.setFont(new Font("Verdana", Font.ITALIC, 14));
        playerLabel.setForeground(new Color(80, 80, 80));

        // Create challenge label
        JLabel challengeLabel = new JLabel(challengeTitle);
        challengeLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        challengeLabel.setForeground(new Color(40, 40, 40));

        // Add labels to center panel.
        center.add(playerLabel, gbc);
        center.add(challengeLabel, gbc);
        center.add(getChallengeTitleRow(), gbc);

        // Add challenge rows.
        for (Challenge challenge : challenges) {
            center.add(getChallengeRow(challenge), gbc);
        }

        return center;
    }
    /**
     * Get challenge title row
     * 
     * @return The challenge title row.
     */
    private JPanel getChallengeTitleRow() {
        JPanel row = getRowPanel();
        
        // Add rows.
        row.add(new CustomTextAreaRowHeader("Challenge"));
        row.add(new CustomTextAreaRowHeader("Leader"));
        row.add(new CustomTextAreaRowHeader("Best score"));       
        row.add(new CustomTextAreaRowHeader("Your best"));

        return row;
    }
    
    /**
     * This method will create the challenge panel to display as row in the challenge.
     * 
     * @param highScore The high score to display.
     * @return The challenge panel.
     */
    private JPanel getChallengeRow(Challenge challenge) {
        HighScore highScore = challenge.getHighScore();
        JPanel row = getRowPanel();
       
        // Set High Scores for zero
        String theHighScore = highScore.getHighScore() == 0 ? 
            "--" :
            String.valueOf(highScore.getHighScore());
        String yourHighScore = highScore.getYourHighScore() == 0 ?
            "--" :
            String.valueOf(highScore.getYourHighScore());

        // Add rows.
        row.add(new CustomTextAreaChallenge(challenge)); // This is needed to make the challenge open.
        row.add(new CustomTextArea(highScore.getHighFirstName()));
        row.add(new CustomTextArea(theHighScore));       
        row.add(new CustomTextArea(yourHighScore));

        return row;
    }

    private JPanel getRowPanel() {
         // Create the challenge panel.
        JPanel row = new JPanel(new BorderLayout());
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));
        row.setBackground(Color.WHITE);

        return row;
    }

    /**
     * This method will create the footer panel to display at the bottom of the page.
     * 
     * @return The footer panel.
     */
    private JPanel createFooterPanel(String warning, String credit) {
        JPanel footerPanel = new JPanel(new GridBagLayout());
        footerPanel.setBackground(Color.WHITE);
        // I looked up a way to do padding and a recommendation was to use a border.
        Border padding = BorderFactory.createEmptyBorder(20, 20, 20, 20);
        footerPanel.setBorder(padding);

        // Button GBC
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridwidth = GridBagConstraints.REMAINDER;
        gbcButton.insets = new Insets(5, 20, 5, 20);

        // Text GBC
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;  // Make the footer panel take up the entire width.
        gbc.insets = new Insets(5, 20, 5, 20);

        // Footer panel.
        CustomTextArea footerLabel = new CustomTextArea(warning);
        footerLabel.setFont(new Font("Ariel", Font.ITALIC, 9));
        footerLabel.setForeground(new Color(120, 120, 120));

        // Credit panel.
        CustomTextArea creditLabel = new CustomTextArea(credit);
        creditLabel.setFont(new Font("Verdana", Font.PLAIN, 11));

        // Add exit button.
        footerPanel.add(new ExitButton(), gbcButton);
        footerPanel.add(footerLabel, gbc);
        footerPanel.add(creditLabel, gbc);

        return footerPanel;
    }   

    /**
     * CustomTextArea.java
     * 
     * This class will create a custom text area.
     */
    class CustomTextArea extends JTextArea {
        public CustomTextArea(String text) {
            super(text);
            setFont(new Font("Verdana", Font.BOLD, 12));
            setForeground(new Color(80, 80, 80));
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(false);
            setEditable(false);
            setFocusable(false);
        }
    }

    class CustomTextAreaRowHeader extends CustomTextArea {
        public CustomTextAreaRowHeader(String text) {
            super(text);
            setFont(new Font("Verdana", Font.ITALIC, 14));
            setForeground(new Color(40, 40, 40));
        }
    }

    class CustomTextAreaChallenge extends CustomTextArea {
        private Challenge challenge;

        public CustomTextAreaChallenge(Challenge challenge) {
            super(challenge.getName());
            this.challenge = challenge;

            // Add action listeners
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new OpenChallenge());
        }

        class OpenChallenge implements MouseListener {

            // Implement MouseListener methods
            public void mouseClicked(MouseEvent e) {
                if (challengeDisplay != null) {
                    String title = "Close Challenge";
                    String message = "You already have a challenge open.  Do you want to close it?";
                    
                    int response = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

                    if (response == JOptionPane.NO_OPTION) {
                        return;
                    }

                    challengeDisplay.close();
                }

                challengeDisplay = new ChallengeDisplay(challenge);
                challengeDisplay.initialize();
            }
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {
                setForeground(new Color(10, 10, 10));
                setFont(new Font("Verdana", Font.BOLD, 12));
            }
            public void mouseExited(MouseEvent e) {
                setForeground(new Color(80, 80, 80));
            }
        }
    }

    /**
     * Exit button.
     * 
     * This class will create an exit button.
     */
    class ExitButton extends JButton {
        public ExitButton() {
            super("Exit");
            setFont(new Font("Verdana", Font.PLAIN, 18));
            setForeground(new Color(80, 80, 80));
            setFocusable(false);

            // Add action listeners
            addActionListener(new ButtonListener());
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        class ButtonListener implements ActionListener
        {
            public void actionPerformed (ActionEvent e)
            {
                System.exit(0);
            }
        }
    }
}
