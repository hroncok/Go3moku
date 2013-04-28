/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package go3moku;

import java.util.Scanner;

/**
 * CLI for basic usage.
 * Command line interface for the Game.
 * @author churchyard
 */
public class CLI implements UI {

    @Override
    public void startNew() {
        System.out.println("A new game just started!");
    }

    @Override
    public void put(Mark mark, Coord where) {
        System.out.println(mark+": ["+where.x+","+where.y+","+where.z+"]");
    }

    @Override
    public void clear(Coord where) {
        System.out.println("Cleared ["+where.x+","+where.y+","+where.z+"]");
    }

    @Override
    public Coord input() {
        Scanner in = new Scanner(System.in);
        System.out.print("Separate coordinates with space: ");
        return new Coord(in.nextInt(),in.nextInt(),in.nextInt());
    }

    @Override
    public void infoText(String message) {
        System.out.println("Info: "+message);
    }

    @Override
    public void removeInfoText() {
        // Not possible :(
    }
    
}
