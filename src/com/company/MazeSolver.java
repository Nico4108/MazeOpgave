package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MazeSolver {

    //0 = Maze wall
    //1 = Maze available path
    //2 = Maze finsh destination
    //3 = Maze Start position

    public static void main(String[] args) throws FileNotFoundException {


        //Registrer tiden når programmet starter i nanosekunder.
        float startTime = System.nanoTime();

        //Arrayliste der indeholder layouts for mazes
        ArrayList<Maze> mazes = readMazes();

        int i = 0;
        while (i < mazes.size()){
            if (solveMaze(mazes.get(i))){
                System.out.println("YOU WOOON!!!\n");

            }else {
                System.out.println("No path available\n");
            }
            i++;
        }

        //registrer når programmet slutter i nanosekunder
        float endTime = System.nanoTime();
        //udregner tiden hvor langtid programmet kørte i sekunder
        float totalTime = (endTime -startTime)/100000000;
        System.out.println("Det tog " + totalTime+ " sekunder for at gennemføre Mazes.");
        //System.out.println("Der blev brugt " + m.path.size() + " ryk til at finde ud af mazen");

    }

    //Metode der indlæster maze layout
    private static ArrayList<Maze> readMazes() throws FileNotFoundException {

        ArrayList<Maze> mazes = new ArrayList<Maze>();

        Scanner in = new Scanner(new File("Mazes.txt"));

        while (in.hasNext()) {

            Maze m = new Maze();
            int rows = Integer.parseInt(in.nextLine());
            m.maze = new int[rows][];

            //for-loop der kører igennem Mazes text filen og indlæser rækkerne i mazen
            for (int i = 0; i < rows; i++){
                String line = in.nextLine();
                m.maze[i] = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
            }

            m.start = new Position(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine()));

            in.nextLine(); // toss extra space

            mazes.add(m);

        }
        in.close();

        return mazes;

    }

    private static boolean solveMaze(Maze m) {

        Position p = m.start;
        //push sætter værdien først i listen
        m.path.push(p);

        while(true) {
            //peek giver det første element i listen
            int y = m.path.peek().y;
            int x = m.path.peek().x;

            m.maze[y][x] = 0;

            //ned funktion
            if(isValid(y+1, x, m)) {
                if(m.maze[y+1][x] == 2) {
                    System.out.println("Moved down");
                    return true;

                } else if(m.maze[y+1][x] == 1) {
                    System.out.println("Moved down");
                    m.path.push(new Position(y+1, x));
                    continue;
                }
            }

            //venstre funktion
            if(isValid(y, x-1, m)) {
                if(m.maze[y][x-1] == 2) {
                    System.out.println("Moved left");
                    return true;

                } else if(m.maze[y][x-1] == 1) {
                    System.out.println("Moved left");
                    m.path.push(new Position(y, x-1));
                    continue;
                }
            }

            //op funktion
            if(isValid(y-1, x, m)) {
                if(m.maze[y-1][x] == 2) {
                    System.out.println("Moved up");
                    return true;

                } else if(m.maze[y-1][x] == 1) {
                    System.out.println("Moved up");
                    m.path.push(new Position(y-1, x));
                    continue;
                }
            }

            //højre funktion
            if(isValid(y, x+1, m)) {
                if(m.maze[y][x+1] == 2) {
                    System.out.println("Moved right");
                    return true;

                } else if(m.maze[y][x+1] == 1) {
                    System.out.println("Moved right");
                    m.path.push(new Position(y, x+1));
                    continue;
                }
            }

            //pop udvælger det første element i en stack
            m.path.pop();
            System.out.println("Moved back");
            if(m.path.size() <= 0) {
                return false;
            }
        }

    }

    public static boolean isValid(int y, int x, Maze m) {
        if(y < 0 ||
                y >= m.maze.length ||
                x < 0 ||
                x >= m.maze[y].length
        ) {
            return false;
        }
        return true;
    }
}
