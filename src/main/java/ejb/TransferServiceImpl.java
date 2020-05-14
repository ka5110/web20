/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dao.AccountDao;
import dao.SystemUserDao;
import dao.TransacDao;
import entity.Account;
import entity.SystemUser;
import entity.Transac;
import entity.TransacStatus;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author 5110
 */
@Stateless
@RolesAllowed({"users", "admins"})
public class TransferServiceImpl implements TransferService {

    @EJB
    SystemUserDao userDao;

    @EJB
    AccountDao accountDao;

    @EJB
    TransacDao transactionDao;


    public TransferServiceImpl() {
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean payCredits(String from, String to, double amount) {
        SystemUser userFrom = userDao.getUserByEmail(from).get();
        SystemUser userTo = userDao.getUserByEmail(to).get();

        boolean canTransferComplete = userFrom.getAccount().getBalance() - amount >= 0;

        if (!canTransferComplete) {
            return false;
        }
        Account accountFrom = userFrom.getAccount();
        Account accountTo = userTo.getAccount();

        accountDao.updateBalance(accountFrom.getBalance() - amount, accountFrom.getId());
        accountDao.updateBalance(accountTo.getBalance() + amount, accountTo.getId());

        transactionDao.persist(new Transac(TransacStatus.APPROVED, userFrom, userTo.getId(), amount));
        return true;
    }

    public void requestCredits(String from, String to, double amount) {
        SystemUser userTo = userDao.getUserByEmail(to).get();
        SystemUser userFrom = userDao.getUserByEmail(from).get();
        transactionDao.persist(new Transac(TransacStatus.REQUESTED, userFrom, userTo.getId(), amount));
    }
}
    
