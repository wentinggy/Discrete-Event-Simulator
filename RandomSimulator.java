package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class RandomSimulator {

    private final PriorityQueue<Event> events = 
        new PriorityQueue<Event>(new EventComparator());
    private final int numOfServers;
    private final int maxSize;
    private final int numOfCustomers;
    private final int seed;
    private final double lambda;
    private final double mu;
    private final double rho;
    private final double restingProb;
    private final double greedyProb;

    public RandomSimulator(int numOfServers, int maxSize, int numOfCustomers,
            int seed, double lambda, double mu, double rho,
            double restingProb, double greedyProb) {
        this.numOfServers = numOfServers;
        this.maxSize = maxSize;
        this.numOfCustomers = numOfCustomers;
        this.seed = seed;
        this.lambda = lambda;
        this.mu = mu;
        this.rho = rho;
        this.restingProb = restingProb;
        this.greedyProb = greedyProb;
    }

    public void simulator() {

        //generate the random outputs
        RandomGenerator generate = new RandomGenerator(seed, lambda, mu, rho);

        ArrayList<Server> servers = new ArrayList<>();
        for (int i = 0; i < numOfServers; i++) {
            servers.add(new Server(i + 1, 0, true, 
                        new ArrayList<>(),
                        maxSize));
        }

        ArrayList<Double> restTimings = new ArrayList<>();
        for (int i = 0; i < numOfCustomers; i++) {
            double rest = (restingProb > generate.genRandomRest())
                ? generate.genRestPeriod()
                : 0;
            restTimings.add(rest);
        }

        double newTime = 0;
        for (int i = 0; i < numOfCustomers; i++) {
            Customer a = (greedyProb > generate.genCustomerType())
                ? new Customer(i + 1, newTime, 0, true)
                : new Customer(i + 1, newTime, 0, false);

            events.add(new Arrive(a, servers));
            newTime += generate.genInterArrivalTime();
        } 

        int restId = -1;
        Stats stats = new Stats(0.00, 0, 0);
        while (!(events.isEmpty())) {
            Event e = events.poll();
            int serverId = e.getServerId();
            
            //Done event
            if (e.getEvent().equals("D")) {
                restId++;
            }
            
            //server rest event
            if (e.getEvent().equals("R")) {
                ArrayList<Server> restArray = e.getArray();
                Server currentServer = restArray.get(serverId - 1);

                Customer waitingCustomer = (currentServer.isEmpty()) 
                    ? e.getCustomer() : currentServer.getQueue().get(0);
                double endTime = (currentServer.isEmpty())
                    ? e.getWaitTime() : e.getEventTime() - waitingCustomer.getArrivalTime();
                
                boolean success = (currentServer.isEmpty())
                    ? events.add(new Event(0, endTime, waitingCustomer))
                    : events.add(new Serve(e.getEventTime(), endTime,
                                waitingCustomer, serverId, restArray));
                
                if (currentServer.isEmpty()) {
                    restArray.set(serverId - 1, new Server(serverId,
                                e.getEventTime(),
                                true, currentServer.getQueue(), 
                                currentServer.getMaxSize()));
                } else {
                    restArray.set(serverId - 1, new Server(serverId,
                                e.getEventTime(), false,
                                currentServer.getQueue(), 
                                currentServer.getMaxSize()));
                    currentServer.getQueue().remove(0);
                }


            } else if (e.getEvent().equals("S")) {
                System.out.println(e);
                stats = e.updateStats(stats);
                Event event = e.run(restId, restTimings, 
                        generate.genServiceTime());
                if (event != null) {
                    events.add(event);
                }
            } else if (e.getEvent().equals("D") ||
                    e.getEvent().equals("W") ||
                    e.getEvent().equals("L") ||
                    e.getEvent().equals("A")) {
                System.out.println(e);
                stats = e.updateStats(stats);
                Event event = e.run(restId, restTimings, 0);
                if (event != null) {
                    events.add(event);
                }
            }
        }

        System.out.println(stats);
    }
}
