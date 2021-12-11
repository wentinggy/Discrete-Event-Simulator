import cs2030.simulator.Simulator;

import java.util.ArrayList;
import java.util.Scanner;

class Main4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        int maxSize = sc.nextInt();
        int numOfCust = sc.nextInt();
        //first input is the number of scanners
        //second input is max queue length
        //third input is numOfCustomers

        boolean isGreedy = false;
        ArrayList<Double> arrivalTimings = new ArrayList<Double>();
        ArrayList<Double> serviceTimings = new ArrayList<Double>();
        ArrayList<Boolean> greedy = new ArrayList<Boolean>();
        ArrayList<Double> restTimings = new ArrayList<Double>();
        int counter = 0;
        while (sc.hasNextDouble()) {
            if (counter < numOfCust) {
                double arrivalTime = sc.nextDouble();
                arrivalTimings.add(arrivalTime);

                double serviceTime = sc.nextDouble();
                serviceTimings.add(serviceTime);
                greedy.add(isGreedy);
                counter++;
            } else {
                double restTime = sc.nextDouble();
                restTimings.add(restTime);
            }
        }

        Simulator simulate = new Simulator(numOfServers, arrivalTimings, serviceTimings, 
                maxSize, greedy, restTimings);
        simulate.simulator();
    }
}
