/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author churchyard
 */
public class Pointy implements Player {
    
    private Mark me;
    private int[][][] points;
    
    public Pointy() {
        points = new int[Game.SIZE][Game.SIZE][Game.SIZE];
    }
    
    private int count(Mark m, Coord a, Coord b, Coord c) {
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
    
    private int evaluate(Coord a, Coord b, Coord c) {
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
    
    private int evaluate(Coord c) {
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
    
    private void evaluateAll() {
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
    
    private Coord withMaximumPoints() {
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
        System.out.println(c.x+","+c.y+","+c.z+"="+points[c.x][c.y][c.z]);
        return c;
    }
    
    @Override
    public Coord play() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Pointy.class.getName()).log(Level.SEVERE, null, ex);
        }
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