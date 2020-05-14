/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Transac;
import entity.TransacStatus;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author 5110
 */
@Stateless
public class JpaTransacDao extends JpaDao<Transac, Long> implements TransacDao {
    @Override
    public List<Transac> getAllSentTransactions(Long userId) {
        return this.entityManager
                .createQuery(
                        "select t from SystemTransaction t " +"where t.transactionOwner.id = :user " +"and t.status <> :request",Transac.class)
                .setParameter("user", userId)
                .setParameter("request", TransacStatus.REQUESTED)
                .getResultList();
    }

    @Override
    public List<Transac> getAllReceivedTransactions(Long userId) {
        return this.entityManager
                .createQuery(
                        "select t from SystemTransaction t " +"where t.transactionParticipantId = :user " + "and t.status <> :request",Transac.class)
                .setParameter("user", userId).setParameter("request", TransacStatus.REQUESTED).getResultList();
    }

    @Override
    public List<Transac> getAllReceivedRequests(long userId) {
        return this.entityManager
                .createQuery(
                        "select t from SystemTransaction t " + "where t.transactionParticipantId = :user " + "and t.status = :request", Transac.class)
                .setParameter("user", userId).setParameter("request", TransacStatus.REQUESTED).getResultList();

    }
}
