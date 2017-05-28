package com.github.orelgenya.jcip.ch03;

/**
 * @author OrelGenya
 */
public class SafeListener extends ThisPublishing {
    private final EventListener listener;
    private SafeListener() {
        listener = new EventListener(){
            @Override
            public void onEvent(Event event) {
                SafeListener.this.doSomething();
            }
        };
    }

    public static SafeListener newInstance(EventSource source) {
        SafeListener safe = new SafeListener();
        source.registerListener(safe.listener);
        return safe;
    }
}
