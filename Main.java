package tictactoe;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner in = new Scanner(System.in);
        char[][] array = new char[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++) array[i][j] = ' ';
        }
        printGrid(array);
        boolean xMove = true;
        while(boardCheck(array)==0){
            nextMove(array, xMove);
            printGrid(array);
            xMove = !xMove;
            switch(boardCheck(array)){
                case -1:
                    System.out.println("Impossible");
                    break;
                case 1:
                    System.out.println("X wins");
                    break;
                case 2:
                    System.out.println("O wins");
                    break;
                case 3:
                    System.out.println("Draw");
                    break;
            }
        }

    }
    static void printGrid(char[][] array){
        System.out.println("---------");
        for(int i = 0; i < 3; i++){
            System.out.print("| ");
            for(int j = 1; j <= 3; j++){
                System.out.print(array[i][j-1] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
    static char[][] nextMove(char[][] array,boolean turn){
        boolean goodCoord = false;
        String input1, input2;
        int xCoord = 0;
        int yCoord = 0;
        Scanner in = new Scanner(System.in);
        do{
            System.out.print("Enter the coordinates: ");
            input1 = in.next();
            input2 = in.next();
            try {
                xCoord = Integer.parseInt(input1);
                yCoord = Integer.parseInt(input2);
                if (xCoord > 0 && xCoord <= 3 && yCoord > 0 && yCoord <= 3) {
                    if (array[3 - yCoord][xCoord - 1] != 'X' && array[3 - yCoord][xCoord - 1] != 'O') {
                        goodCoord = true;
                        if(turn) array[3 - yCoord][xCoord - 1] = 'X';
                        else array[3 - yCoord][xCoord - 1] = 'O';
                    } else System.out.println("This cell is occupied! Choose another one!");
                } else System.out.println("Coordinates should be from 1 to 3!");
            }
            catch(NumberFormatException e)
            {
                System.out.println("You should enter numbers!");
            }
        }while(!goodCoord);
        return array;
    }
    static int boardCheck(char[][] array){
        boolean xWin = false;
        boolean oWin = false;
        int diag1 = 0;
        int diag2 = 0;
        int vert1 = 0;
        int vert2 = 0;
        int vert3 = 0;
        int horizontal = 0;
        int xCount = 0;
        int oCount = 0;
        boolean unfinished = false;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(array[i][j]!='X'&&array[i][j]!='O') unfinished = true;
                if(array[i][j] == 'X') {
                    xCount++;
                    horizontal++;
                    if(j == 0){
                        vert1++;
                        if(i == 0) diag1++;
                        if(i == 2) diag2++;
                    }
                    if(j == 1){
                        vert2++;
                        if(i == 1){
                            diag1++;
                            diag2++;
                        }
                    }
                    if(j == 2){
                        vert3++;
                        if(i == 2) diag1++;
                        if(i == 0) diag2++;
                    }
                }
                if(array[i][j] == 'O') {
                    oCount++;
                    horizontal--;
                    if(j == 0){
                        vert1--;
                        if(i == 0) diag1--;
                        if(i == 2) diag2--;
                    }
                    if(j == 1){
                        vert2--;
                        if(i == 1){
                            diag1--;
                            diag2--;
                        }
                    }
                    if(j == 2){
                        vert3--;
                        if(i == 2) diag1--;
                        if(i == 0) diag2--;
                    }
                }
            }
            if(horizontal == 3||diag1 == 3|| diag2 == 3 || vert1 == 3 || vert2 == 3|| vert3 == 3) xWin = true;
            if(horizontal == -3||diag1 == -3|| diag2 == -3 || vert1 == -3 || vert2 == -3|| vert3 == -3) oWin = true;
            horizontal = 0;

        }
        if(Math.abs(xCount-oCount)>=2||(xWin&&oWin)) return -1;//impossible board state
        else if(xWin) return 1;//X wins
        else if(oWin) return 2;//O wins
        else if(unfinished) return 0;//unfinished
        else return 3;//draw
    }
}
