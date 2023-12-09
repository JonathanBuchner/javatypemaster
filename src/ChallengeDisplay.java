/**
 * ChallengeDisplay.java
 * 
 * This class will display the challenge to the user and allow them to type it.
 * 
 * @author Jonathan Buchner Nov 2023.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChallengeDisplay {
    private Challenge challenge;
    private JFrame frame;
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
    public ChallengeDisplay(Challenge challenge) {
        this();
        this.challenge = challenge;
    }

    /**
     * Display the challenge.
     */
    public void initialize() {
        // Set the frame
        frame.setTitle("TypeMaster Challenge: " + challenge.getName());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setLayout(new BorderLayout());

        // Set the layout
        frame.add(createHeaderPanel(), BorderLayout.NORTH);
        frame.add(new JTextArea(), BorderLayout.CENTER);
        frame.add(createFooterPanel(), BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    /**
     * Close the frame.
     */
    public void close() {
        frame.setVisible(false);
        frame.dispose();
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

        return header;
    }

    /**
     * Create the footer panel.
     * 
     * @return The footer panel.
     */
    private JPanel createFooterPanel() {
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
     * Close button.
     * 
     * This class will create an exit button.
     */
    class Button extends JButton {
        public Button(String text) {
            super(text);
            setFont(new Font("Verdana", Font.PLAIN, 14));
            setForeground(new Color(80, 80, 80));
            setFocusable(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

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

    class ResetButton extends Button {
        public ResetButton() {
            super("Reset");
        }

        class Listener implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                // Reset the challenge
            }
        }
    }



    /**
     * Get the title text.
     * 
     * @return The title text.
     */
    private TextAreaChallenge getTitlePanel() {
        TextAreaChallenge title = new TextAreaChallenge(challenge.getName());
        title.setFont(new Font("Serif", Font.BOLD, 36));
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
        description.setFont(new Font("Serif", Font.ITALIC, 14));
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        return description;
    }

    // Getters

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

    // Setters

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
}

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
