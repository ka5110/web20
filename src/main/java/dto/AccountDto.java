/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Account;
import entity.SystemUser;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 5110
 */
public class AccountDto {

    private Long id;

    private double balance;

    private String currency;

    private SystemUser systemUser;

    public AccountDto() {
    }

 
    public AccountDto(@NotNull int balance, @NotNull String currency, @NotNull SystemUser systemUser) {
        this.balance = balance;
        this.currency = currency;
        this.systemUser = systemUser;
    }

    public Account asEntity() {
        Account account = new Account();
        account.setId(this.id);
        account.setBalance(this.balance);
        account.setCurrency(this.currency);
        account.setSystemUser(this.systemUser);

        return account;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public SystemUser getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }
}
