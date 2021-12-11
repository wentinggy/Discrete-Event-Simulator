package cs2030.simulator;

import java.util.ArrayList;

class Arrive extends Event {

    private final ArrayList<Server> servers;

    Arrive(Customer customer, ArrayList<Server> servers) {
        
        super(customer.getArrivalTime(), 0, customer);
        this.servers = servers;
    }

    @Override
    Event run(int restId, ArrayList<Double> restTimings, double serviceTime) {

        if (!this.getCustomer().getGreedy()) {

            for (Server server: servers) {
                if (server.getAvail()) {
                    return new Serve(this.getEventTime(), 0, this.getCustomer(),
                            server.getServerId(), servers);
                }
            }

            for (Server server: servers) {
                if (server.canAdd()) {
                    return new Wait(this.getEventTime(), this.getCustomer(),
                            server.getServerId(), servers);
                }
            }
            return new Leave(this.getEventTime(), 0, this.getCustomer());
        } else { 
            for (Server server: servers) {
                if (server.getAvail()) {
                    return new Serve(this.getEventTime(), 0, this.getCustomer(),
                            server.getServerId(), servers);
                }
            }
            if (fewestCust(servers).canAdd()) {
                Server currentServer = fewestCust(servers);
                return new Wait(this.getEventTime(), this.getCustomer(), 
                        currentServer.getServerId(), servers);
            }
        }
        return new Leave(this.getEventTime(), 0, this.getCustomer());
    }
    
    //method to find the server with the shortest queue
    Server fewestCust(ArrayList<Server> servers) {
        Server shortest = servers.get(0);
        for (int i = 1; i < servers.size(); i++) {
            if (servers.get(i).length() < shortest.length()) {
                shortest = servers.get(i);
            }
        }
        return shortest;
    }
    
    @Override
    String getEvent() {
        return "A";
    }

    @Override 
    public Stats updateStats(Stats stats) {
        return stats;
    }

    @Override
    public String toString() {
        return (this.getCustomer().getGreedy()) 
            ? (super.toString() + "(greedy) arrives") : super.toString() + " arrives";
    }
}
