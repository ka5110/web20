/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;

/**
 *
 * @author 5110
 */
public interface DAO<E, K> {
    void persist(E entity);
    void remove(K id);
    E findById(K id);
    List<E> getAll();

}

