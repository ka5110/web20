/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import dto.AccountDto;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 5110
 */
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private double balance;

    @NotNull
    private String currency;

    @NotNull
    @OneToOne
    private SystemUser systemUser;

    public Account() {
    }


    /**
     * Users account
     *
     * @param balance    Remaining balance
     * @param currency   Selected currency for account see {@link Currency}
     * @param systemUser The associated {@link SystemUser}
     */
    public Account(@NotNull double balance, @NotNull String currency, @NotNull SystemUser systemUser) {
        this.balance = balance;
        this.currency = currency;
        this.systemUser = systemUser;
    }

    public AccountDto asDto() {
        AccountDto dto = new AccountDto();

        dto.setBalance(this.balance);
        dto.setCurrency(this.currency);
        dto.setSystemUser(this.systemUser);

        return dto;
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
