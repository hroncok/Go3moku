/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

/**
 * @brief Types of markers
 * This should basically be X and O.
 * @author churchyard
 */
public enum Mark {
    X, O;
    
    /**
     * @brief For a given mark, return the other one.
     * @param mark Given mark
     * @return Other mark. Or null when null.
     */
    public static Mark other(Mark mark) {
        if (mark == X) {
            return O;
        }
        if (mark == O) {
            return X;
        }
        return null;
    }
}
