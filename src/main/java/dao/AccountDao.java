/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;

/**
 *
 * @author 5110
 */
public interface AccountDao extends DAO<Account, Long> {
    void updateBalance(double newBalance, long id);
}
