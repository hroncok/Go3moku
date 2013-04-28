/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

/**
 * @brief Helper class to run the Game cycle in a thread
 * @author churchyard
 */
public class ThreadGame implements Runnable {
    
    private Player x;
    private Player o;
    
    /**
     * Prepare the thread.
     * Give information about Players.
     * @param x First Player (X)
     * @param o Second Player (O)
     */
    public ThreadGame(Player x, Player o) {
        this.x = x;
        this.o = o;
    }
    
    /**
     * Start the game.
     * Uses previously given information about Players
     */
    @Override
    public void run() {
        Game.startNewGame(x,o);
    }
    
    /**
     * Kills the thread.
     * Game singleton class is still runing, just this cycle has ended.
     */
    public void kill() {
        Thread.currentThread().interrupt();
    }
    
}
