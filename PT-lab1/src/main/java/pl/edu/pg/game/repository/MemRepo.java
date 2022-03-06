package pl.edu.pg.game.repository;

import java.util.*;

public class MemRepo <E extends Comparable<E>>{
    protected Set<E> set;

    public MemRepo() {
        set = new HashSet<>();
    }

    public MemRepo(boolean sorted) {
        set = sorted ? new TreeSet<>() : new HashSet<>();
    }

    public MemRepo(Comparator<E> comparator) {
        set = new TreeSet<>(comparator);
    }

    public List<E> findAll() {
        return new ArrayList<>(set);
    }

    public void delete(E entity) {
        set.remove(entity);
    }

    public void add(E entity) {
        set.add(entity);
    }
}
