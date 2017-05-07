import java.util.ArrayList;
import java.util.Scanner;

public class MaxTourism {

    static void getConnectedCity(int city, int[][] route, int m, ArrayList<Integer> visitedCities) {


        for(int route_i=0; route_i<m; route_i++) {
            for(int route_j=0; route_j<2; route_j++) {
                if (city == (route[route_i][route_j])) {
                    if (route_j == 1 && !visitedCities.contains(route[route_i][route_j-1])) {
                        visitedCities.add(route[route_i][route_j-1]);
                        getConnectedCity(route[route_i][route_j-1], route, m, visitedCities);
                    }
                    else if (route_j == 0 && !visitedCities.contains(route[route_i][route_j+1])) {
                        visitedCities.add(route[route_i][route_j+1]);
                        getConnectedCity(route[route_i][route_j+1], route, m, visitedCities);
                    }
                }

            }
        }


    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] route = new int[m][2];
        for(int route_i=0; route_i < m; route_i++){
            for(int route_j=0; route_j < 2; route_j++){
                route[route_i][route_j] = in.nextInt();
            }
        }
        // Write Your Code Here
        int max = 0;
        for(int city = 0; city < n; city++) {


            ArrayList<Integer> visitedCities = new ArrayList<Integer>();

            visitedCities.add(city+1);

            getConnectedCity(city+1, route, m, visitedCities);


            if (visitedCities.size() > max)
                max = visitedCities.size();
        }

        System.out.println(max);
    }
}