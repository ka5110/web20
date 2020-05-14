/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import dto.SystemUserDto;
import ejb.SystemUserService;
import ejb.TransactionService;
import ejb.TransferService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author 5110
 */

@Named(value = "request")
@SessionScoped
public class TransferCredit implements Serializable {


    private SystemUserDto selectedUser;
    private List<SystemUserDto> users;
    private SystemUserDto currentUser;
    private int amount;

    @EJB
    SystemUserService userService;

    @EJB
    TransferService transferService;
    
    @EJB
    TransactionService transactionService;

   
    public TransferCredit() {
    }

    public void requestMoney() {
        TransferService.requestCredits(currentUser.getUsername(), selectedUser.getUsername(), amount);
        this.selectedUser = new SystemUserDto();
        this.users = new ArrayList<>();
        this.amount = 0;
    }

    public void commitTransfer() {

        if (TransferService.payCredits(currentUser.getUsername(), selectedUser.getUsername(), amount)) {
            System.out.println("Done");
        } else {
            System.out.println("out of credits");
        }
         
        SystemUserDto modifiedUser = this.userService.getCurrentUser();
        this.amount = 0;
    }



    public SystemUserDto getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(SystemUserDto selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<SystemUserDto> getUsers() {
        return users;
    }

    public void setUsers(List<SystemUserDto> users) {
        this.users = users;
    }

    public SystemUserDto getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(SystemUserDto currentUser) {
        this.currentUser = currentUser;
    }



    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public SystemUserService getUserService() {
        return userService;
    }

    public void setUserService(SystemUserService userService) {
        this.userService = userService;
    }

    public TransferService getTransferService() {
        return transferService;
    }

    public void setTransferService(TransferService transferService) {
        this.transferService = transferService;
    }
    
}