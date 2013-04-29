package go3moku;

/**
 * Artificial intelligence using points.
 * This implementation of Player uses points, to calculate best possible move.
 * @author Miro Hrončok <miro@hroncok.cz>
 */
public class Pointy implements Player {
    
    protected Mark me;
    protected int[][][] points;
    
    public Pointy() {
        points = new int[Game.SIZE][Game.SIZE][Game.SIZE];
    }
    
    /**
     * Counts how many times is the given mark present in given coordinates.
     * @param m Given mark
     * @param a First coordinates
     * @param b Second coordinates
     * @param c Third coordinates
     * @return The amount of given marks present
     */
    protected int count(Mark m, Coord a, Coord b, Coord c) {
        int ret = 0;
        if (Game.whatsOn(a) == m) {
            ret++;
        }
        if (Game.whatsOn(b) == m) {
            ret++;
        }
        if (Game.whatsOn(c) == m) {
            ret++;
        }
        return ret;
    }
    
    /**
     * Calculates the points for combination of marks on given coordinates.
     * @param a First coordinates
     * @param b Second coordinates
     * @param c Third coordinates
     * @return The amount of points calculated
     */
    protected int evaluate(Coord a, Coord b, Coord c) {
        int mec = count(me,a,b,c);
        int hec = count(Mark.other(me),a,b,c);
        
        // This combination is no longer relevant
        if (mec > 0 && hec > 0) {
            return -10;
        }
        // Free combination
        if (mec == 0 && hec == 0) {
            return 0;
        }
        // I would need one more
        if (mec == 2) {
            return 100;
        }
        // He would need one more
        if (hec == 2) {
            return 100;
        }
        // I would need two more
        if (mec == 1) {
            return 10;
        }
        // He would need two more
        if (hec == 1) {
            return -10;
        }
        // I can stop the opponent from winning
        if (hec == 3) {
            return 2000;
        }
        // I can win the game
        if (mec == 3) {
            return 20000;
        }
        return 0; // Should never happen
    }
    
