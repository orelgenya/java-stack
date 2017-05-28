package com.github.orelgenya.jcip.ch03;

import com.github.orelgenya.jcip.annotations.NotThreadSafe;

/**
 * @author OrelGenya
 */
@NotThreadSafe
public class ThisEscape extends ThisPublishing {
    public ThisEscape(EventSource source) {
        source.registerListener(new EventListener() {

            @Override
            public void onEvent(Event e) {
                ThisEscape.this.doSomething();
            }
        });
    }
}

interface EventSource {
    public void registerListener(EventListener l);
}

interface EventListener {
    public void onEvent(Event e);
}

class Event {
}

abstract class ThisPublishing {
    protected void doSomething() {
        System.out.println("Something.");
    }
}
