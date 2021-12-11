import cs2030.simulator.Simulator;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        int maxSize = sc.nextInt();
        //first input is the number of scanners
        //second input is max queue length
        boolean isGreedy = false;
        ArrayList<Double> arrivalTimings = new ArrayList<Double>();
        ArrayList<Double> serviceTimings = new ArrayList<Double>();
        ArrayList<Boolean> greedy = new ArrayList<Boolean>();

        ArrayList<Double> restTimings = new ArrayList<Double>();
        double rest = 0.00;
        while (sc.hasNextDouble()) {

            double arrivalT = sc.nextDouble();
            arrivalTimings.add(arrivalT);

            double serviceT = sc.nextDouble();
            serviceTimings.add(serviceT);
            restTimings.add(rest);
            greedy.add(isGreedy);
        }

        Simulator simulate = new Simulator(numOfServers, arrivalTimings, serviceTimings, 
                maxSize, greedy, restTimings);
        simulate.simulator();
    }
}
