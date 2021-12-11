package cs2030.simulator;

import java.util.ArrayList;

class Event {
    private final double eventTime;
    private final Customer customer;
    private final double waitTime;

    Event(double eventTime, double waitTime, Customer customer) {        
        this.eventTime = eventTime;
        this.waitTime = waitTime;
        this.customer = customer;
    }
    
    //method to get the waiting time
    double getWaitTime() {
        return waitTime;
    }
    
    //method to identify the different events
    String getEvent() {
        return "THIS IS CAUSING TOO MUCH STRESS";
    }
    
    //get the time of the event occuring
    double getEventTime() {
        return this.eventTime;
    }

    //get customer
    Customer getCustomer() {
        return this.customer;
    }

    //method to get the 3 final outputs
    Stats updateStats(Stats stats) {
        return stats;
    }

    //get customer's Id
    int getCustomerId() {
        return customer.getCustomerId();
    }
    
    //method to get the array of servers
    ArrayList<Server> getArray() {
        return new ArrayList<Server>();
    }

    //get the server id position
    int getServerId() {
        return 0; 
    }
    
    //method to execute the next event
    Event run(int restId, ArrayList<Double> restTimings, double serviceTime) {
        return null;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d", 
                eventTime, this.getCustomerId());
    }
 
}
