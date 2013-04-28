package go3moku;

/**
 * A human player.
 * This class implements human Player of the Game
 * @author Miro Hrončok <miro@hroncok.cz>
 */
public class Human implements Player {

    /**
     * Asks the Game, what does human user want to do.
     * Via UI.
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
