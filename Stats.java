package cs2030.simulator;

class Stats {
    private final double totalWaitTime;
    private final int numLeft;
    private final int numServed;

    //to get the 3 frickin outputs
    public Stats(double totalWaitTime, int numLeft, int numServed) {
        this.totalWaitTime = totalWaitTime;
        this.numLeft = numLeft;
        this.numServed = numServed;
    }

    //get number of served customers
    public Stats addServe() {
        return new Stats(totalWaitTime, numLeft, numServed + 1);
    }

    //get total waiting time
    public Stats addWaitTime(double time) {
        return new Stats(totalWaitTime + time, numLeft, numServed);
    }

    //get number of customers who left
    public Stats addNumLeft() {
        return new Stats(totalWaitTime, numLeft + 1, numServed);
    }

    @Override
    public String toString() {
        
        double averageWaitTime = (numServed == 0) 
            ? 0
            : totalWaitTime / numServed;
        
        return String.format("[%.3f %d %d]", averageWaitTime, numServed, numLeft);
    }
}
