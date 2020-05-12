/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.SystemUser;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author 5110
 */
public interface SystemUserDao extends DAO<SystemUser, Long> {
    Optional<SystemUser> getUserByEmail(String email);
    List<SystemUser> getAllNormalUsers();
    List<SystemUser> getAllAdminUsers();
}

