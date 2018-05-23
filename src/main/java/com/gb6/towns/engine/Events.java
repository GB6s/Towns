package com.gb6.towns.engine;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.gb6.towns.utils.Constants.INSTANCE;
import static org.bukkit.Bukkit.getPluginManager;

@SuppressWarnings("unchecked")
public interface Events {

    Map<Class<? extends Event>, ConsumerListener> listeners = new HashMap<>();

    static <T extends Event> void listen(Class<T> type, Consumer<T> consumer) {
        listen(type, EventPriority.HIGHEST, consumer);
    }

    static <T extends Event> void listen(Class<T> type, EventPriority priority, Consumer<T> consumer) {
        listeners.computeIfAbsent(type, c -> {
            ConsumerListener bukkitListener = new ConsumerListener();
            getPluginManager().registerEvent(type, bukkitListener, priority, bukkitListener, INSTANCE);
            return bukkitListener;
        }).consumers.add(consumer);
    }

    class ConsumerListener<Type extends Event> implements Listener, EventExecutor {
        final List<Consumer<Type>> consumers = new ArrayList<>();

        @Override
        public void execute(Listener listener, Event event) throws EventException {
            consumers.forEach(c -> c.accept((Type) event));
        }
    }
}
