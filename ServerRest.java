package cs2030.simulator;

import java.util.ArrayList;

class ServerRest extends Event {
    private final ArrayList<Server> servers;
    private final int serverId;

    ServerRest(double eventTime, double waitTime, Customer customer,
            int serverId, ArrayList<Server> servers) {
        super(eventTime, waitTime, customer);
        this.serverId = serverId;
        this.servers = servers;
    }
    
    @Override
    public String toString() {
        return "HELP! THIS IS TOO STRESSFUL";
    }
    
    @Override
    public Stats updateStats(Stats stats) {
        return stats;
    }
    
    @Override
    ArrayList<Server> getArray() {
        return servers;
    }

    @Override
    int getServerId() {
        return serverId;
    }

    @Override
    String getEvent() {
        return "R";
    }

}
