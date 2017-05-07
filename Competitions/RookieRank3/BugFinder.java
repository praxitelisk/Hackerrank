import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class BugFinder {

    static int[] findTheBug(String[] grid){
        // Complete this function
        
        int bugRow = 0;
        int bugColumn = 0;
        int[] results = new int[2];
        boolean found = false;
        int counterRow = 0;
        int counterColumn = 0;
        
        while(counterRow < grid.length && !found ) {
            
            counterColumn = 0;
            
            while(counterColumn < grid[counterRow].length() && !found) {
                if (grid[counterRow].charAt(counterColumn) == 'X') {
                    found = true;
                    bugRow = counterRow;
                    bugColumn = counterColumn;
                    results[0] = bugRow;
                    results[1] = bugColumn;
                }
                else {
                    counterColumn++;
                }
            }
            
            counterRow++;
        }
        
        return results;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        String[] grid = new String[n];
        for(int grid_i=0; grid_i < n; grid_i++){
            grid[grid_i] = in.next();
        }
        // Return an array containing [r, c]
        int[] result = findTheBug(grid);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + (i != result.length - 1 ? "," : ""));
        }
        System.out.println("");
        

    }
}