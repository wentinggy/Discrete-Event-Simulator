package cs2030.simulator;

import java.util.ArrayList;

class Serve extends Event {

    private final ArrayList<Server> servers;
    private final int serverId;

    Serve(double eventTime, double waitTime, Customer customer, 
            int serverId, ArrayList<Server> servers) {
        super(eventTime, waitTime, customer);
        this.serverId = serverId;
        this.servers = servers;
    }

    @Override
    public Stats updateStats(Stats stats) {
        Stats newStats = stats.addServe();
        newStats = newStats.addWaitTime(this.getEventTime() 
                - this.getCustomer().getArrivalTime());
        return newStats;
    }
    
    @Override
    Done run(int restId, ArrayList<Double> restTimings, double serviceTime) {
        Server currentServer = servers.get(serverId - 1);
        double endTime = this.getEventTime() < currentServer.getNextAvailTime() 
            ? currentServer.getNextAvailTime() 
            : serviceTime + this.getEventTime()
            + this.getCustomer().getServiceTime();
        servers.set(serverId - 1, new Server(serverId,
                    endTime, false, currentServer.getQueue(), 
                    currentServer.getMaxSize()));
        return new Done(endTime, 0, this.getCustomer(), serverId, servers);
    }
    
    @Override
    String getEvent() {
        return "S";
    }

    @Override
    public String toString() {
        return (this.getCustomer().getGreedy()) 
            ? (super.toString() + "(greedy) serves by server " + serverId) 
            : super.toString() + " serves by server " + serverId;
    }
}
