/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dao.SystemUserDao;
import dto.SystemUserDto;
import entity.SystemUser;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

/**
 *
 * @author 5110
 */
@Stateless
public class SystemUserServiceImpl implements SystemUserService {

    @EJB
    private SystemUserDao userDao;
   
    @Override
    @PermitAll
    @Transactional(Transactional.TxType.REQUIRED)
    public void registerUser(SystemUser user) throws EmailAlreadyExistsException {

        SystemUser existingUser = userDao.getUserByEmail(user.getUsername()).orElse(null);

    
        userDao.persist(user);
    }
}


