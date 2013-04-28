/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

/**
 *
 * @author churchyard
 */
public class ThreadGameStart implements Runnable {
    
    private Player x;
    private Player o;
    
    public ThreadGameStart(Player x, Player o) {
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
