package pl.edu.pg.repository;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Optional;

public class EntityRepo<K,E>{
    private HashMap<K,E> collection;

    public EntityRepo() {
        collection = new HashMap<>();
    }

    public Optional<E> find(K key) {
        Optional<E> result;
        E entity = collection.get(key);
        if(entity == null) {
            result = Optional.empty();
        } else {
            result = Optional.of(entity);
        }
        return result;
    }

    public void delete(K key) throws IllegalArgumentException {
        if(!collection.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        collection.remove(key);
    }

    public void save(K key, E entity) throws IllegalArgumentException{
        if(collection.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        collection.put(key,entity);
    }
}
