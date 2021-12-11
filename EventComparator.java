package cs2030.simulator;

import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    @Override
    public int compare(Event e1, Event e2) { 
        return (e1.getEventTime() - e2.getEventTime() > 0)
            ? 1
            : (e1.getEventTime() == e2.getEventTime()) 
            ? (e1.getCustomerId() - e2.getCustomerId())
            : -1;
    }
     
}
