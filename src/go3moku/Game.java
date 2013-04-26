/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

/**
 * @brief The main singleton Game
 * This class controls the game. It is ment to be singleton.
 * @author churchyard
 */
public class Game {
    private static Game game;
    private Game() {}
    public static final int SIZE = 4;
    
    private Player x;
    private Player o;
    private UI ui;
    private boolean running = false;
    private Mark[][][] fields = new Mark[SIZE][SIZE][SIZE];
    
    /**
     * @brief Sets the UI and starts the application
     * This is only meant to be run once, at the begining.
     * @param ui The UI to use, cannot be chnaged during one runtime
     * @throws ExceptionInInitializerError When init was already run once before
     */
    public static void init(UI ui) throws ExceptionInInitializerError {
        if (game != null) {
            throw new ExceptionInInitializerError("Game was already initialised!");
        }
        game = new Game();
        game.ui = ui;
    }
    
    /**
     * @brief Initialise a new gameplay
     * Sets the players for the game and clears all fields
     * @param x
     * @param o
     */
    public static void startNewGame(Player x, Player o) {
        game.x = x;
        game.x.set(Mark.X);
        game.o = o;
        game.o.set(Mark.O);
        game.running = true;
    }
    
    /**
     * @brief Get input form UI
     * When human Player wants to move, this is the way
     * @return Coordinates of user move
     */
    public static Coord uiInput() {
        return game.ui.input();
    }
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                MainWindow mw = new MainWindow();
            }
        });*/
    }
}
