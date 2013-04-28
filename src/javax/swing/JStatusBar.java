/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javax.swing;

import java.awt.Font;
import javax.swing.border.BevelBorder;

/**
 * Status bar for Swing.
 * It's a JPanel with a JLable, looking like a status bar.
 * @author churchyard
 */
public class JStatusBar extends JPanel {
    private JLabel statusLabel;
    
    /**
     * Creates ampty status bar.
     */
    public JStatusBar() {
        this.setBorder(new BevelBorder(BevelBorder.LOWERED));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusLabel.setFont(new Font("sans",Font.PLAIN,statusLabel.getFont().getSize()));
        this.add(statusLabel);
    }
    
    /**
     * Creates status bar with a given message.
     * @param message Text to display on the status bar.
     */
    public JStatusBar(String message) {
        this();
        statusLabel.setText(message);
    }
    
    /**
     * Sets the message on the status bar.
     * @param message Text to display on the status bar.
     */
    public void setMessage(String message) {
        statusLabel.setText(message);
    }
    
    /**
     * Clears the status bar.
     * Removes the text on the status bar.
     */
    public void clearMessage() {
        statusLabel.setText("");
    }
}
