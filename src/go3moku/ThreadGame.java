/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

/**
 *
 * @author churchyard
 */
public class ThreadGame implements Runnable {
    
    private Player x;
    private Player o;
    
    public ThreadGame(Player x, Player o) {
        this.x = x;
        this.o = o;
    }
    
    @Override
    public void run() {
        Game.startNewGame(x,o);
    }
    
    public void kill() {
        Thread.currentThread().interrupt();
    }
    
}
