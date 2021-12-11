package cs2030.simulator;

class Customer {

    private final int customerId;
    private final double arrivalTime;
    private final double serviceTime;
    private final boolean isGreedy;

    //level 3
    Customer(int customerId, double arrivalTime, double serviceTime, 
            boolean isGreedy) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.isGreedy = isGreedy;
    }
    
    //level 1
    Customer(int customerId, double arrivalTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.serviceTime = 1.00; 
        this.isGreedy = false;
    }
    
    //level 2 and 4
    Customer(int customerId, double arrivalTime, double serviceTime) {
        this.customerId = customerId;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.isGreedy = false;
    }
    
    //get customer's Id
    int getCustomerId() {
        return this.customerId;
    }

    //get customer's arrival timings
    double getArrivalTime() {
        return this.arrivalTime;
    }

    //get customer's service time
    double getServiceTime() {
        return this.serviceTime;
    }

    //see if customer is greeeedyyy
    boolean getGreedy() {
        return this.isGreedy;
    }

    @Override 
    public String toString() {
        return arrivalTime + Integer.toString(customerId)
            + " arrives";
    }
}
