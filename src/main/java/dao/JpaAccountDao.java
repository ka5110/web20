/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Account;
import javax.ejb.Stateless;

/**
 *
 * @author 5110
 */
@Stateless
public class JpaAccountDao extends JpaDao<Account, Long> implements AccountDao {
    @Override
    public void updateBalance(double newBalance, long id) {
        Account account = this.entityManager.find(Account.class, id);
        account.setBalance(newBalance);
        this.entityManager.merge(account);
    }
}

