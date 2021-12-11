package cs2030.simulator;

import java.util.ArrayList;

class Leave extends Event {

    Leave(double eventTime, double waitTime, Customer customer) {
        super(eventTime, waitTime, customer);
    }

    @Override
    Event run(int restId, ArrayList<Double> restTimings, double serviceTime) {
        return new Event(0, 0, this.getCustomer());
    }

    @Override
    public Stats updateStats(Stats stats) {
        Stats newStats = stats.addNumLeft();
        return newStats;
    }
    
    @Override
    String getEvent() {
        return "L";
    }
    
    @Override
    public String toString() {
        return (this.getCustomer().getGreedy()) 
            ? (super.toString() + "(greedy) leaves") 
            : super.toString() + " leaves";
    }
}
