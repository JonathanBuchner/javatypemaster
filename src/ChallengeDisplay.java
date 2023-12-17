/**
 * ChallengeDisplay.java
 * 
 * This class will display the challenge to the user.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.ArrayList;

public class ChallengeDisplay {
    private Display display;
    private Challenge challenge;
    private JFrame frame;
    private ChallengeTextDisplay challengeTextDisplay;
    private JTextField secondsPanel;
    private JTextField bestTimesPanel;
    private JTextField yourBestTimesPanel;
    private Inputter inputter;
    
    /**
     * Default constructor.
     */
    public ChallengeDisplay() {
        this.frame = new JFrame();
    }

    /**
     * Constructor.
     * 
     * @param challenge The challenge.
     */
    public ChallengeDisplay(Display display, Challenge challenge) {
        this();
        this.challenge = challenge;
        this.display = display;
        try {
            this.challengeTextDisplay = new ChallengeTextDisplay(challenge.getText());
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e, "Error loading challenge text.");
        }
    }

    /**
     * Display the typing challenge.
     */
    public void initialize() {
        try {
            // Set the frame
            frame.setTitle("TypeMaster Challenge: " + challenge.getName());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(800, 600));
            frame.setLayout(new BorderLayout());

            // Set the layout
            frame.add(createHeaderPanel(), BorderLayout.NORTH);
            frame.add(createChallengePanel(challengeTextDisplay), BorderLayout.CENTER);
            frame.add(createFooterPanel(), BorderLayout.SOUTH);

            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e, "Error initializing challenge.");
        }
    }

    /**
     * Close the frame.
     */
    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }

    /**
     * Reset the challenge.
     */
    public void reset() {
        try {
            inputter.reset();
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e, "Error resetting challenge.");
        }
    }

    /**
     * Update score upon completion of a challenge.
     * 
     * @param score
     */
    public void update(int score) {
        try {
            // Write the score to file.
            writeScoreToFile(score);
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e, "Error writing challenge scores to file.");
        }

        try {
            updateScores(score);
        } catch (Exception e) {
            ExceptionHandler.handleFatelExceptions(e, "Error updating challenge scores.");
        }

        // Update the display.
        display.update();
    }

    /**
     * Update the scores.
     */
    private void updateScores(int score) {
        HighScore hs = challenge.getHighScore();
        String preText = "Let's go again! ";

        // Get score.
        int highScore = hs.getHighScore();
        int yourHighScore = hs.getYourHighScore();

        // Format the score.
        String displayScore = String.format("%.3f", score / 1000.0);

        // Set your high score.
        if (score < yourHighScore || yourHighScore == 0) {
            hs.setYourHighScore(score);
            preText = "You have set a new personal high score! ";

            // Update the displayed scores.
            String yourBestScore = ChallengeDisplay.formatTimeScore("Your best: ", hs.getYourHighScore());
            getYourBestTimesPanel().setText(yourBestScore);
        }

        // Set the high score.
        if (score < highScore || highScore == 0) {
            hs.setHighScore(score);
            String name = hs.getYourFirstName();
            hs.setHighFirstName(name);
            preText = "You have set the new high score! ";

            // Update the displayed scores.
            String bestScore = ChallengeDisplay.formatTimeScore("Best: ", hs.getHighScore());
            getBestTimesPanel().setText(bestScore);
        }
        
        // Alert the user to their score and performance.
        JOptionPane.showMessageDialog(null, preText + "Your time is " + displayScore + " seconds.");
    }

    /**
     * Write score to file.
     */
    public void writeScoreToFile(int score) throws Exception {
        ChallengeResult challengeResult = new ChallengeResult();
        challengeResult.setId();
        challengeResult.setChallenge(challenge);
        challengeResult.setDateCompleted(LocalDateTime.now());
        challengeResult.setFirstName(challenge.getHighScore().getYourFirstName());
        challengeResult.setLastName(challenge.getHighScore().getYourLastName());
        challengeResult.setSecondsToComplete(score);

        // Write the challenge result to file.
        String path = GameManager.getChallengeResultsPath();
        FileHelper.writeLineToFile(path, challengeResult.toString());
    }

    /**
     * Create the header panel.
     * 
     * @return The header panel.
     */
    private JPanel createHeaderPanel() {
        JPanel header = new JPanel(new GridBagLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;  
        gbc.insets = new Insets(10, 20, 10, 20);

        // Add parts to the panel
        header.add(getTitlePanel(), gbc);
        header.add(getDescriptionPanel(), gbc);
        header.add(getInstructionPanel(), gbc);

        return header;
    }

    /**
     * Get the title text.
     * 
     * @return The title text.
     */
    private TextAreaChallenge getTitlePanel() {
        TextAreaChallenge title = new TextAreaChallenge(challenge.getName());
        title.setFont(new Font("Verdana", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        return title;
    }

    /**
     * Get the challenge description.
     * 
     * @return The challenge description.
     */
    private TextAreaChallenge getDescriptionPanel() {
        TextAreaChallenge description = new TextAreaChallenge(challenge.getDescription());
        description.setFont(new Font("Verdana", Font.ITALIC, 14));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        return description;
    }

    /**
     * Get the challenge instructions.
     * 
     * @return The challenge instructions.
     */
    private TextAreaChallenge getInstructionPanel() {
        String instructionText = "In the text input below enter the words of the challenge as fast as you can. The timer will start when you begin typing.";

        TextAreaChallenge instructions = new TextAreaChallenge(instructionText);
        instructions.setFont(new Font("Verdana", Font.PLAIN, 12));
        instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        return instructions;
    }

    /**
     * Create the challenge panel.
     * 
     * @return The challenge panel.
     */
    private JPanel createChallengePanel(ChallengeTextDisplay challengeTextDisplay) {
        JPanel challengePanel = new JPanel();
        challengePanel.setLayout(new BoxLayout(challengePanel, BoxLayout.Y_AXIS));
        challengePanel.setBorder(BorderFactory.createEmptyBorder(10, 80, 20, 80));
        challengePanel.setBackground(Color.WHITE);

        for (ArrayList<Word> row : challengeTextDisplay.getWordRows()) {
            challengePanel.add(createChallengeRowPanel(row));
        }

        return challengePanel;
    }

    private JPanel createChallengeRowPanel(ArrayList<Word> row) {
        JPanel challengeRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        challengeRowPanel.setBackground(Color.WHITE);

        for (Word word : row) {
            challengeRowPanel.add(word);
        }

        return challengeRowPanel;
    }

    /**
     * Create the footer panel.
     * 
     * @return The footer panel.
     */
    private JPanel createFooterPanel() {
        JPanel footer = new JPanel();
        footer.setLayout(new BoxLayout(footer, BoxLayout.Y_AXIS));
        footer.setBackground(Color.WHITE);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 200, 20, 200));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add parts to the panel
        // Seconds panel and inputter panel are instantiated in the constructor.
        this.secondsPanel = createSecondsPanel();
        this.inputter = createInputter(this);
        this.bestTimesPanel = createBestTimePanel();
        this.yourBestTimesPanel = createYourBestTimePanel();

        footer.add(this.secondsPanel, gbc);  
        footer.add(bestTimesPanel, gbc);
        footer.add(yourBestTimesPanel, gbc);             
        footer.add(this.inputter, gbc);
        footer.add(createButtonPanel(), gbc);

        return footer;
    }

    /*
    * Create the button panel.
    */
    private JPanel createButtonPanel() {
        JPanel footer = new JPanel(new FlowLayout());
        footer.setBackground(Color.WHITE);
        footer.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 10));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);

        // Add parts to the panel
        footer.add(new ResetButton(), gbc);
        footer.add(new CloseButton(), gbc);

        return footer;
    }

    /**
     * Create seconds panel
     * 
     * @return The seconds panel.
     */
    private JTextField createSecondsPanel() {
        TextAreaScore secondsPanel = new TextAreaScore("Lets go!");
        secondsPanel.setFont(new Font("Verdana", Font.BOLD, 16));
        secondsPanel.setForeground(new Color(0, 163, 108));  // Green
       
        return secondsPanel;
    }

    /**
     * Get best time panel.
     * 
     * @return The best time panel.
     */
    private JTextField createBestTimePanel() {
        int best = getChallenge().getHighScore().getHighScore();
       
        return new TextAreaScore(formatTimeScore("Best: ", best));
    }

    /**
     * Get your best time panel.
     * 
     * @return The your best time panel.
     */
    private JTextField createYourBestTimePanel() {
        int yourBest = getChallenge().getHighScore().getYourHighScore();
       
        return new TextAreaScore(formatTimeScore("Your best: ", yourBest));
    }

    /**
     * Format time score to string.
     * 
     * @param timeScore The time score.
     */
    public static String formatTimeScore(String prefix, int timeScore) {

        if (timeScore == 0) {
            return  prefix + "N/A";
        }
        
        return prefix + String.format("%.1f", timeScore / 1000.0) + " seconds";
    }

    /**
     * Create the inputter.
     * 
     * @return The inputter.
     */
    private Inputter createInputter(ChallengeDisplay challengeDisplay) {
        return new Inputter(challengeDisplay);
    }

    // Getters

    /**
     * Get the display.
     * 
     * @return The display.
     */
    public Display getDisplay() {
        return this.display;
    }

    /**
     * Get the challenge.
     * 
     * @return The challenge.
     */
    public Challenge getChallenge() {
        return this.challenge;
    }

    /**
     * Get the frame.
     * 
     * @return The frame.
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /*
     * Get the challenge text display.
     */
    public ChallengeTextDisplay getChallengeTextDisplay() {
        return this.challengeTextDisplay;
    }

    /**
     * Get the seconds panel.
     * 
     * @return The seconds panel.
     */
    public JTextField getSecondsPanel() {
        return this.secondsPanel;
    }

    /*
     * Get the best times panel.
     */
    public JTextField getBestTimesPanel() {
        return this.bestTimesPanel;
    }

    /*
     * Get the your best times panel.
     */
    public JTextField getYourBestTimesPanel() {
        return this.yourBestTimesPanel;
    }

    /**
     * Get the inputter.
     * 
     * @return The inputter.
     */
    public Inputter getInputter() {
        return this.inputter;
    }

    // Setters

    /**
     * Set the display.
     * 
     * @param display The display.
     */
    public void setDisplay(Display display) {
        this.display = display;
    }

    /**
     * Set the challenge.
     * 
     * @param challenge The challenge.
     */
    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    /**
     * Set the frame.
     * 
     * @param frame The frame.
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /*
     * Set the challenge text display.
     */
    public void setChallengeTextDisplay(ChallengeTextDisplay challengeTextDisplay) {
        this.challengeTextDisplay = challengeTextDisplay;
    }

    /*
     * Set the seconds panel.
     */
    public void setSecondsPanel(JTextField secondsPanel) {
        this.secondsPanel = secondsPanel;
    }

    /*
     * Set the best times panel.
     */
    public void setBestTimesPanel(JTextField bestTimesPanel) {
        this.bestTimesPanel = bestTimesPanel;
    }

    /*
     * Set the your best times panel.
     */
    public void setYourBestTimesPanel(JTextField yourBestTimesPanel) {
        this.yourBestTimesPanel = yourBestTimesPanel;
    }

    /**
     * Set the inputter.
     * 
     * @param inputter The inputter.
     */
    public void setInputter(Inputter inputter) {
        this.inputter = inputter;
    }

    // Private classes
    
    /**
     * Button 
     */
    abstract class Button extends JButton {
        public Button(String text) {
            super(text);
            setFont(new Font("Verdana", Font.PLAIN, 14));
            setForeground(new Color(80, 80, 80));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    /**
     * Close button.
     */
    class CloseButton extends Button {
        public CloseButton() {
            super("Close");
            addActionListener(new Listener());
        }

        class Listener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                close();
            }
        }
    }

    /**
     * Reset button.
     */
    class ResetButton extends Button {
        public ResetButton() {
            super("Reset");
            addActionListener(new Listener());
        }

        class Listener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        }
    }

    /**
     * This class will create a text field for challenges.
     */
    class TextFieldChallenge extends JTextField {
        public TextFieldChallenge(String text) {
            super(text);
            setFont(new Font("Verdana", Font.PLAIN, 14));
            setForeground(new Color(80, 80, 80));
            setFocusable(true);
            setCursor(new Cursor(Cursor.TEXT_CURSOR));
        }
    }

    /**
     * This class will create a text area.
     */
    class TextAreaChallenge extends JTextArea {
        public TextAreaChallenge(String text) {
            super(text);
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(false);
            setEditable(false);
            setFocusable(false);
        }
    }

    /**
     * This class will create a text area for list scores.
     */
    class TextAreaScore extends JTextField {
        public TextAreaScore(String text) {
            super(text);
            setFont(new Font("Verdana", Font.PLAIN, 12));
            setForeground(new Color(120, 120, 120));  // Green
            setHorizontalAlignment(JTextField.CENTER);
            setOpaque(false);
            setEditable(false);
            setFocusable(false);
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        }
    }
}
