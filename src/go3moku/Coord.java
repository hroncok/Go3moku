/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

/**
 * @brief Coordinates in 3D model
 * @author churchyard
 */
public class Coord {
    public int x; ///< X coordinate
    public int y; ///< Y coordinate
    public int z; ///< Z coordinate
    
    /// Build coords from 3 ints
    public Coord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /// Default coord will have [0,0,0]
    public Coord() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        Coord c = (Coord)other;
        return (x == c.x && y == c.y && z == c.z);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.x;
        hash = 47 * hash + this.y;
        hash = 47 * hash + this.z;
        return hash;
    }
}
