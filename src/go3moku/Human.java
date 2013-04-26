/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

/**
 * @brief A human player
 * This class implements human Player of the Game
 * @author churchyard
 */
public class Human implements Player {

    /**
     * Asks the Game, what does human user want to do via UI.
     */
    @Override
    public Coord play() {
        return Game.uiInput();
    }

    @Override
    public void set(Mark whoami) {
        // Don't need to store this :D
    }
    
}
