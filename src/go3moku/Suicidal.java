package go3moku;

/**
 * Fun class that just plays deadly.
 * It is just Pointy with reverse maximum search.
 * @author Miro Hrončok <miro@hroncok.cz>
 */
public class Suicidal extends Pointy {
    // This actually gives minimum
    @Override
    protected Coord withMaximumPoints() {
        Coord c = new Coord();
        if (Game.whatsOn(c) != null) { // and was not evaluated
            points[0][0][0] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
                for (int k = 0; k < Game.SIZE; k++) {
                    if ((Game.whatsOn(new Coord(i,j,k)) == null) && (points[i][j][k] < points[c.x][c.y][c.z])){
                        c = new Coord(i,j,k);
                    }
                }
            }
        }
        return c;
    }
}
