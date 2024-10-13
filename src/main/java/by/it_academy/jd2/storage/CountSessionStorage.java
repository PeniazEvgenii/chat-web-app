package by.it_academy.jd2.storage;

import java.util.concurrent.atomic.AtomicLong;

public class CountSessionStorage implements ICountSessionStorage {
    private final AtomicLong activeSession = new AtomicLong(0);

    public void addActive() {
        activeSession.getAndIncrement();
    }

    public void remove() {
        activeSession.getAndDecrement();
    }

    public Long getCountActiveUsers() {
        return activeSession.get();
    }
}
