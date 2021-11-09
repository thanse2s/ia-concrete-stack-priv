package de.hbrs.concrete_stack.services;

import java.util.List;

public interface CrudService<T> {

     List<T> readAll();
     T read(long id);
     boolean delete(long id);
     boolean update(long id,T t);
     boolean create(T t);

}
