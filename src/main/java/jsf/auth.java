/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import dto.SystemUserDto;
import ejb.EmailAlreadyExistsException;
import ejb.SystemUserService;
import entity.SystemUser;
import java.io.IOException;
import java.io.Serializable;import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;

/**
 *
 * @author 5110
 */
@Named("authoriz")
@SessionScoped
public class auth implements Serializable {

    private SystemUserDto systemUserDto;
    

   
    @EJB
    SystemUserService systemUserService;
    
    private String errorMsg;
    private String currency;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    
    
        public auth() {
        this.systemUserDto = new SystemUserDto();
        
        
    }
           
      public SystemUserDto getUserDto() {
        return systemUserDto;
    }
      
            public void register() {   
        try {
            String hashedPassword = this.encodePassword();
            System.out.println("password hashed");
            SystemUser user = this.systemUserDto.asEntity();
            System.out.println("DTO entity created");
            user.setUserpassword(hashedPassword);

            try {
                this.systemUserService.registerUser(user, currency);
                System.out.println("registered in database?");
            } catch (EmailAlreadyExistsException e) {
                System.out.println("error calling registerUser Service");
            }
        } 
        catch (Exception ex) {
            ex.printStackTrace();
                System.out.println("Error");
            


        }

            }
            
        
       public void registerAdmin() throws EmailAlreadyExistsException {
   
          try {
            String hashedPassword = this.encodePassword();
            System.out.println("password hashed");
            SystemUser adminuser = this.systemUserDto.asEntity();
            System.out.println("DTO entity created");
            adminuser.setUserpassword(hashedPassword);

            try {
                this.systemUserService.registerAdmin(adminuser, currency);
                System.out.println("registered in database?");
            } catch (EmailAlreadyExistsException e) {
                System.out.println("error calling registerUser Service");
            }

        } 
        catch (Exception ex) {
            ex.printStackTrace();
                System.out.println("Error");
            
        }
    }        
      
            

   

          private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) {
            result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }

        private String encodePassword() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(this.systemUserDto.getUserpassword().getBytes(StandardCharsets.UTF_8));
        return bytesToHex(md.digest());
    }
        
            private void loginToServer(String username, String password) throws ServletException {
        try {
            this.systemUserService.loginUser(username, password);
        } catch (ServletException e) {
            throw e;
        }
    }

        
            public void login() {
        try {
            this.loginToServer(this.systemUserDto.getUsername(), this.systemUserDto.getUserpassword());
        } catch (ServletException e) {
            this.errorMsg = "Invalid username or password";
    
    }
            }
    public void logout() {
        try {
            this.systemUserService.logout();

        } catch (IOException ex) {
            Logger.getLogger(auth.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed");
        }
    }
       

}