import cs2030.simulator.RandomSimulator;

import java.util.Scanner;

class Main5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfServers = sc.nextInt();
        int maxSize = sc.nextInt(); 
        int numOfCustomers = sc.nextInt();
        int seed = sc.nextInt(); 
        double lambda = sc.nextDouble();
        double mu = sc.nextDouble();
        double rho = sc.nextDouble();
        double restingProb = sc.nextDouble();
        double greedyProb = sc.nextDouble();
        RandomSimulator simulate = new RandomSimulator(numOfServers,
                maxSize, numOfCustomers, seed, lambda, mu, rho,
                restingProb, greedyProb);
        simulate.simulator();
    }
}
