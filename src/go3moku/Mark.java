package go3moku;

/**
 * Types of markers.
 * This should basically be X and O.
 * @author Miro Hrončok <miro@hroncok.cz>
 */
public enum Mark {
    X, O;
    
    /**
     * For a given mark, return the other one.
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
