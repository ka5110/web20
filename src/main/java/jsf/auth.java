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
import java.io.Serializable;import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author 5110
 */
@Named("authoriz")
@SessionScoped
public class auth implements Serializable {

    private SystemUserDto systemUserDto;
    

    @Inject
    private Navigation navigation;
   
    @EJB
    SystemUserService systemUserService;
    
    
        public auth() {
        this.systemUserDto = new SystemUserDto();
        
        
    }
           
      public SystemUserDto getUserDto() {
        return systemUserDto;
    }
      
            public String register() {   
        try {
            String hashedPassword = this.encodePassword();
            System.out.println("password hashed");
            SystemUser user = this.systemUserDto.asEntity();
            System.out.println("DTO entity created");
            user.setUserpassword(hashedPassword);

            try {
                this.systemUserService.registerUser(user);
                System.out.println("registered in database?");
            } catch (EmailAlreadyExistsException e) {
                System.out.println("error calling registerUser Service");
                return navigation.getREGISTER_FAILURE();
            }

        } 
        catch (Exception ex) {
            ex.printStackTrace();
                System.out.println("Error");
            

            return navigation.getREGISTER_FAILURE();

        }

        return navigation.getREGISTER_SUCCESS();
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
        
       

}