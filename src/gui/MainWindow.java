/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author churchyard
 */
public class MainWindow extends JFrame {
    public MainWindow() {
        setVisible(true);
        setResizable(false);
        setSize(1024, 170);
        Toolbar t = new Toolbar();
        Levels l = new Levels();
        add(l,BorderLayout.NORTH);
        add(t,BorderLayout.SOUTH);
                // App ends when clicked close button
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
}
