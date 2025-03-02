
package dao;

import java.util.List;

public interface IDAO <V, K> {
    //create
    boolean create(V object);
    //read
    List<V> readAll();
    V readById(K id);
    //update
    boolean update(V object);
    //delete
    boolean delete(K id);
}
