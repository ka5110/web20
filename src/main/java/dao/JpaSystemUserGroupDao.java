/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.SystemUserGroup;
import javax.ejb.Stateless;

/**
 *
 * @author 5110
 */
@Stateless
public class JpaSystemUserGroupDao extends JpaDao<SystemUserGroup, Long> implements SystemUserGroupDao {
}
