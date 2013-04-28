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
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * GUI for the Game.
 * Graphical interface for the Game
 * @author churchyard
 */
public class GUI extends JFrame implements UI, ActionListener {

    private ThreadGame tgs;
    
    private JButton newGame;
    private JPanel toolbar;
    private JPanel levelsWrapper;
    private JPanel[]  levels;
    private JButton[][][] buttons;
    private JComboBox playerx;
    private JComboBox playero;
    
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
                    buttons[i][j][k].addActionListener(this);
                    levels[i].add(buttons[i][j][k]);
                }
            }
            levelsWrapper.add(levels[i]);
        }
        
        
        toolbar = new JPanel();
        newGame = new JButton("New game");
        newGame.setActionCommand("NEWGAME");
        newGame.addActionListener(this);
        
        playerx = new JComboBox(Game.getAvailablePlayers());
        playero = new JComboBox(Game.getAvailablePlayers());
        
        toolbar.add(playerx);
        toolbar.add(playero);
        toolbar.add(newGame);
        
        this.add(toolbar,BorderLayout.SOUTH);
        this.add(levelsWrapper,BorderLayout.NORTH);
        
        // App ends when clicked close button
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                if (Game.isPlaying() && !areYouSure()) {
                        return;
                }
                System.exit(0);
            }
        });
    }
    
    private boolean areYouSure() {
        // TODO: Display a yes/no dialog
        return true;
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        if (cmd == "NEWGAME") {
            try {
                if (tgs != null) {
                    if (Game.isPlaying() && !areYouSure()) {
                        return;
                    }
                    tgs.kill();
                }
                tgs = new ThreadGame((Player) Class.forName("go3moku."+playerx.getSelectedItem()).getConstructors()[0].newInstance(),
                                     (Player) Class.forName("go3moku."+playero.getSelectedItem()).getConstructors()[0].newInstance());
                (new Thread(tgs)).start();
                return;
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        while (click == null) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
