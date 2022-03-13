package pl.edu.pg.game.repository;

import java.util.*;

public class MemMap<E extends Comparable<E>, F> {
    protected Map<E, F> map;

    public MemMap() {
        map = new HashMap<>();
    }

    public MemMap(boolean sorted) {
        map = sorted ? new TreeMap<E, F>() : new HashMap<E, F>();
    }

    public MemMap(Comparator<E> comparator) {
        map = new TreeMap<>(comparator);
    }

    @Override
    public String toString() {
        String output = "";
        for (Map.Entry<E, F> entry : map.entrySet()) {
            output += "<" + entry.getKey().toString() + ", " +
                    entry.getValue().toString() + ">\n";
        }
        return output;
    }

    public Map<E, F> findAll() {
        return map;
    }

    public void delete(E entity) {
        map.remove(entity);
    }

    public void add(E key, F value) {
        map.put(key, value);
    }
}
