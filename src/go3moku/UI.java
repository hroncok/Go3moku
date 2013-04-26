/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

/**
 * @brief User interface interface
 * This is interface for user interafce :)
 * This means, GUI, TUI, CLI, whatever, need to implement this.
 * @author churchyard
 */
public interface UI {
    /**
     * @brief Start a new game in UI
     * Prepares the UI for a new game.
     * This means for example cleaning all fileds.
     */
    void startNew();
    
    /**
     * @brief Put a mark to a specific field
     * UI will put the specified mark to a certain location.
     * It is not UI's bussines, if the move is legal.
     * If there already is a mrak present, this simply overwrites it.
     * @param mark A mark to put
     * @param where Where to put it
     */
    void put(Mark mark, Coord where);
    
    /**
     * @brief Removes a mark from a specific field
     * UI will remove a mark from the given localtion.
     * If there was no mark, nothing happens.
     * @param where A place to clear
     */
    void clear(Coord where);
    
    /* NOT IMPLEMENTED
     * @brief Highlights a specified field or marker on it
     * This is used, when the game is over and we want to highlight winning row.
     * Each field of the row will be highlighted in a diffrend method call.
     * If this is called on an empty field (should never happen), do nothing.
     * Definition of highlight is left upon implementing class.
     * @param where What coordinates to highlight
     *
    void highlight(Coord where); */
    
    /**
     * @brief Read input form the user via UI
     * Sometimes, player are humans. This makes it possible to read their moves.
     * Example: User might be prompted in text UI, or GUI waits for click.
     * It is not UI's bussines, if the move is legal.
     * It is not UI's bussines, to display an reaction right away.
     * @return Coordinates of user action
     */
    Coord input();
    
    /**
     * @breaf Display an info to the human users
     * Someitmes, the Game want to tell the users something.
     * This is the way to display it.
     * Example: "Ilegal move" or "Player X is on the move"
     * New message owervrites old one (if technically possible).
     * @param message Text to display.
     */
    void infoText(String message);
    
    /**
     * @brief Clear previously displayed info
     * If there is a text displayed in the information area, this removes it.
     * If there is no text, do nothing.
     * This might not be possible in CLI interface, so only implement this, if it has a reason.
     */
    void removeInfoText();
     
}
