package pl.edu.pg.controller;

import pl.edu.pg.repository.EntityRepo;

import java.util.Optional;

public class StringController {
        private final EntityRepo<String,String> repository;

        public StringController(EntityRepo<String,String> repository) {
            this.repository = repository;
        }

        public String find(String name) {
            Optional<String> entity = repository.find(name);
            if(entity.isPresent()) {
                return entity.toString();
            } else {
                return "not found";
            }
        }

        public String delete(String name) {
            try{
                repository.delete(name);
                return "done";
            } catch (IllegalArgumentException e) {
                return "not found";
            }
        }

        public String save(String key, String entity) {
            try{
                repository.save(key,entity);
                return "done";
            } catch (IllegalArgumentException e) {
                return "bad request";
            }
        }
}
