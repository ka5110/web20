package dao;

import entity.SystemUser;
import java.util.List;
import java.util.Optional;
import javax.persistence.Query;
import javax.ejb.Stateless;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 5110
 */

@Stateless
public class JpaSystemUserDao extends JpaDao<SystemUser, Long> implements SystemUserDao {

    @Override
    public Optional<SystemUser> getUserByEmail(String email) {
        Query query = this.entityManager.createQuery(
                "select u from SystemUser u where u.username = :email",
                SystemUser.class
        );
        query.setParameter("email", email);

        SystemUser user = null;

        try {
            return Optional.of((SystemUser) query.getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public List<SystemUser> getAllSimpleUsers() {
        Query q = this.entityManager.createQuery(
                "SELECT u FROM SystemUser u WHERE u.userGroup.groupname = 'users'",
                SystemUser.class
        );

        return q.getResultList();
    }

    @Override
    public List<SystemUser> getAllAdminUsers() {
        Query q = this.entityManager.createQuery(
                "SELECT u FROM SystemUser u WHERE u.userGroup.groupname = 'admins'",
                SystemUser.class
        );

        return q.getResultList();
    }

    @Override
    public List<SystemUser> getAllNormalUsers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
    
