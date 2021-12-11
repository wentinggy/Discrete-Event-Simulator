package cs2030.simulator;

import java.util.ArrayList;

class Wait extends Event {

    private final ArrayList<Server> servers;
    private final int serverId;

    Wait(double eventTime, Customer customer,
            int serverId, ArrayList<Server> servers) {
        super(eventTime, 0, customer);
        this.serverId = serverId;
        this.servers = servers;
    }

    @Override
    Event run(int restId, ArrayList<Double> restTimings, double serviceTime) {
        Server currentServer = servers.get(serverId - 1);
        currentServer.getQueue().add(this.getCustomer());
        servers.set(serverId - 1, new Server(serverId,
                    currentServer.getNextAvailTime(), false,
                    currentServer.getQueue(),
                    currentServer.getMaxSize()));
        return new Event(0, 0, this.getCustomer()); 
    }

    @Override
    public Stats updateStats(Stats stats) {
        return stats;
    }

    @Override
    String getEvent() {
        return "W";
    }

    @Override
    public String toString() {
        return (this.getCustomer().getGreedy()) 
            ? (super.toString() + "(greedy) waits at server " + serverId) 
            : super.toString() + " waits at server " + serverId;
    }
}
