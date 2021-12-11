package cs2030.simulator;

import java.util.ArrayList;

class Done extends Event {
    private final int serverId;
    private final ArrayList<Server> servers;

    Done(double eventTime, double waitTime, Customer customer,
            int serverId, ArrayList<Server> servers) {
        super(eventTime, waitTime, customer);
        this.serverId = serverId;
        this.servers = servers;
    }

    @Override
    ServerRest run(int restId, ArrayList<Double> restTimings, 
            double serviceTime) {
        return new ServerRest(this.getEventTime() + restTimings.get(restId),
                0, this.getCustomer(), serverId, servers);
    }

    @Override
    public Stats updateStats(Stats stats) {
        return stats;
    }

    @Override
    String getEvent() {
        return "D";
    }

    @Override
    public String toString() {
        return (this.getCustomer().getGreedy()) 
            ? (super.toString() + "(greedy) done serving by server " + serverId) 
            : super.toString() + " done serving by server " + serverId;
    }
}
