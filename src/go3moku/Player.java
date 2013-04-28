/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

/**
 * Player interface.
 * All players, human or artificial, implement this.
 * @author churchyard
 */
public interface Player {
    /**
     * Play the move.
     * When called this, player decides, where to put his mark
     * @return Coordinates of the placed mark
     */
    Coord play();
    /**
     * Tell the Player, what his mark is.
     * The Player should know, what his mark is, to be able to play good moves.
     * This tells him. He should save it to some class variable or do other woodoo.
     * @param whoami Mark this player is using
     */
    void set(Mark whoami);
}
