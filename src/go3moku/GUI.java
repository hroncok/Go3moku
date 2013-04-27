/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author churchyard
 */
public class GUI extends JFrame implements UI {

    private JButton newGame;
    private JPanel toolbar;
    private JPanel levelsWrapper;
    private JPanel[]  levels;
    private JButton[][][] buttons;
    
    
    public GUI() {
        setVisible(true);
        setResizable(false);
        setSize(1024, 170);

        levelsWrapper = new JPanel();
        levelsWrapper.setLayout(new GridLayout(1, Game.SIZE, 4, 4));        
        levels = new JPanel[Game.SIZE];
        buttons = new JButton[Game.SIZE][Game.SIZE][Game.SIZE];
        for (int i = 0; i < Game.SIZE; i++) {
            levels[i] = new JPanel();
            levels[i].setLayout(new GridLayout(Game.SIZE, Game.SIZE));
            for (int j = 0; j < Game.SIZE; j++) {
                for (int k = 0; k < Game.SIZE; k++) {
                    buttons[i][j][k] = new JButton(" ");
                    levels[i].add(buttons[i][j][k]);
                }
            }
            levelsWrapper.add(levels[i]);
        }
        
        
        toolbar = new JPanel();
        newGame = new JButton("New game");
        
        toolbar.add(newGame);
        this.add(toolbar,BorderLayout.SOUTH);
        this.add(levelsWrapper,BorderLayout.NORTH);
        
        
        // App ends when clicked close button
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }
    
    @Override
    public void startNew() {
        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
                for (int k = 0; k < Game.SIZE; k++) {
                    clear(new Coord(i,j,k));
                }
            }
        }
    }

    @Override
    public void put(Mark mark, Coord where) {
        buttons[where.x][where.y][where.z].setText(""+mark);
    }

    @Override
    public void clear(Coord where) {
        buttons[where.x][where.y][where.z].setText(" ");
    }

    @Override
    public Coord input() {
        // Do nothing yet
        return new Coord();
    }

    @Override
    public void infoText(String message) {
        // Do nothing yet
    }

    @Override
    public void removeInfoText() {
        // Do nothing yet
    }
    
}
