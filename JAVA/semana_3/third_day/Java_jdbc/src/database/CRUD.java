package database;

import java.util.List;

public interface CRUD {
    // Object en general para que le sirva para siembre hasta el create
    public Object insert(Object obj);
    public List<Object> findAll();
    public boolean update(Object obj);
    public boolean delete(Object obj);
}
