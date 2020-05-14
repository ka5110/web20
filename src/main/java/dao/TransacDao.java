/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Transac;
import java.util.List;

/**
 *
 * @author 5110
 */
public interface TransacDao extends DAO<Transac, Long> {
    List<Transac> getAllSentTransactions(Long userId);
    List<Transac> getAllReceivedTransactions(Long userId);
    List<Transac> getAllReceivedRequests(long userId);
}
