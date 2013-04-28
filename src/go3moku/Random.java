package go3moku;

/**
 * Random player.
 * Very easy implementation of Player. It just plays random.
 * @author Miro Hrončok <miro@hroncok.cz>
 */
public class Random implements Player {
    
    /**
     * Just pick a random coordinates and hit the Game.
     */
    @Override
    public Coord play() {
        return new Coord((int)(Math.random() * Game.SIZE),(int)(Math.random() * Game.SIZE),(int)(Math.random() * Game.SIZE));
    }

    @Override
    public void set(Mark whoami) {
        // Don't need to store this :D
    }
    
}
