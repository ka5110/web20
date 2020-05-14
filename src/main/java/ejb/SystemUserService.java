/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import dto.SystemUserDto;
import entity.SystemUser;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;

/**
 *
 * @author 5110
 */
public interface SystemUserService {
    
 

    void registerUser(SystemUser user, String currency) throws EmailAlreadyExistsException;

    void loginUser(String email, String password) throws ServletException;

    void logout() throws IOException;

    boolean isUserAdmin();
        
     public void registerAdmin(SystemUser user, String currency) throws EmailAlreadyExistsException;

//    SystemUserDto getCurrentUser();
//    
//    List<SystemUserDto> getAllUsers();
//    
    SystemUserDto findUser(long id);
//    
//    SystemUserDto getByEmail(String email);
//    
//    List<SystemUserDto> getAllNormalUsers();
//    
//    List<SystemUserDto> getAllAdminUsers();
//

        

}

