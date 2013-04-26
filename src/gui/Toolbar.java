/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JButton;
//import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 *
 * @author churchyard
 */
public class Toolbar extends JPanel {
    JButton n = new JButton("New game");
    String[] players1 = {"X: Human","X: Computer"};
    //String[] players2 = {"O: Human","O: Computer"};
    //JComboBox p1 = new JComboBox(players1);
    //JComboBox p2 = new JComboBox(players2);
    public Toolbar() {
        //add(p1);
        //add(p2);
        add(n);
    }
}
