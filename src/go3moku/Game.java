/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

import javax.swing.SwingUtilities;

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
    private Mark move = null;
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
     * @brief Initialise a new gameplay and run the main cycle
     * Sets the players for the game and clears all fields
     * @param x
     * @param o
     */
    public static void startNewGame(Player x, Player o) {
        game.x = x;
        game.x.set(Mark.X);
        game.o = o;
        game.o.set(Mark.O);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    game.fields[i][j][k] = null;
                }
            }
        }
        game.move = Mark.X;
        game.ui.startNew();
        while (game.move != null) {
            Player pl = currentPlayer();
            // While the move is not legal, ask for other one
            while (!play(pl.play())) {}
            // Other player goes
            game.move = Mark.other(game.move);
        }
    }

    private static boolean coordOutOfRange(Coord c) {
        return ((c.x >= SIZE) || (c.y >= SIZE) || (c.z >= SIZE) || (c.x < 0) || (c.y < 0) || (c.z < 0));
    }
    
    private static boolean play(Coord c) {
        // Ilegal move
        if (coordOutOfRange(c) || game.fields[c.x][c.y][c.z] != null) {
            return false;
        }
        // Put it to data
        game.fields[c.x][c.y][c.z] = game.move;
        // Put it to UI
        game.ui.put(game.move, c);
        if (checkWin(c)) {
            game.ui.infoText("Player "+game.move+" wins!");
            game.move = null;
        }
        if (checkFull()) {
            game.ui.infoText("It's a draw!");
            game.move = null;
        }
        return true;
    }
    
    private static boolean checkFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (game.fields[i][j][k] == null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private static boolean check4Win(Mark a, Mark b, Mark c, Mark d) {
        // They cannot be all nulls, as one of them was just played
        return (a == b && a == c && a == d);
    }
    
    // This will be pain, brace yourself
    private static boolean checkWin(Coord c) {
        // Row
        if (check4Win(game.fields[c.x][c.y][c.z],
                      game.fields[(c.x+1) % SIZE][c.y][c.z],
                      game.fields[(c.x+2) % SIZE][c.y][c.z],
                      game.fields[(c.x+3) % SIZE][c.y][c.z])) {
            return true;
        }
        // Column
        if (check4Win(game.fields[c.x][c.y][c.z],
                      game.fields[c.x][(c.y+1) % SIZE][c.z],
                      game.fields[c.x][(c.y+2) % SIZE][c.z],
                      game.fields[c.x][(c.y+3) % SIZE][c.z])) {
            return true;
        }
        // Tunel
        if (check4Win(game.fields[c.x][c.y][c.z],
                      game.fields[c.x][c.y][(c.z+1) % SIZE],
                      game.fields[c.x][c.y][(c.z+2) % SIZE],
                      game.fields[c.x][c.y][(c.z+3) % SIZE])) {
            return true;
        }
        // XY diagonals
        if (c.x == c.y) {
            if (check4Win(game.fields[c.x][c.y][c.z],
                          game.fields[(c.x+1) % SIZE][(c.y+1) % SIZE][c.z],
                          game.fields[(c.x+2) % SIZE][(c.y+2) % SIZE][c.z],
                          game.fields[(c.x+3) % SIZE][(c.y+3) % SIZE][c.z])) {
                return true;
            }
        }
        if (c.x == (SIZE-1-c.y)) {
            if (check4Win(game.fields[c.x][c.y][c.z],
                          game.fields[(c.x+1) % SIZE][(c.y-1+SIZE) % SIZE][c.z],
                          game.fields[(c.x+2) % SIZE][(c.y-2+SIZE) % SIZE][c.z],
                          game.fields[(c.x+3) % SIZE][(c.y-3+SIZE) % SIZE][c.z])) {
                return true;
            }
        }
        // XZ diagonals
        if (c.x == c.z) {
            if (check4Win(game.fields[c.x][c.y][c.z],
                          game.fields[(c.x+1) % SIZE][c.y][(c.z+1) % SIZE],
                          game.fields[(c.x+2) % SIZE][c.y][(c.z+2) % SIZE],
                          game.fields[(c.x+3) % SIZE][c.y][(c.z+3) % SIZE])) {
                return true;
            }
        }
        if (c.x == (SIZE-1-c.z)) {
            if (check4Win(game.fields[c.x][c.y][c.z],
                          game.fields[(c.x+1) % SIZE][c.y][(c.z-1+SIZE) % SIZE],
                          game.fields[(c.x+2) % SIZE][c.y][(c.z-2+SIZE) % SIZE],
                          game.fields[(c.x+3) % SIZE][c.y][(c.z-3+SIZE) % SIZE])) {
                return true;
            }
        }
        // YZ diagonals
        if (c.y == c.z) {
            if (check4Win(game.fields[c.x][c.y][c.z],
                          game.fields[c.x][(c.y+1) % SIZE][(c.z+1) % SIZE],
                          game.fields[c.x][(c.y+2) % SIZE][(c.z+2) % SIZE],
                          game.fields[c.x][(c.y+3) % SIZE][(c.z+3) % SIZE])) {
                return true;
            }
        }
        if (c.y == (SIZE-1-c.z)) {
            if (check4Win(game.fields[c.x][c.y][c.z],
                          game.fields[c.x][(c.y+1) % SIZE][(c.z-1+SIZE) % SIZE],
                          game.fields[c.x][(c.y+2) % SIZE][(c.z-2+SIZE) % SIZE],
                          game.fields[c.x][(c.y+3) % SIZE][(c.z-3+SIZE) % SIZE])) {
                return true;
            }
        }
        // XYZ diagonals
        if (c.x == c.y && c.x == c.z) {
            if (check4Win(game.fields[c.x][c.y][c.z],
                          game.fields[(c.x+1) % SIZE][(c.y+1) % SIZE][(c.z+1) % SIZE],
                          game.fields[(c.x+2) % SIZE][(c.y+2) % SIZE][(c.z+2) % SIZE],
                          game.fields[(c.x+3) % SIZE][(c.y+3) % SIZE][(c.z+3) % SIZE])) {
                return true;
            }
        }
        if (c.x == (SIZE-1-c.y) && c.x == c.z) {
            if (check4Win(game.fields[c.x][c.y][c.z],
                          game.fields[(c.x+1) % SIZE][(c.y-1+SIZE) % SIZE][(c.z+1) % SIZE],
                          game.fields[(c.x+2) % SIZE][(c.y-2+SIZE) % SIZE][(c.z+2) % SIZE],
                          game.fields[(c.x+3) % SIZE][(c.y-3+SIZE) % SIZE][(c.z+3) % SIZE])) {
                return true;
            }
        }
        if (c.x == (SIZE-1-c.y) && c.y == c.z) {
            if (check4Win(game.fields[c.x][c.y][c.z],
                          game.fields[(c.x+1) % SIZE][(c.y-1+SIZE) % SIZE][(c.z-1+SIZE) % SIZE],
                          game.fields[(c.x+2) % SIZE][(c.y-2+SIZE) % SIZE][(c.z-2+SIZE) % SIZE],
                          game.fields[(c.x+3) % SIZE][(c.y-3+SIZE) % SIZE][(c.z-3+SIZE) % SIZE])) {
                return true;
            }
        }
        if (c.x == (SIZE-1-c.z) && c.x == c.y) {
            if (check4Win(game.fields[c.x][c.y][c.z],
                          game.fields[(c.x+1) % SIZE][(c.y+1) % SIZE][(c.z-1+SIZE) % SIZE],
                          game.fields[(c.x+2) % SIZE][(c.y+2) % SIZE][(c.z-2+SIZE) % SIZE],
                          game.fields[(c.x+3) % SIZE][(c.y+3) % SIZE][(c.z-3+SIZE) % SIZE])) {
                return true;
            }
        }
        return false;
    }
    
    private static Player currentPlayer() {
        if (game.move == Mark.X) {
            return game.x;
        }
        if (game.move == Mark.O) {
            return game.o;
        }
        return null;
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
                Game.init(new GUI());
                Game.startNewGame(new Human(), new Random());
            }
        });*/
    Game.init(new GUI());
    Game.startNewGame(new Random(), new Random());
    }
}
