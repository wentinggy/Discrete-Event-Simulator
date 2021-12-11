package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Simulator {
    private final PriorityQueue<Event> events = 
        new PriorityQueue<Event>(new EventComparator());
    private final ArrayList<Double> arrivalTimings;
    private final ArrayList<Double> serviceTimings;
    private final ArrayList<Server> servers;
    private final ArrayList<Boolean> greedy;
    private ArrayList<Double> restTimings;
    private final int numOfServers;
    private final int maxSize;

    public Simulator(int numOfServers, ArrayList<Double> arrivalTimings, 
            ArrayList<Double> serviceTimings, int maxSize, 
            ArrayList<Boolean> greedy,
            ArrayList<Double> restTimings) {
        this.numOfServers = numOfServers;
        this.maxSize = maxSize;
        this.arrivalTimings = arrivalTimings;
        this.serviceTimings = serviceTimings;
        this.restTimings = restTimings;
        ArrayList<Server> servers = new ArrayList<>();
        for (int i = 0; i < numOfServers; i++) {
            servers.add(new Server(i + 1, 0, true, 
                        new ArrayList<>(),
                        maxSize));
        }
        
        this.greedy = greedy;
        this.servers = servers;
    }       
    
    public void simulator() {

        for (int i = 0; i < serviceTimings.size(); i++) {
            Customer a = new Customer(i + 1, arrivalTimings.get(i), 
                    serviceTimings.get(i), greedy.get(i));
            events.add(new Arrive(a, servers));
        }

        int restId = -1;
        Stats stats = new Stats(0.00, 0, 0);
        while (!(events.isEmpty())) {
            Event e = events.poll();
            int serverId = e.getServerId();
            
            //Done Event
            if (e.getEvent().equals("D")) {
                restId++;
            }
            //ServerRest Event
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
            } else if (e.getEvent().equals("S") ||
                    e.getEvent().equals("D") ||
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
