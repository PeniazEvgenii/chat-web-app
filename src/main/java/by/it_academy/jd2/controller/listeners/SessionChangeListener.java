package by.it_academy.jd2.controller.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionChangeListener implements HttpSessionAttributeListener {
    AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("Новый аттрибут " + event.getName());
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        atomicInteger.getAndDecrement();
    }
}
