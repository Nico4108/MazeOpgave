package com.company;

import java.util.LinkedList;


public class Maze {
    public int [][] maze;
    /*
     * LinkedList bruger internt en dobbelthægtet liste til at gemme sine data.
     * I en dobbelthægtet liste gemmes data led i en kæde: hver indgang har en reference til ("er hægtet" med) forrige og næste indgang.
     * Skal man søge et bestemt index frem i en sådan liste, går det langsomt: Man må starte fra en ende og så løbe gennem hver indgang ("led i kæden"), indtil den ønskede indgang nås.
     * Det er til gengæld meget hurtigt at føje nye elementer til listen (da de andre elementer ikke behøves at blive rykket).
     */
    public LinkedList<Position> path = new LinkedList<Position>();
    public Position start;

}
