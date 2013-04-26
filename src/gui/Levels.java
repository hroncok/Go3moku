/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author churchyard
 */
public class Levels extends JPanel {
    
    public Levels() {
        setLayout(new GridLayout(1, 4, 4, 4));
        for (int i = 0; i < 4; i++) {
            add(new Level());
        }
    }
}
