/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author churchyard
 */
public class Level extends JPanel {
    
    public Level() {
        setLayout(new GridLayout(4, 4));
        for (int i = 0; i < 16; i++) {
            add(new JButton(" "));
        }
    }
}
