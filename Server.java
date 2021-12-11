package cs2030.simulator;

import java.util.ArrayList;

class Server {
    private final int serverId;
    private final double nextAvailTime;
    private final boolean isAvail;
    private final int maxSize;
    private final ArrayList<Customer> queue;

    //level 1
    Server(int serverId, double nextAvailTime,
            boolean isAvail, ArrayList<Customer> queue) {
        this.serverId = serverId;
        this.nextAvailTime = nextAvailTime;
        this.isAvail = isAvail;
        this.queue = queue;
        this.maxSize = 1;
    }

    Server(int serverId, double nextAvailTime, 
            boolean isAvail, ArrayList<Customer> queue,
            int maxSize) {
        this.serverId = serverId;
        this.nextAvailTime = nextAvailTime;
        this.isAvail = isAvail;
        this.queue = queue;
        this.maxSize = maxSize;
    }
    
    //get length of queue
    int length() {
        return this.queue.size();
    }
    
    //get server's Id
    int getServerId() {
        return this.serverId;
    }

    //see if queue is full
    boolean canAdd() {
        return this.queue.size() < maxSize; 
    }
    
    //get maxsize of queue
    int getMaxSize() {
        return this.maxSize;
    }

    //see if server is available to serve
    boolean getAvail() {
        return this.isAvail;
    }

    //see if queue is empty
    boolean isEmpty() {
        return this.queue.size() == 0;
    }

    //get server's next avail time
    double getNextAvailTime() {
        return this.nextAvailTime;
    }
    
    //get queue
    ArrayList<Customer> getQueue() {
        return this.queue;
    }
    
    @Override
    public String toString() {
        return "server " + this.serverId;
    }
}
