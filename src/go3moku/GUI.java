/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author churchyard
 */
public class GUI extends JFrame implements UI, ActionListener {

    private JButton newGame;
    private JPanel toolbar;
    private JPanel levelsWrapper;
    private JPanel[]  levels;
    private JButton[][][] buttons;
    
    private boolean action;
    private Coord click;
    
    public GUI() {
        setVisible(true);
        setResizable(false);
        setSize(1024, 170);

        action = false;

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
                    buttons[i][j][k].setActionCommand(i+" "+j+" "+k); // tricky :)
                    levels[i].add(buttons[i][j][k]);
                }
            }
            levelsWrapper.add(levels[i]);
        }
        
        
        toolbar = new JPanel();
        newGame = new JButton("New game");
        newGame.setActionCommand("NEWGAME");
        
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
    public void actionPerformed(ActionEvent evt) {
        System.out.println("magic");
        String cmd = evt.getActionCommand();
        if (cmd == "NEWGAME") {
            //
            return;
        }
        if (!action) {
            return;
        }
        click = new Coord(cmd);
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
        action = true;
        while (click == null) {}
        Coord ret = click;
        action = false;
        click = null;
        return ret;
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
