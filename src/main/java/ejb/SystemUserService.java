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
    
 

    void registerUser(SystemUser user) throws EmailAlreadyExistsException;

    

}

