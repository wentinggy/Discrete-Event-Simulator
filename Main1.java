import cs2030.simulator.Simulator;

import java.util.ArrayList;
import java.util.Scanner;

class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        int maxSize = 1;
        boolean isGreedy = false;
        double serviceTime = 1.00;
        ArrayList<Double> arrivalTimings = new ArrayList<Double>();
        ArrayList<Double> serviceTimings = new ArrayList<Double>();
        ArrayList<Boolean> greedy = new ArrayList<Boolean>();

        ArrayList<Double> restTimings = new ArrayList<Double>();
        double rest = 0.00;
        while (sc.hasNextDouble()) {
            double arrivalT = sc.nextDouble();
            arrivalTimings.add(arrivalT);
            greedy.add(isGreedy);
            restTimings.add(rest);
            serviceTimings.add(serviceTime);
        }

        Simulator simulate = new Simulator(numOfServers, arrivalTimings, serviceTimings, 
                maxSize, greedy, restTimings);
        simulate.simulator();
    }
}
