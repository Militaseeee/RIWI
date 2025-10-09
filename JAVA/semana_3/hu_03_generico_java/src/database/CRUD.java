package database;

import java.util.List;

// Usamos <T> para indicar que esta interfaz trabajará con un tipo genérico
public interface CRUD<T> {
    T insert(T t);
    List<T> findAll();
    boolean update(T t);
    boolean delete(int id);
}
