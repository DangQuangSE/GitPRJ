/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author Dell
 */
public interface IDAO<T, K> {
    //creat
    boolean creat(T object);
    //read;
    List<T> readAll();
    T readById(K id);
    //update;
    boolean update(T object);
    //delete
    boolean delete(K id);
    
}
