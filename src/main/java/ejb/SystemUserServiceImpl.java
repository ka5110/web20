/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dao.SystemUserDao;
import dao.SystemUserGroupDao;
import dto.SystemUserDto;

import entity.Account;
import entity.SystemUser;
import entity.SystemUserGroup;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

/**
 *
 * @author 5110
 */
@Stateless
@RolesAllowed({"users", "admins"})
public class SystemUserServiceImpl implements SystemUserService {

    @EJB
    private SystemUserDao userDao;
    
    @EJB
    private SystemUserGroupDao userGroupDao;


    @PermitAll
    @Transactional(Transactional.TxType.REQUIRED)
    public void registerUser(SystemUser user, String currency) throws EmailAlreadyExistsException {

        SystemUser existingUser = userDao.getUserByEmail(user.getUsername()).orElse(null);
                if (existingUser != null) {
            throw new EmailAlreadyExistsException("email exists");
        }
          SystemUserGroup userGroup = new SystemUserGroup(user, user.getUsername(), "users");

        double balance = 1000.0;
     

        Account account = new Account(balance, currency, user);
        user.setAccount(account);

        System.out.println("Registered User");

        userDao.persist(user);
        userGroupDao.persist(userGroup);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect("/webapps2020/faces/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SystemUserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    @Override
    @PermitAll
    public void loginUser(String username, String password) throws ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.login(username, password);
        System.out.println( username + "logged in");
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if(isLoggedUserAdmin()){
            try {
                externalContext.redirect("/webapps2020/faces/adminsdash.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(SystemUserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            try {
                externalContext.redirect("/webapps2020/faces/userdash.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(SystemUserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        
    }
    
    public boolean isLoggedUserAdmin() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        return context.isUserInRole("admins");
    }
    
    @Override
    public void logout() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        FacesContext context = FacesContext.getCurrentInstance();
        externalContext.redirect("/webapps2020/faces/index.xhtml");
        context.getExternalContext().invalidateSession();
    }

    
        @Override
    public boolean isUserAdmin() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

        return context.isUserInRole("admins");
    }
    
    @PermitAll
    @Transactional(Transactional.TxType.REQUIRED)
        public void registerAdmin(SystemUser user, String currency) throws EmailAlreadyExistsException {

        SystemUser existingUser = userDao.getUserByEmail(user.getUsername()).orElse(null);
                if (existingUser != null) {
            throw new EmailAlreadyExistsException("email exists");
        }
          SystemUserGroup userGroup = new SystemUserGroup(user, user.getUsername(), "admins");

        double balance = 1000.0;
     

        Account account = new Account(balance, currency, user);
        user.setAccount(account);

        System.out.println("Registered User");

        userDao.persist(user);
        userGroupDao.persist(userGroup);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect("/webapps2020/faces/adminsdash.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SystemUserServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

}
    public SystemUserDto findUser(long id) {
        return this.userDao.findById(id).toDto();
    }

}



