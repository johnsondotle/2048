import java.util.*;
import java.lang.Math;

/**
 * 2048
 * Original Date: 02/16/19
 * Modified Date: 12/23/22
 * A classic game of 2048.
 * 
 * @author Johnson Le
 */

class gameOf2048 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to 2048");
        System.out.println("W is for UP");
        System.out.println("A is for LEFT");
        System.out.println("S is for DOWN");
        System.out.println("D is for RIGHT");
        System.out.println("Start");
        String choice = ""; // Choice is the direction the user enters (W, A, S, D)
        int winORnot = 0, loseORnot = 0;
        int x00 = 0, x10 = 0, x20 = 0, x30 = 0; // Variables declared in form of a matrix (xij form)
        int x01 = 0, x11 = 0, x21 = 0, x31 = 0;
        int x02 = 0, x12 = 0, x22 = 0, x32 = 0;
        int x03 = 0, x13 = 0, x23 = 0, x33 = 0;
        int matrix[][] = { { x00, x01, x02, x03 }, { x10, x11, x12, x13 }, { x20, x21, x22, x23 },
                { x30, x31, x32, x33 } };
        matrix = RandomInsertion(matrix); // A random number is inserted // all variables put in a 2D array OR matrix
        PrintDisplay(matrix); // Then displayed
        for (int q = 1; q <= 2048; q++) // Within 2048 moves you have to reach 2048
        {
            System.out.println("Enter your choice of direction (Enter stop to exit)");
            choice = in.next(); // Recurring choice changes thus, changing direction
            if (choice.equalsIgnoreCase("w")) {
                matrix = calculateW(matrix); // Variables are shifted
                matrix = RandomInsertion(matrix); // The random is inserted
                PrintDisplay(matrix); // Then displayed
            } else if (choice.equalsIgnoreCase("a")) {
                matrix = calculateA(matrix); // Variables are shifted
                matrix = RandomInsertion(matrix); // The random is inserted
                PrintDisplay(matrix); // Then displayed
            } else if (choice.equalsIgnoreCase("s")) {
                matrix = calculateS(matrix);
                matrix = RandomInsertion(matrix); // Looks UP
                PrintDisplay(matrix);
            } else if (choice.equalsIgnoreCase("d")) {
                matrix = calculateD(matrix);
                matrix = RandomInsertion(matrix); // Looks UP
                PrintDisplay(matrix);
            } else if (choice.equalsIgnoreCase("stop")) {
                q = 4000; // If stop is pressed then the q is raised higher than 2048.
                System.out.println("Program has stopped"); // Thus, exiting the loop
            }
            if (winORnot == 0) { // Calls a function, checking if you won or not
                winORnot = winChecker(matrix); // If you win then winORnot is changed to 1
            } // and then condition is not checked anymore
            loseORnot = loseChecker(matrix);
            if (loseORnot == 1) {
                q = 4000; // If you lose then the loop exits because of this
            }
        }
    }

    /**
     * Shifts all variables towards the upward direction
     * Pre: w[][] should be a 4 by 4 matrix
     * Post: Reaaranged matrix is returned
     */
    public static int[][] calculateW(int w[][]) {
        int ProEXEw1, ProEXEw2, ProEXEw3, ProEXEw4, ProEXEw5; // ProEXE means program executed or not (and number of
                                                              // times)
        for (int w2 = 0; w2 <= 3; w2++) // Used recognize patterns and evaluate accordingly
        {
            ProEXEw1 = 0;
            ProEXEw2 = 0;
            ProEXEw3 = 0;
            ProEXEw4 = 0;
            ProEXEw5 = 0;
            if (w[w2][0] == w[w2][1] || w[w2][0] == 0) {
                if (w[w2][0] == w[w2][1] && w[w2][0] != 0) {
                    ProEXEw4 += 1;
                    ProEXEw5 += 1;
                }
                w[w2][0] += w[w2][1];
                w[w2][1] = 0;
            }
            if (w[w2][1] == w[w2][2] || w[w2][1] == 0) {
                ProEXEw1 = 1;
                if (w[w2][1] == 0) {
                    ProEXEw4 += 1;
                    ProEXEw5 += 1;
                }
                if (w[w2][1] == 0) {
                    ProEXEw3 = 1;
                }
                w[w2][1] += w[w2][2];
                w[w2][2] = 0;
            }
            if (w[w2][2] == w[w2][3] || w[w2][2] == 0) {
                ProEXEw2 = 1;
                if (w[w2][2] == 0) {
                    if (w[w2][1] == w[w2][3]) {
                        ProEXEw4 += 1;
                    }
                    ProEXEw5 += 1;
                    ProEXEw2 = 0;
                }
                w[w2][2] += w[w2][3];
                w[w2][3] = 0;
            }
            if (ProEXEw2 == 1 && w[w2][1] == 0) {
                w[w2][1] += w[w2][2];
                w[w2][2] = 0;
            } else if (ProEXEw2 == 0 && (w[w2][1] == w[w2][2] || w[w2][1] == 0) && (ProEXEw4 != 2)) {
                w[w2][1] += w[w2][2];
                w[w2][2] = 0;
            } else if (ProEXEw4 == 2 && (w[w2][0] == w[w2][3] && w[w2][0] == 0) && (w[w2][1] == w[w2][2])) {
                w[w2][1] += w[w2][2];
                w[w2][2] = 0;
            }
            if (ProEXEw1 == 1 && w[w2][0] == 0 && (true != (ProEXEw4 == 3))) {
                w[w2][0] += w[w2][1];
                w[w2][1] = 0;
            } else if (ProEXEw1 == 1 && ProEXEw3 == 1 && (w[w2][0] == w[w2][1] || w[w2][0] == 0) &&
                    (true != (ProEXEw4 == 3) && (true != (ProEXEw5 == 3)))) {
                w[w2][0] += w[w2][1];
                w[w2][1] = 0;
            }
            if (w[w2][1] == 0 || (ProEXEw2 == 0 && ProEXEw1 == 1 && ProEXEw4 == 2 && ProEXEw5 == 2 &&
                    ProEXEw3 == 1 && w[w2][1] == w[w2][2])) {
                w[w2][1] += w[w2][2];
                w[w2][2] = 0;
            }
            if ((w[w2][0] == w[w2][1] || w[w2][0] == 0) && (ProEXEw4 == 2) && (ProEXEw5 == 2) &&
                    (ProEXEw2 == 0) && ProEXEw2 != 0) {
                w[w2][0] += w[w2][1];
                w[w2][1] = 0;
            }
        }
        return (w);
    }

    /**
     * Shifts all variables towards the left direction
     * Pre: a[][] should be a 4 by 4 matrix
     * Post: Reaaranged matrix is returned
     */
    public static int[][] calculateA(int a[][]) {
        int ProEXEa1, ProEXEa2, ProEXEa3, ProEXEa4, ProEXEa5; // ProEXE means program executed or not (and number of
                                                              // times)
        for (int a2 = 0; a2 <= 3; a2++) // Used recognize patterns and evaluate accordingly
        {
            ProEXEa1 = 0;
            ProEXEa2 = 0;
            ProEXEa3 = 0;
            ProEXEa4 = 0;
            ProEXEa5 = 0;
            if (a[0][a2] == a[1][a2] || a[0][a2] == 0) {
                if (a[0][a2] == a[1][a2] && a[0][a2] != 0) {
                    ProEXEa4 += 1;
                    ProEXEa5 += 1;
                }
                a[0][a2] += a[1][a2];
                a[1][a2] = 0;
            }
            if (a[1][a2] == a[2][a2] || a[1][a2] == 0) {
                ProEXEa1 = 1;
                if (a[1][a2] == 0) {
                    ProEXEa4 += 1;
                    ProEXEa5 += 1;
                }
                if (a[1][a2] == 0) {
                    ProEXEa3 = 1;
                }
                a[1][a2] += a[2][a2];
                a[2][a2] = 0;
            }
            if (a[2][a2] == a[3][a2] || a[2][a2] == 0) {
                ProEXEa2 = 1;
                if (a[2][a2] == 0) {
                    if (a[1][a2] == a[3][a2]) {
                        ProEXEa4 += 1;
                    }
                    ProEXEa5 += 1;
                    ProEXEa2 = 0;
                }
                a[2][a2] += a[3][a2];
                a[3][a2] = 0;
            }
            if (ProEXEa2 == 1 && a[1][a2] == 0) {
                a[1][a2] += a[2][a2];
                a[2][a2] = 0;
            } else if (ProEXEa2 == 0 && (a[1][a2] == a[2][a2] || a[1][a2] == 0) && (true != (ProEXEa4 == 2))) {
                a[1][a2] += a[2][a2];
                a[2][a2] = 0;
            } else if (ProEXEa4 == 2 && (a[0][a2] == a[3][a2] && a[0][a2] == 0) && (a[1][a2] == a[2][a2])) {
                a[1][a2] += a[2][a2];
                a[2][a2] = 0;
            }
            if (ProEXEa1 == 1 && a[0][a2] == 0 && (true != (ProEXEa4 == 3))) {
                a[0][a2] += a[1][a2];
                a[1][a2] = 0;
            } else if (ProEXEa1 == 1 && ProEXEa3 == 1 && (a[0][a2] == a[1][a2] || a[0][a2] == 0) &&
                    (true != (ProEXEa4 == 3) && (true != (ProEXEa5 == 3)))) {
                a[0][a2] += a[1][a2];
                a[1][a2] = 0;
            }
            if (a[1][a2] == 0 || (ProEXEa2 == 0 && ProEXEa1 == 1 && ProEXEa4 == 2 && ProEXEa5 == 2 &&
                    ProEXEa3 == 1 && a[1][a2] == a[2][a2])) {
                a[1][a2] += a[2][a2];
                a[2][a2] = 0;
            }
            if ((a[0][a2] == a[1][a2] || a[0][a2] == 0) && (ProEXEa4 == 2) && (ProEXEa5 == 2) && (ProEXEa2 == 0)
                    && ProEXEa2 != 0) {
                a[0][a2] += a[1][a2];
                a[1][a2] = 0;
            }
        }
        return (a);
    }

    /**
     * Shifts all variables towards the downward direction
     * Pre: s[][] should be a 4 by 4 matrix
     * Post: Rearranged matrix is returned
     */
    public static int[][] calculateS(int s[][]) {
        int ProEXEs1, ProEXEs2, ProEXEs3, ProEXEs4, ProEXEs5; // ProEXE means program executed or not (and number of
                                                              // times)
        for (int s2 = 0; s2 <= 3; s2++) // Used recognize patterns and evaluate accordingly
        {
            ProEXEs1 = 0;
            ProEXEs2 = 0;
            ProEXEs3 = 0;
            ProEXEs4 = 0;
            ProEXEs5 = 0;
            if (s[s2][3] == s[s2][2] || s[s2][3] == 0) {
                if (s[s2][3] == s[s2][2] && s[s2][3] != 0) {
                    ProEXEs4 += 1;
                    ProEXEs5 += 1;
                }
                s[s2][3] += s[s2][2];
                s[s2][2] = 0;
            }
            if (s[s2][2] == s[s2][1] || s[s2][2] == 0) {
                ProEXEs1 = 1;
                if (s[s2][2] == 0) {
                    ProEXEs4 += 1;
                    ProEXEs5 += 1;
                }
                if (s[s2][2] == 0) {
                    ProEXEs3 = 1;
                }
                s[s2][2] += s[s2][1];
                s[s2][1] = 0;
            }
            if (s[s2][1] == s[s2][0] || s[s2][1] == 0) {
                ProEXEs2 = 1;
                if (s[s2][1] == 0) {
                    if (s[s2][2] == s[s2][0]) {
                        ProEXEs4 += 1;
                    }
                    ProEXEs5 += 1;
                    ProEXEs2 = 0;
                }
                s[s2][1] += s[s2][0];
                s[s2][0] = 0;
            }
            if (ProEXEs2 == 1 && s[s2][2] == 0) {
                s[s2][2] += s[s2][1];
                s[s2][1] = 0;
            } else if (ProEXEs2 == 0 && (s[s2][2] == s[s2][1] || s[s2][2] == 0) && (ProEXEs4 != 2)) {
                s[s2][2] += s[s2][1];
                s[s2][1] = 0;
            } else if (ProEXEs4 == 2 && (s[s2][3] == s[s2][0] && s[s2][3] == 0) && (s[s2][2] == s[s2][1])) {
                s[s2][2] += s[s2][1];
                s[s2][1] = 0;
            }
            if (ProEXEs1 == 1 && s[s2][3] == 0 && (true != (ProEXEs4 == 3))) {
                s[s2][3] += s[s2][2];
                s[s2][2] = 0;
            } else if (ProEXEs1 == 1 && ProEXEs3 == 1 && (s[s2][3] == s[s2][2] || s[s2][3] == 0) &&
                    (true != (ProEXEs4 == 3) && (true != (ProEXEs5 == 3)))) {
                s[s2][3] += s[s2][2];
                s[s2][2] = 0;
            }
            if (s[s2][2] == 0 || (ProEXEs2 == 0 && ProEXEs1 == 1 && ProEXEs4 == 2 && ProEXEs5 == 2 &&
                    ProEXEs3 == 1 && s[s2][2] == s[s2][1])) {
                s[s2][2] += s[s2][1];
                s[s2][1] = 0;
            }
            if ((s[s2][3] == s[s2][2] || s[s2][3] == 0) && (ProEXEs4 == 2) && (ProEXEs5 == 2) && (ProEXEs2 == 0) &&
                    ProEXEs2 != 0) {
                s[s2][3] += s[s2][2];
                s[s2][2] = 0;
            }
        }
        return (s);
    }

    /**
     * Shifts all variables towards the right direction
     * Pre: d[][] should be a 4 by 4 matrix
     * Post: Rearranged matrix is returned
     */
    public static int[][] calculateD(int d[][]) {
        int ProEXEd1, ProEXEd2, ProEXEd3, ProEXEd4, ProEXEd5; // ProEXE means program executed or not (and number of
                                                              // times)
        for (int d2 = 0; d2 <= 3; d2++) // Used recognize patterns and evaluate accordingly
        {
            ProEXEd1 = 0;
            ProEXEd2 = 0;
            ProEXEd3 = 0;
            ProEXEd4 = 0;
            ProEXEd5 = 0;
            if (d[3][d2] == d[2][d2] || d[3][d2] == 0) {
                if (d[3][d2] == d[2][d2] && d[3][d2] != 0) {
                    ProEXEd4 += 1;
                    ProEXEd5 += 1;
                }
                d[3][d2] += d[2][d2];
                d[2][d2] = 0;
            }
            if (d[2][d2] == d[1][d2] || d[2][d2] == 0) {
                ProEXEd1 = 1;
                if (d[2][d2] == 0) {
                    ProEXEd4 += 1;
                    ProEXEd5 += 1;
                }
                if (d[2][d2] == 0) {
                    ProEXEd3 = 1;
                }
                d[2][d2] += d[1][d2];
                d[1][d2] = 0;
            }
            if (d[1][d2] == d[0][d2] || d[1][d2] == 0) {
                ProEXEd2 = 1;
                if (d[1][d2] == 0) {
                    if (d[2][d2] == d[0][d2]) {
                        ProEXEd4 += 1;
                    }
                    ProEXEd5 += 1;
                    ProEXEd2 = 0;
                }
                d[1][d2] += d[0][d2];
                d[0][d2] = 0;
            }
            if (ProEXEd2 == 1 && d[2][d2] == 0) {
                d[2][d2] += d[1][d2];
                d[1][d2] = 0;
            } else if (ProEXEd2 == 0 && (d[2][d2] == d[1][d2] || d[2][d2] == 0) && (true != (ProEXEd4 == 2))) {
                d[2][d2] += d[1][d2];
                d[1][d2] = 0;
            } else if (ProEXEd4 == 2 && (d[3][d2] == d[0][d2] && d[3][d2] == 0) && (d[2][d2] == d[1][d2])) {
                d[2][d2] += d[1][d2];
                d[1][d2] = 0;
            }
            if (ProEXEd1 == 1 && d[3][d2] == 0 && (true != (ProEXEd4 == 3))) {
                d[3][d2] += d[2][d2];
                d[2][d2] = 0;
            } else if (ProEXEd1 == 1 && ProEXEd3 == 1 && (d[3][d2] == d[2][d2] || d[3][d2] == 0) &&
                    (true != (ProEXEd4 == 3) && (true != (ProEXEd5 == 3)))) {
                d[3][d2] += d[2][d2];
                d[2][d2] = 0;
            }
            if (d[2][d2] == 0 || (ProEXEd2 == 0 && ProEXEd1 == 1 && ProEXEd4 == 2 && ProEXEd5 == 2 &&
                    ProEXEd3 == 1 && d[2][d2] == d[1][d2])) {
                d[2][d2] += d[1][d2];
                d[1][d2] = 0;
            }
            if ((d[3][d2] == d[2][d2] || d[3][d2] == 0) && (ProEXEd4 == 2) && (ProEXEd5 == 2) && (ProEXEd2 == 0)
                    && ProEXEd2 != 0) {
                d[3][d2] += d[2][d2];
                d[2][d2] = 0;
            }
        }
        return (d);
    }

    /**
     * Checks if matrix has any 2048. If it does then you win
     * Pre: winCheck[][] should be a 4 by 4 matrix
     * Post: 1 is returned if 2048 is found
     */
    public static int winChecker(int winCheck[][]) {
        for (int var1w = 0; var1w <= 3; var1w++) // Nested For loop checks more efficiently
        {
            for (int var2w = 0; var2w <= 3; var2w++) {
                if (winCheck[var1w][var2w] == 2048) // If any variable is 2048 then you win (1 is returned)
                {
                    System.out.println("YOU WIN, but continue playing");
                    return (1);
                }
            }
        }
        return (0); // 0 is returned if you haven't won yet
    }

    /**
     * Checks if matrix has any 0s. If it does not then you lose
     * Pre: loseCheck[][] should be a 4 by 4 matrix
     * Post: 1 is returned if no zeros found
     */
    public static int loseChecker(int loseCheck[][]) {
        int counter = 0;
        for (int var1l = 0; var1l <= 3; var1l++) // Nested For loop checks more efficiently
        {
            for (int var2l = 0; var2l <= 3; var2l++) {
                if (loseCheck[var1l][var2l] == 0) {
                    counter++;
                }
            }
        }
        if (counter == 0) {
            System.out.println("YOU LOSE, sorry");
            return (1);
        }
        return (0);
    }

    /**
     * Print matrix into a form of a matrix (4 by 4)
     * Pre: Matrix entered should be a 4 by 4 matrix
     * Post: One variable is changed from 0 to 2
     */
    public static void PrintDisplay(int pd[][]) // pd stands for printdisplay
    {
        System.out.println(pd[0][0] + " " + pd[1][0] + " " + pd[2][0] + " " + pd[3][0]);
        System.out.println(pd[0][1] + " " + pd[1][1] + " " + pd[2][1] + " " + pd[3][1]);
        System.out.println(pd[0][2] + " " + pd[1][2] + " " + pd[2][2] + " " + pd[3][2]);
        System.out.println(pd[0][3] + " " + pd[1][3] + " " + pd[2][3] + " " + pd[3][3]);
        System.out.println(""); // Printed to immulate a matrix
    }

    /**
     * Insert a random number into the matrix
     * Pre: Atleast one value in random[][] should be zero
     * Post: One variable is changed from 0 to 2
     */
    public static int[][] RandomInsertion(int random[][]) {
        int rand;
        do {
            rand = (int) (16 * Math.random());
            if (random[rand / 4][rand % 4] == 0) {
                random[rand / 4][rand % 4] = 2;
                rand = 20;
            }
        } while (rand != 20); // rand is 20 means one variable has been changed and then it exits
        return random;
    }
}