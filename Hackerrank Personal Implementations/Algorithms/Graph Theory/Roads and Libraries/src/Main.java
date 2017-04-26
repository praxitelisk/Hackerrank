import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by praxitelis on 17/4/2017.
 */
public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int numOfQuieries = in.nextInt();

        for(int a0 = 0; a0 < numOfQuieries; a0++) {
            int numOfCities = in.nextInt();
            int numOfRoads = in.nextInt();
            int libraryCost = in.nextInt();
            int roadCost = in.nextInt();

            double[][] graph = new double[numOfCities][numOfCities];

            Random rand = new Random();

            ArrayList<Integer> mstSet = new ArrayList<Integer>();

            //add the first vertex to mstSet according to PRIM's algorithm
            mstSet.add(0);

           //initialize array
            for(int i=0; i<numOfCities; i++)
                for(int j=0; j< numOfCities; j++)
                    graph[i][j] = Double.POSITIVE_INFINITY;

           //create adjacent cities
            for (int a1 = 0; a1 < numOfRoads; a1++) {
                int city_1 = in.nextInt();
                int city_2 = in.nextInt();
                int cost = rand.nextInt(1000);

                try {
                    graph[city_1 - 1][city_2 - 1] = cost;
                }
                catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    continue;
                }

                try {
                    graph[city_2 - 1][city_1 - 1] = cost;
                }
                catch (java.lang.ArrayIndexOutOfBoundsException e) {
                    continue;
                }

            }

            //print array
            System.out.print("{");
            for(int i=0; i< numOfCities; i++) {
                System.out.print("{");
                for (int j = 0; j < numOfCities; j++)
                    System.out.print(graph[i][j] + " ");
                System.out.println("}");
            }
            System.out.println("}");


            //initialize while loop auxiliary variables
            int lookupVertex = mstSet.indexOf(0);
            double minCost = 0;
            int totalMinCost = 0;
            int index = 1;

            while(mstSet.size() != numOfCities) {

                int indexOfVertex = lookupVertex;
                //System.out.println("lookupVertex " + lookupVertex);

                double[] pivotArray = copyColumn(graph, lookupVertex);

                double[] nextMinAndVertex = getMinAndNextVertex(pivotArray, mstSet, graph);


                minCost = nextMinAndVertex[0];
                //System.out.println("min cost "+ minCost);
                totalMinCost += minCost;

                lookupVertex = (int) nextMinAndVertex[1];
                //System.out.println(" next lookupVertex " + lookupVertex);

                mstSet.add(index, indexOfVertex);
                index++;

            }

            System.out.println("total min cost "+ totalMinCost);

        }
    } //end of main

    /* this method copy a row from a two dimensional array to a vector array */
    private static double[] copyColumn(double[][] graph, int lookupVertex) {

        double[] array = new double[graph[lookupVertex].length];

        for(int i=0; i<graph[lookupVertex].length; i++) {

            array[i] = graph[lookupVertex][i];

        }

        return array;

    }

    /* this function returns the minimum cost to travel from array and gives also the
     * the next cheapest town to travel
     * */
    private static double[] getMinAndNextVertex(double[] array, ArrayList<Integer> mstSet, double[][] graph) {

        double[] nextMinAndVertex = new double[2];
        double min = Double.POSITIVE_INFINITY;
        int index = 0;
        int indexOfMin = 0;

        while(index < array.length) {


            if (array[index] < min && !mstSet.contains(index)) {
                min = array[index];
                indexOfMin = index;
            }
            else
                index++;
        }

        if (min != Double.POSITIVE_INFINITY) {
            nextMinAndVertex[0] = min;
            nextMinAndVertex[1] = indexOfMin;
        }

        //if min is still infitiny, which means there are distant vertixes
        //who are not connected to the rest of the graph.
        else {
            Collections.sort(mstSet);
            nextMinAndVertex[1] = mstSet.size();
            double[] auxArray = copyColumn(graph, mstSet.size());
            double[] results = getMinAndNextVertex(auxArray, mstSet, graph);
            mstSet.add(mstSet.size());
            nextMinAndVertex[0] = results[0];
            nextMinAndVertex[1] = results[1];

        }


        return nextMinAndVertex;
    }

}