/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

/**
 *
 * @author 5110
 */
public class EmailAlreadyExistsException extends Exception {

    public EmailAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
    
}