    /**
     * Calculate total ponts for given field.
     * @param c Coordinates of the given field
     * @return Totoal amoutn of points for given field
     */
    protected int evaluate(Coord c) {
        int p = 0;
        // Row
        p += evaluate(new Coord((c.x+1) % Game.SIZE,c.y,c.z),
                      new Coord((c.x+2) % Game.SIZE,c.y,c.z),
                      new Coord((c.x+3) % Game.SIZE,c.y,c.z));
        // Column
        p += evaluate(new Coord(c.x,(c.y+1) % Game.SIZE,c.z),
                      new Coord(c.x,(c.y+2) % Game.SIZE,c.z),
                      new Coord(c.x,(c.y+3) % Game.SIZE,c.z));
        // Tunel
        p += evaluate(new Coord(c.x,c.y,(c.z+1) % Game.SIZE),
                      new Coord(c.x,c.y,(c.z+2) % Game.SIZE),
                      new Coord(c.x,c.y,(c.z+3) % Game.SIZE));
        // XY diagonals
        if (c.x == c.y) {
            p += evaluate(new Coord((c.x+1) % Game.SIZE,(c.y+1) % Game.SIZE,c.z),
                          new Coord((c.x+2) % Game.SIZE,(c.y+2) % Game.SIZE,c.z),
                          new Coord((c.x+3) % Game.SIZE,(c.y+3) % Game.SIZE,c.z));
        }
        if (c.x == (Game.SIZE-1-c.y)) {
            p += evaluate(new Coord((c.x+1) % Game.SIZE,(c.y-1+Game.SIZE) % Game.SIZE,c.z),
                          new Coord((c.x+2) % Game.SIZE,(c.y-2+Game.SIZE) % Game.SIZE,c.z),
                          new Coord((c.x+3) % Game.SIZE,(c.y-3+Game.SIZE) % Game.SIZE,c.z));
        }
        // XZ diagonals
        if (c.x == c.z) {
            p += evaluate(new Coord((c.x+1) % Game.SIZE,c.y,(c.z+1) % Game.SIZE),
                          new Coord((c.x+2) % Game.SIZE,c.y,(c.z+2) % Game.SIZE),
                          new Coord((c.x+3) % Game.SIZE,c.y,(c.z+3) % Game.SIZE));
        }
        if (c.x == (Game.SIZE-1-c.z)) {
            p += evaluate(new Coord((c.x+1) % Game.SIZE,c.y,(c.z-1+Game.SIZE) % Game.SIZE),
                          new Coord((c.x+2) % Game.SIZE,c.y,(c.z-2+Game.SIZE) % Game.SIZE),
                          new Coord((c.x+3) % Game.SIZE,c.y,(c.z-3+Game.SIZE) % Game.SIZE));
        }
        // YZ diagonals
        if (c.y == c.z) {
            p += evaluate(new Coord(c.x,(c.y+1) % Game.SIZE,(c.z+1) % Game.SIZE),
                          new Coord(c.x,(c.y+2) % Game.SIZE,(c.z+2) % Game.SIZE),
                          new Coord(c.x,(c.y+3) % Game.SIZE,(c.z+3) % Game.SIZE));
        }
        if (c.y == (Game.SIZE-1-c.z)) {
            p += evaluate(new Coord(c.x,(c.y+1) % Game.SIZE,(c.z-1+Game.SIZE) % Game.SIZE),
                          new Coord(c.x,(c.y+2) % Game.SIZE,(c.z-2+Game.SIZE) % Game.SIZE),
                          new Coord(c.x,(c.y+3) % Game.SIZE,(c.z-3+Game.SIZE) % Game.SIZE));
        }
        // XYZ diagonals
        if (c.x == c.y && c.x == c.z) {
            p += evaluate(new Coord((c.x+1) % Game.SIZE,(c.y+1) % Game.SIZE,(c.z+1) % Game.SIZE),
                          new Coord((c.x+2) % Game.SIZE,(c.y+2) % Game.SIZE,(c.z+2) % Game.SIZE),
                          new Coord((c.x+3) % Game.SIZE,(c.y+3) % Game.SIZE,(c.z+3) % Game.SIZE));
        }
        if (c.x == (Game.SIZE-1-c.y) && c.x == c.z) {
            p += evaluate(new Coord((c.x+1) % Game.SIZE,(c.y-1+Game.SIZE) % Game.SIZE,(c.z+1) % Game.SIZE),
                          new Coord((c.x+2) % Game.SIZE,(c.y-2+Game.SIZE) % Game.SIZE,(c.z+2) % Game.SIZE),
                          new Coord((c.x+3) % Game.SIZE,(c.y-3+Game.SIZE) % Game.SIZE,(c.z+3) % Game.SIZE));
        }
        if (c.x == (Game.SIZE-1-c.y) && c.y == c.z) {
            p += evaluate(new Coord((c.x+1) % Game.SIZE,(c.y-1+Game.SIZE) % Game.SIZE,(c.z-1+Game.SIZE) % Game.SIZE),
                          new Coord((c.x+2) % Game.SIZE,(c.y-2+Game.SIZE) % Game.SIZE,(c.z-2+Game.SIZE) % Game.SIZE),
                          new Coord((c.x+3) % Game.SIZE,(c.y-3+Game.SIZE) % Game.SIZE,(c.z-3+Game.SIZE) % Game.SIZE));
        }
        if (c.x == (Game.SIZE-1-c.z) && c.x == c.y) {
            p += evaluate(new Coord((c.x+1) % Game.SIZE,(c.y+1) % Game.SIZE,(c.z-1+Game.SIZE) % Game.SIZE),
                          new Coord((c.x+2) % Game.SIZE,(c.y+2) % Game.SIZE,(c.z-2+Game.SIZE) % Game.SIZE),
                          new Coord((c.x+3) % Game.SIZE,(c.y+3) % Game.SIZE,(c.z-3+Game.SIZE) % Game.SIZE));
        }
        return p;
    }
    
    /**
     * Calculates points for all empty fields.
     */
    protected void evaluateAll() {
        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
                for (int k = 0; k < Game.SIZE; k++) {
                    if (Game.whatsOn(new Coord(i,j,k)) == null) {
                        points[i][j][k] = evaluate(new Coord(i,j,k));
                    }
                }
            }
        }
    }
    
    /**
     * Get the most valuable field.
     * @return Coordinates with maximum points
     */
    protected Coord withMaximumPoints() {
        Coord c = new Coord();
        if (Game.whatsOn(c) != null) { // and was not evaluated
            points[0][0][0] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < Game.SIZE; i++) {
            for (int j = 0; j < Game.SIZE; j++) {
                for (int k = 0; k < Game.SIZE; k++) {
                    if ((Game.whatsOn(new Coord(i,j,k)) == null) && (points[i][j][k] > points[c.x][c.y][c.z])){
                        c = new Coord(i,j,k);
                    }
                }
            }
        }
        return c;
    }
    
    @Override
    public Coord play() {
        // A bit of optimalisation
        if (Game.isEmpty()) {
            return new Coord(); // A corner
        }
        evaluateAll();
        return withMaximumPoints();
    }

    @Override
    public void set(Mark whoami) {
        me = whoami;
    }
    
}
